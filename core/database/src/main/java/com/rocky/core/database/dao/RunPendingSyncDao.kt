package com.rocky.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rocky.core.database.entity.DeleteRunSyncEntity
import com.rocky.core.database.entity.RunPendingSyncEntity

@Dao
interface RunPendingSyncDao {

    @Query("SELECT * FROM runpendingsyncentity WHERE userId = :userId")
    suspend fun getAllRunPendingSyncs(userId: String): List<RunPendingSyncEntity>

    @Query("SELECT * FROM runpendingsyncentity WHERE userId = :userId")
    suspend fun getRunPendingSync(userId: String): RunPendingSyncEntity?

    @Upsert
    suspend fun upsertRunPendingSync(entity: RunPendingSyncEntity)

    @Query("DELETE FROM runpendingsyncentity WHERE runId = :runId")
    suspend fun deleteRunPendingSync(runId: String)

    @Query("DELETE FROM deleterunsyncentity WHERE userId = :userId")
    suspend fun getAllDeletedRunSyncs(userId: String): List<DeleteRunSyncEntity>

    @Upsert
    suspend fun upsertDeletedRunSync(entity: DeleteRunSyncEntity)

    @Query("DELETE FROM deleterunsyncentity WHERE runId = :runId")
    suspend fun deleteDeletedRunSync(runId: String)
}