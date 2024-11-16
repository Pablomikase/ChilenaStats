package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.LeaguesRepository
import io.pdaa.chilenastats.data.models.local.LeagueUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeaguesViewModel : ViewModel() {

    private val leaguesRepository = LeaguesRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    init {
        Log.d("LeaguesViewModel", "init")
    }

    fun onUiReady(countryCode: List<String>) {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            _state.value = UiState(
                isLoading = false,
                leagues = leaguesRepository.fetchLeaguesByCountry(countryCode = "ES")
            )
        }
    }

    fun onLeagueSelected(selectedLeague: LeagueUi) {
        _state.update { currentState ->
            Log.d("LeaguesViewModel", "Before update: $currentState")
            val newState = currentState.copy(leagues = currentState.leagues.map {
                if (it.id == selectedLeague.id) {
                    it.copy(isSelected = !it.isSelected)
                } else {
                    it
                }
            })
            Log.d("LeaguesViewModel", "After update: $newState")
            newState
        }
    }


    data class UiState(
        val leagues: List<LeagueUi> = emptyList(),
        val isLoading: Boolean = false,
    )

}