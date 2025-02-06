package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.domain.TeamUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TeamRepository(
    private val remoteDataSource: TeamsRemoteDataSource,
    private val localDataSource: TeamsLocalDataSource,
    private val leaguesRepository: LeaguesRepository,
    private val countryRepository: CountriesRepository,
) {

    val teams: Flow<List<TeamUi>>
        get() = localDataSource.teams

    suspend fun selectTeam(selectedTeam: TeamUi) {
        localDataSource.insertTeams(
            listOf(
                selectedTeam.copy(isFavourite = selectedTeam.isFavourite.not())
            ),
        )
    }

    val areLocalTeamsEmpty = localDataSource.isEmpty

    fun saveTeamsFromFavouriteLeagues(lifeCycleScope : CoroutineScope) {
        leaguesRepository.leagues.onEach { leagues ->
            leagues.filter { it.isFavourite }.forEach { favouriteLeague ->
                val teams = remoteDataSource.fetchTeamsByLeagueId(favouriteLeague.id, 2022)
                localDataSource.insertTeams(teams)
            }
        }.launchIn(lifeCycleScope)
    }

    fun saveTeamsFromUserCountry(lifeCycleScope: CoroutineScope) {
        countryRepository.countries.onEach { countries ->
            val selectedCountry = countries.find { it.isSelected }
            selectedCountry?.let { country ->
                val teams = remoteDataSource.fetchTeamsByCountryName(country.name)
                localDataSource.insertTeams(teams)
            }
        }.launchIn(lifeCycleScope)
    }

    suspend fun saveTeamsFromSearchRequest(query: String) {
        val teams = remoteDataSource.fetchTeamsByQuery(query)
        localDataSource.insertTeams(teams)
    }

}