package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.models.local.TeamUi
import io.pdaa.chilenastats.data.repositories.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamSelectionViewModel : ViewModel() {

    private val teamsRepository = TeamRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    fun onUiReady(countries: List<String>, leagueIds: List<Int>) {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            val teams = countries.flatMap { country ->
                teamsRepository.fetchTeamsByCountryName(country)
            }
            _state.value = UiState(isLoading = false, teams = teams)
        }
    }
    fun onLeagueSelected(selectedTeam: TeamUi) {
        _state.update { currentState ->
            val index = _state.value.teams.indexOfFirst { it.id == selectedTeam.id }
            if (index != -1) {
                val updatedTeam =
                    _state.value.teams[index].copy(isSelected = !_state.value.teams[index].isSelected)
                val updatedTeams = _state.value.teams.toMutableList().apply {
                    this[index] = updatedTeam
                }
                currentState.copy(teams = updatedTeams)
            } else {
                currentState
            }
        }
    }

    fun isAnyTeamsSelected(): Boolean {
        return _state.value.teams.any { it.isSelected }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val teams: List<TeamUi> = emptyList()
    )

}