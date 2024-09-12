package com.rocky.analytics.presentation

sealed interface AnalyticsAction {
    data object OnBackClick: AnalyticsAction
}