package com.rocky.run.data.di

import com.rocky.core.domain.run.SyncRunScheduler
import com.rocky.run.data.CreateRunWorker
import com.rocky.run.data.DeleteRunWorker
import com.rocky.run.data.FetchRunsWorker
import com.rocky.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}