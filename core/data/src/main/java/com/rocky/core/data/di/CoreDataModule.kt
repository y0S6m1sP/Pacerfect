package com.rocky.core.data.di

import com.rocky.core.data.auth.EncryptedSessionStorage
import com.rocky.core.data.networking.HttpClientFactory
import com.rocky.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}