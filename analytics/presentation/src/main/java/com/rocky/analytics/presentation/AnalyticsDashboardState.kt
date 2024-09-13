package com.rocky.analytics.presentation

import com.rocky.analytics.domain.ChartData

data class AnalyticsDashboardState(
    val totalDistanceRun: String,
    val totalTimeRun: String,
    val fastestEverRun: String,
    val avgDistance: String,
    val avgPace: String,
    val avgDistanceOverTime: List<ChartData>
)
