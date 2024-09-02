@file:OptIn(ExperimentalMaterial3Api::class)

package com.rocky.run.presentation.active_run

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocky.core.presentation.designsystem.PacerfectTheme
import com.rocky.core.presentation.designsystem.StartIcon
import com.rocky.core.presentation.designsystem.StopIcon
import com.rocky.core.presentation.designsystem.components.PacerfectFloatingActionButton
import com.rocky.core.presentation.designsystem.components.PacerfectScaffold
import com.rocky.core.presentation.designsystem.components.PacerfectToolbar
import com.rocky.run.presentation.R
import com.rocky.run.presentation.active_run.components.RunDataCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActiveRunScreen(
    viewModel: ActiveRunViewModel = koinViewModel()
) {
    ActiveRunContent(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun ActiveRunContent(
    state: ActiveRunState,
    onAction: (ActiveRunAction) -> Unit
) {
    PacerfectScaffold(
        withGradient = false,
        topAppBar = {
            PacerfectToolbar(
                showBackButton = true,
                title = stringResource(id = R.string.active_run),
                onBackClick = { onAction(ActiveRunAction.OnBackClick) },
            )
        },
        floatingActionButton = {
            PacerfectFloatingActionButton(
                onClick = { onAction(ActiveRunAction.OnToggleRunClick) },
                icon = if (state.shouldTrack) StopIcon else StartIcon,
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            RunDataCard(
                elapsedTime = state.elapsedTime,
                runData = state.runData,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(padding)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun ActiveRunScreenPreview() {
    PacerfectTheme {
        ActiveRunContent(
            state = ActiveRunState(),
            onAction = {}
        )
    }
}