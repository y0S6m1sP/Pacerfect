package com.rocky.pacerfect

import android.app.Application
import com.rocky.auth.data.di.authDataModule
import com.rocky.auth.presentation.di.authViewModelModule
import com.rocky.core.data.di.coreDataModule
import com.rocky.core.database.di.databaseModule
import com.rocky.pacerfect.di.appModule
import com.rocky.run.location.di.locationModule
import com.rocky.run.presentation.di.runPresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PacerfectApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

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
                runPresentationModule,
                appModule,
                coreDataModule,
                locationModule,
                databaseModule
            )
        }
    }
}