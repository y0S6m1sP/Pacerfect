package com.rocky.core.data.di

import com.rocky.core.data.auth.EncryptedSessionStorage
import com.rocky.core.data.networking.HttpClientFactory
import com.rocky.core.data.run.DefaultRunRepository
import com.rocky.core.domain.SessionStorage
import com.rocky.core.domain.run.RunRepository
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build(CIO.create())
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()

    singleOf(::DefaultRunRepository).bind<RunRepository>()
}