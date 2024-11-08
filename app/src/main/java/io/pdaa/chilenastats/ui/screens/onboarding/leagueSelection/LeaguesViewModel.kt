package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.LeaguesRepository
import io.pdaa.chilenastats.data.models.local.LeagueUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaguesViewModel: ViewModel() {

    private val leaguesRepository = LeaguesRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()


    fun onUiReady(countryCode: String){
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            _state.value = UiState(isLoading = false, leagues = leaguesRepository.fetchLeaguesByCountry(countryCode = countryCode))
        }
    }


    data class UiState(
        val leagues: List<LeagueUi> = emptyList(),
        val isLoading: Boolean = false
    )

}