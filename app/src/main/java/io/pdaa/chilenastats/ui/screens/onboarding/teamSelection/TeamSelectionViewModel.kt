package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.SelectTeamUseCase
import io.pdaa.chilenastats.usecases.teams.FetchTeamsUseCase
import io.pdaa.chilenastats.usecases.teams.SaveTeamsFromFavouriteLeaguesUseCase
import io.pdaa.chilenastats.usecases.teams.SaveTeamsFromUserCountryUseCase
import io.pdaa.chilenastats.usecases.teams.SearchTeamUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamSelectionViewModel(
    fetchTeamsUseCase: FetchTeamsUseCase,
    private val saveTeamsFromFavouriteLeaguesUseCase: SaveTeamsFromFavouriteLeaguesUseCase,
    private val saveTeamsFromUserCountryUseCase: SaveTeamsFromUserCountryUseCase,
    private val selectTeamUseCase: SelectTeamUseCase,
    private val searchTeamUseCase: SearchTeamUseCase
) : ViewModel() {

    private val uiReady: MutableStateFlow<Boolean> = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val teams: Flow<List<TeamUi>> =
        uiReady
            .flatMapLatest { fetchTeamsUseCase() }


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val isAnyTeamSelected: Flow<Boolean> = teams
        .map { leagues ->
            leagues.any { it.isFavourite }
        }

    private val queriedTeams = mutableListOf<String>()

    @OptIn(FlowPreview::class)
    val teamsState: StateFlow<Result<List<TeamUi>>> =
        searchText
            .debounce(1000L)
            .onEach {
                _isSearching.update { true }
            }
            .combine(teams) { text, teams ->
                if (text.isBlank()) {
                    teams
                } else {
                    teams.filter { it.doesMatchSearch(text) }.also {
                        if(it.isEmpty()) {

                            if(!queriedTeams.contains(text)) {
                                Log.i("TeamSelectionViewModel", "Searching in remote for: $text")
                                queriedTeams.add(text)
                                searchTeamUseCase(text)
                            }
                        }
                    }
                }
            }
            .onEach {
                _isSearching.update { false }
            }
            .stateAsResultIn(viewModelScope)

    fun onTeamSelected(selectedTeam: TeamUi) {
        viewModelScope.launch {
            selectTeamUseCase(selectedTeam)
        }
    }

    fun onSearchBarStateChanged(searchText: String) {
        _searchText.value = searchText
    }

    fun onLocationPermissionConceded() {
        saveTeamsFromUserCountryUseCase(viewModelScope)
    }

    fun onUiReady() {
        saveTeamsFromFavouriteLeaguesUseCase(viewModelScope)
        uiReady.value = true
    }
}