@file:OptIn(ExperimentalMaterial3Api::class)

package com.rocky.analytics.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocky.analytics.presentation.components.AnalyticsCard
import com.rocky.core.presentation.designsystem.PacerfectTheme
import com.rocky.core.presentation.designsystem.components.PacerfectScaffold
import com.rocky.core.presentation.designsystem.components.PacerfectToolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnalyticsDashboardScreen(
    onBackClick: () -> Unit,
    viewModel: AnalyticsDashboardViewModel = koinViewModel()
) {
    AnalyticsDashboardContent(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is AnalyticsAction.OnBackClick -> onBackClick()
            }
        }
    )
}

@Composable
private fun AnalyticsDashboardContent(
    state: AnalyticsDashboardState?,
    onAction: (AnalyticsAction) -> Unit
) {
    PacerfectScaffold(
        topAppBar = {
            PacerfectToolbar(
                showBackButton = true,
                title = stringResource(id = R.string.analytics),
                onBackClick = { onAction(AnalyticsAction.OnBackClick) }
            )
        }
    ) { padding ->
        if (state == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {

            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    AnalyticsCard(
                        title = stringResource(id = R.string.total_distance_run),
                        value = state.totalDistanceRun,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    AnalyticsCard(
                        title = stringResource(id = R.string.total_time_run),
                        value = state.totalTimeRun,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    AnalyticsCard(
                        title = stringResource(id = R.string.fastest_ever_run),
                        value = state.fastestEverRun,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    AnalyticsCard(
                        title = stringResource(id = R.string.avg_distance),
                        value = state.avgDistance,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    AnalyticsCard(
                        title = stringResource(id = R.string.avg_pace),
                        value = state.avgPace,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AnalyticsDashboardScreenPreview() {
    PacerfectTheme {
        AnalyticsDashboardContent(
            state = AnalyticsDashboardState(
                totalDistanceRun = "100 km",
                totalTimeRun = "0d 0h 0m",
                fastestEverRun = "5 km/h",
                avgDistance = "5 km",
                avgPace = "08:00"
            ),
            onAction = {}
        )
    }
}