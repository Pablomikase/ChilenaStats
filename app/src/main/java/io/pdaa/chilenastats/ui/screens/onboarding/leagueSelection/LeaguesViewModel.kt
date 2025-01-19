package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import io.pdaa.chilenastats.usecases.UserIsLoggedInUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeaguesViewModel(
    private val fetchLeaguesUseCase: FetchLeaguesUseCase,
    private val selectLeagueUseCase: SelectLeagueUseCase,
    private val userIsLoggedInUseCase: UserIsLoggedInUseCase
) : ViewModel() {



    val state: StateFlow<Result<List<LeagueUi>>> = fetchLeaguesUseCase()
        .stateAsResultIn(viewModelScope)

    fun onLeagueSelected(selectedLeague: LeagueUi) {
        viewModelScope.launch {
            selectLeagueUseCase(selectedLeague)
        }
    }

    fun isAnyLeaguesSelected(): Boolean {
        return (state.value as Result.Success).data.any { it.isFavourite }
    }

}