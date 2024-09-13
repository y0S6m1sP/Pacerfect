package com.rocky.analytics.data

import android.util.Log
import com.rocky.analytics.data.mappers.toChartData
import com.rocky.analytics.domain.AnalyticsRepository
import com.rocky.analytics.domain.AnalyticsValues
import com.rocky.analytics.domain.ChartData
import com.rocky.core.database.dao.AnalyticsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class RoomAnalyticsRepository(
    private val analyticsDao: AnalyticsDao
) : AnalyticsRepository {

    override suspend fun getAnalyticsValues(): AnalyticsValues {
        return withContext(Dispatchers.IO) {
            val totalDistance = async { analyticsDao.getTotalDistance() }
            val totalTimeMillis = async { analyticsDao.getTotalTimeRun() }
            val maxRunSpeed = async { analyticsDao.getMaxRunSpeed() }
            val avgDistance = async { analyticsDao.getAvgDistance() }
            val avgPace = async { analyticsDao.getAvgPace() }
            val avgDistanceOverTime = async { analyticsDao.getAvgDistanceOverTime().map {
                it.toChartData()
            } }

            Log.e("test", "${avgDistanceOverTime.await()}")

            AnalyticsValues(
                totalDistanceRun = totalDistance.await(),
                totalTimeRun = totalTimeMillis.await().milliseconds,
                fastestEverRun = maxRunSpeed.await(),
                avgDistance = avgDistance.await(),
                avgPace = avgPace.await(),
                avgDistanceOverTime = avgDistanceOverTime.await()
            )
        }
    }
}