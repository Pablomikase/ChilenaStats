package io.pdaa.chilenastats.ui.screens.onboarding.teamSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchTeamsUseCase
import io.pdaa.chilenastats.usecases.SelectTeamUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class TeamSelectionViewModel(
    private val fetchTeamsUseCase: FetchTeamsUseCase,
    private val selectTeamUseCase: SelectTeamUseCase,
) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<TeamUi>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchTeamsUseCase() }
        .stateAsResultIn(viewModelScope)

    fun onUiReady() {
        uiReady.value = true
    }

    fun onTeamSelected(selectedTeam: TeamUi) {
        viewModelScope.launch {
            selectTeamUseCase(selectedTeam)
        }
    }

    fun isAnyTeamsSelected(): Boolean {
        return (state.value as Result.Success).data.any { it.isSelected }
    }
}