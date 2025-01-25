package io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.ifSuccess
import io.pdaa.chilenastats.stateAsResultIn
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeaguesViewModel(
    fetchLeaguesUseCase: FetchLeaguesUseCase,
    private val selectLeagueUseCase: SelectLeagueUseCase
) : ViewModel() {



    val leaguesState: StateFlow<Result<List<LeagueUi>>> = fetchLeaguesUseCase()
        .stateAsResultIn(viewModelScope)

    val leagues = fetchLeaguesUseCase()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    @OptIn(FlowPreview::class)
    val newState: StateFlow<Result<List<LeagueUi>>> = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(leagues) { text, leagues ->
            if(text.isBlank()) {
                leagues
            } else {
                leagues.filter { it.name.contains(text, ignoreCase = true) }
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

    fun isAnyLeaguesSelected(): Boolean {
        var result = false
        leaguesState.value.ifSuccess { leagues ->
            result = leagues.any { it.isFavourite }
        }
        return result
    }

    fun onSearchBarStateChanged(inputText: String) {
        _searchText.value = inputText
    }

}