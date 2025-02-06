package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeaguesViewModel(
    fetchLeaguesUseCase: FetchLeaguesUseCase,
    private val selectLeagueUseCase: SelectLeagueUseCase
) : ViewModel() {

    val leagues: Flow<List<LeagueUi>> = fetchLeaguesUseCase()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val isAnyLeagueSelected: Flow<Boolean>  = leagues
        .map { leagues ->
            leagues.any { it.isFavourite }
        }

    @OptIn(FlowPreview::class)
    val leaguesState: StateFlow<Result<List<LeagueUi>>> = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(leagues) { text, leagues ->
            if(text.isBlank()) {
                leagues
            } else {
                leagues.filter { it.doesMatchSearch(text) }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateAsResultIn(
            viewModelScope
        )


    fun onLeagueSelected(selectedLeague: LeagueUi) {
        viewModelScope.launch {
            selectLeagueUseCase(selectedLeague)
        }
    }

    fun onSearchBarStateChanged(inputText: String) {
        _searchText.value = inputText
    }

}