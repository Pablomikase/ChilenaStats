package io.pdaa.chilenastats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardScreen
import io.pdaa.chilenastats.ui.screens.dashboard.DashboardViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.countrySelection.CountrySelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.countrySelection.CountrySelectionViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeagueSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeaguesViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.login.LoginScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionScreen
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    /*val application = LocalContext.current.applicationContext as App
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
    }*/

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
            val viewModel: CountrySelectionViewModel = koinViewModel()
            CountrySelectionScreen(
                vm = viewModel,
                onContinueToLeagues = {
                    navController.navigate(LeaguesSelector)
                }
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
                onContinueToDashboard = {
                    navController.navigate(
                        Dashboard
                    ) {
                        popUpTo(0) { inclusive = true }
                    }
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