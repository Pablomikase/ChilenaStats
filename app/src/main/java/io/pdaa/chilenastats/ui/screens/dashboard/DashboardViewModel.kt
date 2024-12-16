package io.pdaa.chilenastats.ui.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.repositories.FixturesRepository
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val fixturesRepository: FixturesRepository
) : ViewModel() {
    
    data class UiState(
        val isLoading: Boolean = false,
        val fixtures: List<FixtureContainerUi> = emptyList()
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    fun onUiReady() {
        _state.value = UiState(isLoading = true)
        viewModelScope.launch {
            fixturesRepository.fixturesFavouriteTeam.collect{fixtures ->
                Log.i("DashboardViewModel", "onUiReady - CollectingState: $fixtures")
                _state.value = UiState(
                    isLoading = false,
                    fixtures = fixtures
                )
            }
        }
    }
}
