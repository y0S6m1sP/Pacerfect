package com.rocky.analytics.data.di

import com.rocky.analytics.data.RoomAnalyticsRepository
import com.rocky.analytics.domain.AnalyticsRepository
import com.rocky.core.database.RunDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsDataModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
    single {
        get<RunDatabase>().analyticsDao
    }
}