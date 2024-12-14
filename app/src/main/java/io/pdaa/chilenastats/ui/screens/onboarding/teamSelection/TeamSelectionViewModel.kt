package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.data.repositories.TeamRepository
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.stateAsResultIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class TeamSelectionViewModel(
    private val teamsRepository: TeamRepository
) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<TeamUi>>> = uiReady
        .filter { it }
        .flatMapLatest { teamsRepository.teams }
        .stateAsResultIn(viewModelScope)

    fun onUiReady() {
        uiReady.value = true
    }

    fun onTeamSelected(selectedTeam: TeamUi) {
        viewModelScope.launch {
            teamsRepository.selectTeam(selectedTeam)
        }
    }

    fun isAnyTeamsSelected(): Boolean {
        return (state.value as Result.Success).data.any { it.isSelected }
    }

    fun getSelectedTeamId(): Int {
        return (state.value as Result.Success).data.first { it.isSelected }.id

    }
}