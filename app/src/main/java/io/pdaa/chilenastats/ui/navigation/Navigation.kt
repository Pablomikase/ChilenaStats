package io.pdaa.chilenastats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardScreen
import io.pdaa.chilenastats.ui.screens.onboarding.countrySelection.CountrySelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeagueSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.login.LoginScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Dashboard()) {
        composable<Login> {
            LoginScreen(
                continueToOnBoarding = {
                    navController.navigate(CountrySelector) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
            )
        }

        composable<CountrySelector> {
            CountrySelectionScreen(
                onContinueToLeagues = { countryNames ->
                    navController.navigate(LeaguesSelector(countryNames = countryNames))
                }
            )
        }


        composable<LeaguesSelector> { backStackEntry ->
            val countries = backStackEntry.toRoute<LeaguesSelector>()
            LeagueSelectionScreen(
                onContinueToTeamSelection = { leagues ->
                    navController.navigate(
                        TeamsSelector(
                            countries = countries.countryNames,
                            leagueIds = leagues
                        )
                    )
                },
                selectedCountries = countries.countryNames
            )
        }

        composable<TeamsSelector> { backStackEntry ->
            val teamsSelector = backStackEntry.toRoute<TeamsSelector>()
            TeamSelectionScreen(
                countries = teamsSelector.countries,
                leagueIds = teamsSelector.leagueIds,
                onSkipAndGoToDashboard = {
                    navController.navigate(
                        Dashboard()
                    ) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable<Dashboard> { backStackEntry ->
            val dashboardData = backStackEntry.toRoute<Dashboard>()
            DashboardScreen(
                countries = dashboardData.countries,
                leagueIds = dashboardData.leagueIds,
                teamIds = dashboardData.teamIds
            )
        }

    }
}