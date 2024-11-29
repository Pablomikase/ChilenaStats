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

class TeamSelectionViewModel(
    private val teamsRepository : TeamRepository
) : ViewModel() {

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
    fun onTeamSelected(selectedTeam: TeamUi) {
        _state.update { currentState ->
            val updatedIndexTeam = _state.value.teams.indexOfFirst { it.id == selectedTeam.id }
            val updatedTeam = _state.value.teams[updatedIndexTeam].copy(isSelected = true)
            if(_state.value.indexSelectedTeam>=0){
                val updatedPreviousTeam = _state.value.teams[_state.value.indexSelectedTeam].copy(isSelected = false)
                val updatedTeams = _state.value.teams.toMutableList().apply {
                    this[_state.value.indexSelectedTeam] = updatedPreviousTeam
                    this[updatedIndexTeam] = updatedTeam
                }
                currentState.copy(teams = updatedTeams, indexSelectedTeam = updatedIndexTeam)
            }else{
                val updatedTeams = _state.value.teams.toMutableList().apply {
                    this[updatedIndexTeam] = updatedTeam
                }
                currentState.copy(teams = updatedTeams, indexSelectedTeam = updatedIndexTeam)
            }
        }
    }

    fun isAnyTeamsSelected(): Boolean {
        return _state.value.teams.any { it.isSelected }
    }

    fun getSelectedTeamId(): Int {
        return try {
            state.value.teams[_state.value.indexSelectedTeam].id
        }catch (e: Exception){
            -1
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val teams: List<TeamUi> = emptyList(),
        val indexSelectedTeam: Int = -1
    )

}