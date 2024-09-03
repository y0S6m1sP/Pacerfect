package com.rocky.run.presentation.di

import com.rocky.run.domain.RunningTracker
import com.rocky.run.presentation.active_run.ActiveRunViewModel
import com.rocky.run.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val runPresentationModule = module {
    singleOf(::RunningTracker)

    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}