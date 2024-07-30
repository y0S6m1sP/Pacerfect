package com.rocky.auth.presentation.onboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rocky.auth.presentation.R
import com.rocky.core.presentation.designsystem.PacerfectTheme
import com.rocky.core.presentation.designsystem.RunIcon
import com.rocky.core.presentation.designsystem.components.GradientBackground
import com.rocky.core.presentation.designsystem.components.PacerfectActionButton
import com.rocky.core.presentation.designsystem.components.PacerfectOutlinedActionButton


@Composable
fun OnBoardScreen(
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    OnBoardContent(
        onAction = {
            when (it) {
                OnBoardAction.OnSignInClick -> onSignInClick()
                OnBoardAction.OnSignUpClick -> onSignUpClick()
            }
        }
    )
}

@Composable
fun OnBoardContent(
    onAction: (OnBoardAction) -> Unit
) {
    GradientBackground {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            PacerfectLogoVertical()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 48.dp)
        ) {
            Text(
                text = stringResource(id = R.string.welcome_to_pacerfect),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.pacerfect_description),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(32.dp))
            PacerfectOutlinedActionButton(
                text = stringResource(id = R.string.sign_in),
                isLoading =false,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onAction(OnBoardAction.OnSignInClick) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PacerfectActionButton(
                text = stringResource(id = R.string.sign_up),
                isLoading = false,
                modifier =  Modifier.fillMaxWidth(),
                onClick = { onAction(OnBoardAction.OnSignUpClick) }
            )
        }
    }
}

@Composable
private fun PacerfectLogoVertical(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = RunIcon,
            contentDescription = "Logo",
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.pacerfect),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun OnBoardScreenPreview() {
    PacerfectTheme {
        OnBoardContent(
            onAction = {}
        )
    }
}