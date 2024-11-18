package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.models.local.TeamUi
import io.pdaa.chilenastats.data.repositories.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeamSelectionViewModel: ViewModel() {

    private val teamsRepository = TeamRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    fun onUiReady(countries: List<String>, leagueIds: List<Int>) {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            teamsRepository.fetchTeamsByCountryName(countries.first()).let { teams ->
                _state.value = UiState(
                    isLoading = false,
                    teams = teams
                )
            }
        }
    }

    fun onLeagueSelected(selectedTeam: TeamUi) {
        TODO("Not yet implemented")
    }

    data class UiState(
        val isLoading: Boolean = false,
        val teams: List<TeamUi> = emptyList()
    )

}