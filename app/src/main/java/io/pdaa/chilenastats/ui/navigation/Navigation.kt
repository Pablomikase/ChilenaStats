package io.pdaa.chilenastats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import io.pdaa.chilenastats.ui.screens.leagueDetail.LeagueDetailViewModel
import io.pdaa.chilenastats.ui.screens.leagueDetail.LeagueScreen
import io.pdaa.chilenastats.ui.screens.onboarding.countrySelection.CountrySelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeagueSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.login.LoginScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                continueToOnBoarding = {
                    navController.navigate(CountrySelector)
                },
                continueToDashboard = {
                    navController.navigate(Dashboard)
                }
            )
        }

        composable<CountrySelector> {
            CountrySelectionScreen(
                onContinueToLeagues = { countryNames ->
                    navController.navigate(LeaguesSelector(countryNames = countryNames))
                },
                onSkipAndGoToDashboard = {
                    navController.navigate(Dashboard)
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
                onSkipAndGoToDashboard = {
                    navController.navigate(Dashboard)
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
                    navController.navigate(Dashboard)
                }
            )
        }


        composable(
            route = "leagues/{leagueId}",
            arguments = listOf(navArgument("leagueId") { type = NavType.IntType })
        ) { backStackEntry ->
            val leagueId = requireNotNull(backStackEntry.arguments?.getInt("leagueId"))
            LeagueScreen(
                vm = viewModel { LeagueDetailViewModel(leagueId) },
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}