package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.models.local.LeagueUi
import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeaguesViewModel(private val leaguesRepository: LeaguesRepository) : ViewModel() {


    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    fun onUiReady(countryNames: List<String>) {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            val allLeagues = leaguesRepository.fetchLeagues().toMutableList()
            allLeagues.apply {
                countryNames.forEach { selectedCountryNames ->
                    this.find { it.country.name == selectedCountryNames }?.let {
                        remove(it)
                        add(0, it)
                    }
                }
            }.toList()

            _state.value = UiState(
                isLoading = false,
                leagues = allLeagues
            )
        }
    }

    fun onLeagueSelected(selectedLeague: LeagueUi) {
        _state.update { currentState ->
            val newState = currentState.copy(leagues = currentState.leagues.map {
                if (it.id == selectedLeague.id) {
                    it.copy(isSelected = !it.isSelected)
                } else {
                    it
                }
            })
            newState
        }
    }

    fun filterSelectedLeagues(): List<Int> {
        return _state.value.leagues.filter { it.isSelected }.map { it.id }
    }

    fun isAnyLeaguesSelected(): Boolean {
        return _state.value.leagues.any { it.isSelected }
    }


    data class UiState(
        val leagues: List<LeagueUi> = emptyList(),
        val isLoading: Boolean = false,
    )

}