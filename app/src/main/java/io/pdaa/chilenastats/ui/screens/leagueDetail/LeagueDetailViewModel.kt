package io.pdaa.chilenastats.ui.screens.leagueDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.LeaguesRepository
import io.pdaa.chilenastats.data.models.local.LeagueUi
import kotlinx.coroutines.launch

class LeagueDetailViewModel(leagueId: Int) : ViewModel() {

    private val repository = LeaguesRepository()

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(
                isLoading = false,
                league = repository.fetchLeagueById(leagueId)
            )
        }
    }

    data class UiState(
        val isLoading: Boolean = true,
        val league: LeagueUi? = null
    )

}