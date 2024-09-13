package com.rocky.analytics.data.mappers

import com.rocky.analytics.domain.ChartData
import com.rocky.core.domain.analytics.AvgDistanceOverTime

fun AvgDistanceOverTime.toChartData() = ChartData(day, avgDistance)