package io.pdaa.chilenastats.ui.navigation

import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import io.pdaa.chilenastats.App
import io.pdaa.chilenastats.data.repositories.CountriesRepository
import io.pdaa.chilenastats.data.repositories.FixturesRepository
import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.framework.datasourcesImpl.local.CountriesRoomDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.local.FixturesRoomDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.local.LeaguesRoomDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.local.TeamsRoomDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.remote.CountriesServerDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.remote.FixturesServerDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.remote.LeaguesServerDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.remote.TeamsServerDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.sensors.GeocoderRegionSource
import io.pdaa.chilenastats.framework.datasourcesImpl.sensors.PlayServicesLocationDataSource
import io.pdaa.chilenastats.framework.server.FreeFootballDataClient
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
import io.pdaa.chilenastats.usecases.UserIsLoggedInUseCase

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as App
    val countriesRepository = remember {
        CountriesRepository(
            regionDataSource = GeocoderRegionSource(
                geocoder = Geocoder(application),
                locationDataSource = PlayServicesLocationDataSource(LocationServices.getFusedLocationProviderClient(application))
            ),
            remoteDataSource = CountriesServerDataSource(FreeFootballDataClient.instance),
            localDataSource = CountriesRoomDataSource(application.db.countriesDao())
        )
    }

    val leaguesRepository = remember {
        LeaguesRepository(
            remoteDataSource = LeaguesServerDataSource(FreeFootballDataClient.instance),
            localDataSource = LeaguesRoomDataSource(application.db.leaguesDao())
        )
    }

    val teamsRepository = remember {
        TeamRepository(
            remoteDataSource = TeamsServerDataSource(FreeFootballDataClient.instance),
            localDataSource = TeamsRoomDataSource(application.db.teamsDao()),
            regionDataSource = GeocoderRegionSource(
                geocoder = Geocoder(application),
                locationDataSource = PlayServicesLocationDataSource(LocationServices.getFusedLocationProviderClient(application))
            ),
            countryLocalDataSource = CountriesRoomDataSource(application.db.countriesDao()),
            countriesRemoteDataSource = CountriesServerDataSource(FreeFootballDataClient.instance)
        )
    }
    val fixturesRepository = remember {
        FixturesRepository(
            remoteDataSource = FixturesServerDataSource(FreeFootballDataClient.instance),
            teamsLocalDataSource = TeamsRoomDataSource(application.db.teamsDao()),
            fixturesLocalDataSource = FixturesRoomDataSource(application.db.fixturesDao())
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
                onContinueToDashboard = {
                    navController.navigate(
                        Dashboard
                    ) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                vm = viewModel {
                    LeaguesViewModel(
                        fetchLeaguesUseCase = FetchLeaguesUseCase(
                            leaguesRepository
                        ),
                        selectLeagueUseCase = SelectLeagueUseCase(
                            leaguesRepository
                        ),
                        userIsLoggedInUseCase = UserIsLoggedInUseCase(
                            teamsRepository
                        )
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
                        fetchTeamsUseCase = FetchTeamsUseCase(
                            teamsRepository
                        ),
                        selectTeamUseCase = SelectTeamUseCase(
                            teamsRepository
                        )
                    )
                }
            )
        }

        composable<Dashboard> {
            DashboardScreen(
                vm = viewModel {
                    DashboardViewModel(
                        fetchFixturesFromFavouriteTeamsUseCase = FetchFixturesFromFavouriteTeamsUseCase(
                            fixturesRepository = fixturesRepository,
                            teamsRepository = teamsRepository
                        )
                    )
                }
            )
        }

    }
}