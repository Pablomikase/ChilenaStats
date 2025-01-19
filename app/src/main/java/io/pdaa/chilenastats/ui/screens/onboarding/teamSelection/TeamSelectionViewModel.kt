package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.ifSuccess
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchTeamsUseCase
import io.pdaa.chilenastats.usecases.SelectTeamUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeamSelectionViewModel(
    fetchTeamsUseCase: FetchTeamsUseCase,
    private val selectTeamUseCase: SelectTeamUseCase,
) : ViewModel() {

    val state: StateFlow<Result<List<TeamUi>>> = fetchTeamsUseCase()
        .stateAsResultIn(viewModelScope)

    fun onTeamSelected(selectedTeam: TeamUi) {
        viewModelScope.launch {
            selectTeamUseCase(selectedTeam)
        }
    }

    fun isAnyTeamsSelected(): Boolean {
        var result = false
        state.value.ifSuccess { teams ->
            result = teams.any { it.isSelected }
        }
        return result
    }
}