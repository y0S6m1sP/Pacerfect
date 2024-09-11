package com.rocky.run.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rocky.core.database.dao.RunPendingSyncDao
import com.rocky.core.database.mappers.toRun
import com.rocky.core.domain.run.RemoteRunDataSource

class CreateRunWorker(
    context: Context,
    private val params: WorkerParameters,
    private val remoteRunDataSource: RemoteRunDataSource,
    private val pendingSyncDao: RunPendingSyncDao
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 5) {
            return Result.failure()
        }

        val pendingRunId = params.inputData.getString(RUN_ID) ?: return Result.failure()
        val pendingRunSync = pendingSyncDao.getRunPendingSync((pendingRunId))
            ?: return Result.failure()

        val run = pendingRunSync.run.toRun()
        return when (val result =
            remoteRunDataSource.postRun(run, pendingRunSync.mapPictureBytes)) {
            is com.rocky.core.domain.util.Result.Success -> {
                pendingSyncDao.deleteRunPendingSync(pendingRunId)
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