package com.rocky.pacerfect

import android.app.Application
import com.rocky.auth.data.di.authDataModule
import com.rocky.auth.presentation.di.authViewModelModule
import com.rocky.pacerfect.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PacerfectApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@PacerfectApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule
            )
        }
    }
}