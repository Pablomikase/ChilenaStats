package io.pdaa.chilenastats.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchFixturesFromFavouriteTeamsUseCase
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel(
    fetchFixturesFromFavouriteTeamsUseCase: FetchFixturesFromFavouriteTeamsUseCase
) : ViewModel() {


    val newState: StateFlow<Result<List<Pair<TeamUi, List<FixtureContainerUi>>>>> =
        fetchFixturesFromFavouriteTeamsUseCase()
            .stateAsResultIn(viewModelScope)

}
