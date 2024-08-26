package com.rocky.auth.presentation.di

import com.rocky.auth.presentation.login.LoginViewModel
import com.rocky.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}