package io.pdaa.chilenastats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardScreen
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeagueSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeaguesViewModel
import io.pdaa.chilenastats.ui.screens.login.sign_in.SignInScreen
import io.pdaa.chilenastats.ui.screens.login.sign_up.SignUpScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionViewModel
import io.pdaa.chilenastats.ui.screens.splash_screen.SplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen) {

        composable<SplashScreen> {
            SplashScreen(
                viewModel = koinViewModel(),
                openAndPopUp = {
                    navController.navigate(
                        SignIn
                    ) {
                        launchSingleTop = true
                        popUpTo(SplashScreen) { inclusive = true }
                    }
                }
            )
        }

        composable<SignIn> {
            SignInScreen(
                onClickSignUp = {
                    navController.navigate(SignUp)
                },
                vm = koinViewModel()
            )
        }

        composable<SignUp> {
            SignUpScreen(
                onSuccessSignUp = {
                    navController.navigate(
                        LeaguesSelector
                    ) {
                        launchSingleTop = true
                        popUpTo(SignIn) { inclusive = true }
                    }
                },
                viewModel = koinViewModel()
            )
        }


        composable<LeaguesSelector> {
            val viewModel: LeaguesViewModel = koinViewModel()
            LeagueSelectionScreen(
                onContinueToTeamSelection = {
                    navController.navigate(
                        TeamsSelector
                    )
                },
                vm = viewModel
            )
        }

        composable<TeamsSelector> {
            val viewModel: TeamSelectionViewModel = koinViewModel()
            TeamSelectionScreen(
                onSkipAndGoToDashboard = {
                    navController.navigate(
                        Dashboard
                    ) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                vm = viewModel
            )
        }

        composable<Dashboard> {
            val viewModel: DashboardViewModel = koinViewModel()
            DashboardScreen(
                vm = viewModel
            )
        }

    }
}