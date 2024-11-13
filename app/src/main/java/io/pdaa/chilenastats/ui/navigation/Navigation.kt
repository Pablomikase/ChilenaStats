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

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login>{
            LoginScreen (
                continueToOnBoarding = {
                    navController.navigate(CountrySelector)
                },
                continueToDashboard = {}
            )
        }

        composable<CountrySelector>{
            CountrySelectionScreen(
                onContinueToLeagues = { countryCodes ->
                    navController.navigate(LeaguesSelector(countryCodes = countryCodes))
                },
                onSkipAndGoToDashboard = {
                    navController.navigate(Dashboard)
                }
            )
        }


        composable<LeaguesSelector> {backStackEntry ->
            val countries = backStackEntry.toRoute<LeaguesSelector>()
            LeagueSelectionScreen(
                onLeagueClick = { league ->
                    navController.navigate(TeamsSelector( countries = countries.countryCodes ))
                }
            )
        }

        composable<TeamsSelector>{ backStackEntry ->
            val teamsSelector = backStackEntry.toRoute<TeamsSelector>()

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