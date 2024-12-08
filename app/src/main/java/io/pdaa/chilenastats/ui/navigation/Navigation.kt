package io.pdaa.chilenastats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.pdaa.chilenastats.App
import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.FixturesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.LocationDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
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
    val application = LocalContext.current.applicationContext as App
    val countriesRepository = remember {
        CountriesRepository(
            regionDataSource = RegionDataSource(
                app = application,
                locationDataSource = LocationDataSource(application)
            ),
            remoteDataSource = CountriesRemoteDataSource(),
            localDataSource = CountriesLocalDataSource(application.db.countriesDao())
        )
    }

    val leaguesRepository = remember {
        LeaguesRepository(
            remoteDataSource = LeaguesRemoteDataSource(),
            localDataSource = LeaguesLocalDataSource(application.db.leaguesDao())
        )
    }

    val teamsRepository = remember {
        TeamRepository(
            remoteDataSource = TeamsRemoteDataSource(),
            localDataSource = TeamsLocalDataSource(application.db.teamsDao()),
            countryRepository = CountriesLocalDataSource(application.db.countriesDao())
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
                onSkipAndGoToDashboard = { selectedTeamId: Int ->
                    navController.navigate(
                        Dashboard(
                            countryCode = teamsSelector.countries.first(),
                            leagueIds = teamsSelector.leagueIds,
                            teamId = selectedTeamId
                        )
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
                countryCode = dashboardData.countryCode,
                leagueIds = dashboardData.leagueIds,
                teamId = dashboardData.teamId,
                vm = viewModel { DashboardViewModel(fixturesRepository) }
            )
        }

    }
}