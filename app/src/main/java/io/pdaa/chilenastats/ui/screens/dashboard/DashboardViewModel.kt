package io.pdaa.chilenastats.ui.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchFixturesFromFavouriteTeamsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class DashboardViewModel(
    private val fetchFixturesFromFavouriteTeamsUseCase: FetchFixturesFromFavouriteTeamsUseCase
) : ViewModel() {


    private val uiReady = MutableStateFlow(false)
    @OptIn(ExperimentalCoroutinesApi::class)
    val newState : StateFlow<Result<List<Pair<TeamUi, List<FixtureContainerUi>>>>> = uiReady
        .filter { it }
        .flatMapLatest {
            fetchFixturesFromFavouriteTeamsUseCase()
        }
        .stateAsResultIn(viewModelScope)

    fun onUiReady() {
        uiReady.value = true
    }

    fun testingVisibility(){
        Log.i("DashboardViewModel", "testingVisibility: ")
    }
}
