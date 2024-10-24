package io.pdaa.chilenastats.ui.screens.leagues

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.LeaguesRepository
import io.pdaa.chilenastats.data.models.local.LeagueUi
import kotlinx.coroutines.launch

class LeaguesViewModel: ViewModel() {

    private val leaguesRepository = LeaguesRepository()

    var state by mutableStateOf(UiState())
        private set

    init {
        Log.i("LeaguesViewModel", "init")
    }

    fun onUiReady(countryCode: String){
        viewModelScope.launch {
            state = UiState(isLoading = true)
            state = UiState(isLoading = false, leagues = leaguesRepository.fetchLeaguesByCountry(countryCode = countryCode))
        }
    }


    data class UiState(
        val leagues: List<LeagueUi> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
    )

}