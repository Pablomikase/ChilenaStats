package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import io.pdaa.chilenastats.stateAsResultIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class LeaguesViewModel(private val leaguesRepository: LeaguesRepository) : ViewModel() {


    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<io.pdaa.chilenastats.domain.LeagueUi>>> = uiReady
        .filter { it }
        .flatMapLatest { leaguesRepository.leagues }
        .stateAsResultIn(viewModelScope)


    fun onUiReady(countryNames: List<String>) {
        uiReady.value = true

    }

    fun onLeagueSelected(selectedLeague: io.pdaa.chilenastats.domain.LeagueUi) {

        viewModelScope.launch {
            leaguesRepository.selectLeague(selectedLeague)
        }

    }

    fun filterSelectedLeagues(): List<Int> {

        return (state.value as Result.Success).data.filter { it.isSelected }.map { it.id }
    }

    fun isAnyLeaguesSelected(): Boolean {
        return (state.value as Result.Success).data.any { it.isSelected }
    }

}