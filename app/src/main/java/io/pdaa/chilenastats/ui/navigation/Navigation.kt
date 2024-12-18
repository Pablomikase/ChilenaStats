package io.pdaa.chilenastats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.pdaa.chilenastats.App
import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.FixturesLocalDataSource
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
import io.pdaa.chilenastats.usecases.FetchFixturesFromFavouriteTeamsUseCase
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.FetchTeamsUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import io.pdaa.chilenastats.usecases.SelectTeamUseCase

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
            regionDataSource = RegionDataSource(
                app = application,
                locationDataSource = LocationDataSource(application)
            ),
            countryLocalDataSource = CountriesLocalDataSource(application.db.countriesDao()),
            countriesRemoteDataSource = CountriesRemoteDataSource()
        )
    }
    val fixturesRepository = remember {
        FixturesRepository(
            remoteDataSource = FixturesRemoteDataSource(),
            teamsLocalDataSource = TeamsLocalDataSource(application.db.teamsDao()),
            fixturesLocalDataSource = FixturesLocalDataSource(application.db.fixturesDao())
        )
    }

    NavHost(navController = navController, startDestination = LeaguesSelector) {
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
                onContinueToLeagues = {
                    navController.navigate(LeaguesSelector)
                }
            )
        }


        composable<LeaguesSelector> {
            LeagueSelectionScreen(
                onContinueToTeamSelection = {
                    navController.navigate(
                        TeamsSelector
                    )
                },
                vm = viewModel {
                    LeaguesViewModel(
                        fetchLeaguesUseCase = FetchLeaguesUseCase(leaguesRepository),
                        selectLeagueUseCase = SelectLeagueUseCase(leaguesRepository)
                    )
                }
            )
        }

        composable<TeamsSelector> {
            TeamSelectionScreen(
                onSkipAndGoToDashboard = {
                    navController.navigate(
                        Dashboard
                    ) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                vm = viewModel {
                    TeamSelectionViewModel(
                        fetchTeamsUseCase = FetchTeamsUseCase(teamsRepository),
                        selectTeamUseCase = SelectTeamUseCase(teamsRepository)
                    )
                }
            )
        }

        composable<Dashboard> {
            DashboardScreen(
                vm = viewModel { DashboardViewModel(
                    fetchFixturesFromFavouriteTeamsUseCase = FetchFixturesFromFavouriteTeamsUseCase(
                        fixturesRepository = fixturesRepository,
                        teamsRepository = teamsRepository
                        )
                ) }
            )
        }

    }
}