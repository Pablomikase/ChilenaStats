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

    fun onUiReady(countryCode: String) {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            _state.value = UiState(
                isLoading = false,
                leagues = leaguesRepository.fetchLeaguesByCountry(countryCode = countryCode)
            )
        }
    }

    fun onLeagueSelected(selectedLeague: LeagueUi) {
        _state.update { currentState ->
            /*val index = _state.value.leagues.indexOfFirst { it.id == selectedLeague.id }
            if (index != -1) {
                val updatedLeague =
                    _state.value.leagues[index].copy(isSelected = !_state.value.leagues[index].isSelected)
                val updatedLeagues = _state.value.leagues.toMutableList().apply {
                    this[index] = updatedLeague
                }
                currentState.copy(leagues = updatedLeagues, version = currentState.version + 1)
            } else {
                currentState.copy(leagues = currentState.leagues)
            }*/
            Log.d("LeaguesViewModel", "Before update: $currentState")
            val newState = currentState.copy(leagues = currentState.leagues.map {
                if (it.id == selectedLeague.id) {
                    it.copy(isSelected = !it.isSelected)
                } else {
                    it
                }
            }, version = currentState.version + 1)
            Log.d("LeaguesViewModel", "After update: $newState")
            newState
        }
    }


    data class UiState(
        val leagues: List<LeagueUi> = emptyList(),
        val isLoading: Boolean = false,
        val version: Int = 0
    )

}