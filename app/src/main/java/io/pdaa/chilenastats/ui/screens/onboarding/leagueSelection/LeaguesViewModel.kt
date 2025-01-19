package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.ifSuccess
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeaguesViewModel(
    fetchLeaguesUseCase: FetchLeaguesUseCase,
    private val selectLeagueUseCase: SelectLeagueUseCase
) : ViewModel() {



    val state: StateFlow<Result<List<LeagueUi>>> = fetchLeaguesUseCase()
        .stateAsResultIn(viewModelScope)

    fun onLeagueSelected(selectedLeague: LeagueUi) {
        viewModelScope.launch {
            selectLeagueUseCase(selectedLeague)
        }
    }

    fun isAnyLeaguesSelected(): Boolean {
        var result = false
        state.value.ifSuccess { leagues ->
            result = leagues.any { it.isFavourite }
        }
        return result
    }

}