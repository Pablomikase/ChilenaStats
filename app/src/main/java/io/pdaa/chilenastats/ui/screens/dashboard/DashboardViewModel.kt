package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.models.local.FixtureUi
import io.pdaa.chilenastats.data.repositories.FixturesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel: ViewModel() {

    private val fixturesRepository = FixturesRepository()

    data class UiState (
        val isLoading: Boolean = false,
        val fixtures: List<FixtureUi> = emptyList()
    )

    private val _state = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()

    fun onUiReady(teamId: Int) {
        _state.value = UiState(isLoading = true)
        viewModelScope.launch {
            fixturesRepository.fetchFixturesByTeam(teamId).let { fixtures ->
                _state.value = UiState(
                    isLoading = false,
                    fixtures = fixtures
                )
            }

        }
    }
}
