@file:OptIn(ExperimentalMaterial3Api::class)

package com.rocky.run.presentation.run_overview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocky.core.presentation.designsystem.AnalyticsIcon
import com.rocky.core.presentation.designsystem.LogoutIcon
import com.rocky.core.presentation.designsystem.PacerfectTheme
import com.rocky.core.presentation.designsystem.RunIcon
import com.rocky.core.presentation.designsystem.components.PacerfectFloatingActionButton
import com.rocky.core.presentation.designsystem.components.PacerfectScaffold
import com.rocky.core.presentation.designsystem.components.PacerfectToolbar
import com.rocky.core.presentation.designsystem.components.util.DropDownItem
import com.rocky.run.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable

fun RunOverviewScreen(
    viewModel: RunOverviewViewModel = koinViewModel()
) {
    RunOverviewContent(
        onAction = viewModel::onAction
    )
}

@Composable
private fun RunOverviewContent(
    onAction: (RunOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    PacerfectScaffold(
        topAppBar = {
            PacerfectToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.pacerfect),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(id = R.string.analytics)
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout)
                    )
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = RunIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
            )
        },
        floatingActionButton = {
            PacerfectFloatingActionButton(
                onClick = { onAction(RunOverviewAction.OnStartClick) },
                icon = RunIcon
            )
        }
    ) { padding ->

    }
}

@Preview
@Composable
private fun RunOverviewScreenPreview() {
    PacerfectTheme {
        RunOverviewContent(
            onAction = {}
        )
    }
}