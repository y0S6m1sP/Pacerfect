package com.rocky.pacerfect

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rocky.auth.presentation.onboard.OnBoardScreen
import com.rocky.auth.presentation.register.RegisterScreen

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        authGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = "onboard",
        route = "auth"
    ) {
        composable(route = "onboard") {
            OnBoardScreen(
                onSignUpClick = { navController.navigate("register") },
                onSignInClick = { navController.navigate("login") }
            )
        }
        composable(route = "register") {
            RegisterScreen(
                onSignInClick = {
                    navController.navigate("login") {
                        popUpTo("register") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = { navController.navigate("login") }
            )
        }
    }
}