package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchTeamsUseCase
import io.pdaa.chilenastats.usecases.SelectTeamUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamSelectionViewModel(
    fetchTeamsUseCase: FetchTeamsUseCase,
    private val selectTeamUseCase: SelectTeamUseCase,
) : ViewModel() {

    val teams = fetchTeamsUseCase()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val isAnyTeamSelected: Flow<Boolean> = teams
        .map { leagues ->
            leagues.any { it.isFavourite }
        }

    @OptIn(FlowPreview::class)
    val teamsState: StateFlow<Result<List<TeamUi>>> = searchText
        .debounce(1000L)
        .onEach {
            _isSearching.update { true }
        }
        .combine(teams) { text, teams ->
            if (text.isBlank()) {
                teams
            } else {
                teams.filter { it.doesMatchSearch(text) }
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
}