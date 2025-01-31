package io.pdaa.chilenastats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardScreen
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeagueSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeaguesViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.login.LoginScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                continueToOnBoarding = {
                    navController.navigate(LeaguesSelector) {
                        popUpTo(Login) { inclusive = true }
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