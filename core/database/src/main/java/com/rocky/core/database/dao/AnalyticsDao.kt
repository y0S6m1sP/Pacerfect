package com.rocky.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.rocky.core.domain.analytics.AvgDistanceOverTime

@Dao
interface AnalyticsDao {

    @Query("SELECT SUM(distanceMeters) FROM RunEntity")
    suspend fun getTotalDistance(): Int

    @Query("SELECT SUM(durationMillis) FROM RunEntity")
    suspend fun getTotalTimeRun(): Long

    @Query("SELECT MAX(maxSpeedKmh) FROM RunEntity")
    suspend fun getMaxRunSpeed(): Double

    @Query("SELECT AVG(distanceMeters / 1000.0) FROM RunEntity")
    suspend fun getAvgDistance(): Double

    @Query("SELECT AVG((durationMillis / 60000.0) / (distanceMeters / 1000.0)) FROM RunEntity")
    suspend fun getAvgPace(): Double

    @Query(" SELECT strftime('%m-%d', dateTimeUtc) as day, AVG(distanceMeters / 1000.0) as avgDistance FROM RunEntity GROUP BY day ORDER BY day")
    suspend fun getAvgDistanceOverTime(): List<AvgDistanceOverTime>
}