@file:OptIn(ExperimentalMaterial3Api::class)

package com.rocky.run.presentation.active_run

import android.Manifest
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocky.core.presentation.designsystem.PacerfectTheme
import com.rocky.core.presentation.designsystem.StartIcon
import com.rocky.core.presentation.designsystem.StopIcon
import com.rocky.core.presentation.designsystem.components.PacerfectDialog
import com.rocky.core.presentation.designsystem.components.PacerfectFloatingActionButton
import com.rocky.core.presentation.designsystem.components.PacerfectOutlinedActionButton
import com.rocky.core.presentation.designsystem.components.PacerfectScaffold
import com.rocky.core.presentation.designsystem.components.PacerfectToolbar
import com.rocky.run.presentation.R
import com.rocky.run.presentation.active_run.components.RunDataCard
import com.rocky.run.presentation.active_run.maps.TrackerMap
import com.rocky.run.presentation.util.hasLocationPermission
import com.rocky.run.presentation.util.hasNotificationPermission
import com.rocky.run.presentation.util.shouldShowLocationPermissionRationale
import com.rocky.run.presentation.util.shouldShowNotificationPermissionRationale
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
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val hasCoarseLocationPermission =
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        val hasFineLocationPermission =
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val hasNotificationPermission = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
            permissions[Manifest.permission.ACCESS_BACKGROUND_LOCATION] == true
        } else true

        val activity = context as ComponentActivity
        val showLocationRationale = activity.shouldShowLocationPermissionRationale()
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            ActiveRunAction.SubmitLocationPermissionInfo(
                acceptedLocationPermission = hasCoarseLocationPermission || hasFineLocationPermission,
                showLocationRationale = showLocationRationale
            )
        )
        onAction(
            ActiveRunAction.SubmitNotificationPermissionInfo(
                acceptedNotificationPermission = hasNotificationPermission,
                showNotificationRationale = showNotificationRationale
            )
        )
    }

    LaunchedEffect(key1 = true) {
        val activity = context as ComponentActivity
        val showLocationRationale = activity.shouldShowLocationPermissionRationale()
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            ActiveRunAction.SubmitLocationPermissionInfo(
                acceptedLocationPermission = context.hasLocationPermission(),
                showLocationRationale = showLocationRationale
            )
        )
        onAction(
            ActiveRunAction.SubmitNotificationPermissionInfo(
                acceptedNotificationPermission = context.hasNotificationPermission(),
                showNotificationRationale = showNotificationRationale
            )
        )

        if (!showLocationRationale && !showNotificationRationale) {
            permissionLauncher.requestPacerfectPermissions(context)
        }
    }

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
            TrackerMap(
                isRunFinished = state.isRunFinished,
                currentLocation = state.currentLocation,
                locations = state.runData.locations,
                onSnapshot = {},
                modifier = Modifier.fillMaxSize()
            )
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

    if (state.showLocationRationale || state.showNotificationRationale) {
        PacerfectDialog(
            title = stringResource(id = R.string.permission_rationale_title),
            onDismiss = {},
            description = when {
                state.showLocationRationale && state.showNotificationRationale -> {
                    stringResource(id = R.string.permission_rationale_description_both)
                }

                state.showLocationRationale -> {
                    stringResource(id = R.string.permission_rationale_description_location)
                }

                else -> stringResource(id = R.string.permission_rationale_description_notification)
            },
            primaryButton = {
                PacerfectOutlinedActionButton(
                    text = stringResource(id = R.string.okay),
                    isLoading = false,
                    onClick = {
                        onAction(ActiveRunAction.DismissRationaleDialog)
                        permissionLauncher.requestPacerfectPermissions(context)
                    }
                )
            }
        )
    }
}

private fun ActivityResultLauncher<Array<String>>.requestPacerfectPermissions(
    context: Context
) {
    val hasLocationPermission = context.hasLocationPermission()
    val hasNotificationPermission = context.hasNotificationPermission()

    val locationPermission = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val notificationPermission = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
        arrayOf(Manifest.permission.POST_NOTIFICATIONS)
    } else arrayOf()

    when {
        !hasLocationPermission && !hasNotificationPermission -> {
            launch(locationPermission + notificationPermission)
        }

        !hasLocationPermission -> launch(locationPermission)
        !hasNotificationPermission -> launch(notificationPermission)
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