package io.pdaa.chilenastats.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.pdaa.chilenastats.data.models.local.mockLeagues
import io.pdaa.chilenastats.ui.screens.leagueDetail.LeagueDetailViewModel
import io.pdaa.chilenastats.ui.screens.leagueDetail.LeagueScreen
import io.pdaa.chilenastats.ui.screens.leagues.LeaguesScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            LeaguesScreen(
                onLeagueClick = { league ->
                    navController.navigate("leagues/${league.id}")
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