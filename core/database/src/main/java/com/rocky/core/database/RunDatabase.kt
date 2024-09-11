package com.rocky.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rocky.core.database.dao.RunDao
import com.rocky.core.database.dao.RunPendingSyncDao
import com.rocky.core.database.entity.DeleteRunSyncEntity
import com.rocky.core.database.entity.RunEntity
import com.rocky.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [RunEntity::class, RunPendingSyncEntity::class, DeleteRunSyncEntity::class],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {
    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
}