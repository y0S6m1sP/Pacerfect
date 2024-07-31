package com.rocky.auth.data.di

import com.rocky.auth.data.DefaultAuthRepository
import com.rocky.auth.data.EmailPatternValidator
import com.rocky.auth.domain.AuthRepository
import com.rocky.auth.domain.PatternValidator
import com.rocky.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::DefaultAuthRepository).bind<AuthRepository>()
}