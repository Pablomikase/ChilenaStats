package io.pdaa.chilenastats.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.pdaa.chilenastats.data.datasources.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.FixturesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.LeaguesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.LocationDataSource
import io.pdaa.chilenastats.data.datasources.RegionDataSource
import io.pdaa.chilenastats.data.datasources.TeamsRemoteDataSource
import io.pdaa.chilenastats.data.repositories.CountriesRepository
import io.pdaa.chilenastats.data.repositories.FixturesRepository
import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardScreen
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.countrySelection.CountrySelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.countrySelection.CountrySelectionViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeagueSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeaguesViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.login.LoginScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as Application
    val countriesRepository = remember {
        CountriesRepository(
            regionDataSource = RegionDataSource(
                app = application,
                locationDataSource = LocationDataSource(application)
            ),
            remoteDataSource = CountriesRemoteDataSource()
        )
    }

    val leaguesRepository = remember {
        LeaguesRepository(
            remoteDataSource = LeaguesRemoteDataSource()
        )
    }

    val teamsRepository = remember {
        TeamRepository(
            remoteDataSource = TeamsRemoteDataSource()
        )
    }
    val fixturesRepository = remember {
        FixturesRepository(
            remoteDataSource = FixturesRemoteDataSource()
        )
    }

    NavHost(navController = navController, startDestination = Login) {
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
                vm = viewModel { CountrySelectionViewModel(countriesRepository) },
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
                selectedCountries = countries.countryNames,
                vm = viewModel { LeaguesViewModel(leaguesRepository) }
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
                },
                vm = viewModel { TeamSelectionViewModel(teamsRepository) }
            )
        }

        composable<Dashboard> { backStackEntry ->
            val dashboardData = backStackEntry.toRoute<Dashboard>()
            DashboardScreen(
                countries = dashboardData.countries,
                leagueIds = dashboardData.leagueIds,
                teamIds = dashboardData.teamIds,
                vm = viewModel { DashboardViewModel(fixturesRepository) }
            )
        }

    }
}