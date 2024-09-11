package com.rocky.run.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rocky.core.database.dao.RunPendingSyncDao
import com.rocky.core.domain.run.RemoteRunDataSource

class DeleteRunWorker(
    context: Context,
    private val params: WorkerParameters,
    private val remoteRunDataSource: RemoteRunDataSource,
    private val pendingSyncDao: RunPendingSyncDao
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 5) {
            return Result.failure()
        }

        val runId = params.inputData.getString(RUN_ID) ?: return Result.failure()
        return when (val result = remoteRunDataSource.deleteRun(runId)) {
            is com.rocky.core.domain.util.Result.Success -> {
                pendingSyncDao.deleteDeletedRunSync(runId)
                Result.success()
            }

            is com.rocky.core.domain.util.Result.Error -> {
                result.error.toWorkerResult()
            }
        }
    }

    companion object {
        const val RUN_ID = "RUN_ID"
    }
}