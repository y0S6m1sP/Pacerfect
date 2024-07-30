package com.rocky.auth.presentation.onboard

sealed interface OnBoardAction {
    data object OnSignInClick : OnBoardAction
    data object OnSignUpClick : OnBoardAction
}