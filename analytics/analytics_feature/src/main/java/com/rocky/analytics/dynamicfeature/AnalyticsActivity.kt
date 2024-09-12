package com.rocky.analytics.dynamicfeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.play.core.splitcompat.SplitCompat
import com.rocky.analytics.data.di.analyticsDataModule
import com.rocky.analytics.presentation.AnalyticsDashboardScreen
import com.rocky.analytics.presentation.di.analyticsPresentationModule
import com.rocky.core.presentation.designsystem.PacerfectTheme
import org.koin.core.context.loadKoinModules

class AnalyticsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loadKoinModules(
            listOf(
                analyticsDataModule,
                analyticsPresentationModule
            )
        )
        SplitCompat.installActivity(this)

        setContent {
            PacerfectTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "analytics"
                ) {
                    composable("analytics") {
                        AnalyticsDashboardScreen(
                            onBackClick = { finish() }
                        )
                    }
                }
            }
        }
    }
}