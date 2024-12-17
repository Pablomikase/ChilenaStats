package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class LeaguesViewModel(
    private val fetchLeaguesUseCase: FetchLeaguesUseCase,
    private val selectLeagueUseCase: SelectLeagueUseCase,
) : ViewModel() {


    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<io.pdaa.chilenastats.domain.LeagueUi>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchLeaguesUseCase() }
        .stateAsResultIn(viewModelScope)


    fun onUiReady() {
        uiReady.value = true
    }

    fun onLeagueSelected(selectedLeague: io.pdaa.chilenastats.domain.LeagueUi) {
        viewModelScope.launch {
            selectLeagueUseCase(selectedLeague)
        }

    }

    fun isAnyLeaguesSelected(): Boolean {
        return (state.value as Result.Success).data.any { it.isSelected }
    }

}