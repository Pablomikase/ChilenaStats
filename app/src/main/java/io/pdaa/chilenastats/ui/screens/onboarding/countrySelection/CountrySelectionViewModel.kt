package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.Result
import io.pdaa.chilenastats.data.repositories.CountriesRepository
import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.stateAsResultIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class CountrySelectionViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<CountryUi>>> = uiReady
        .filter { it }
        .flatMapLatest { countriesRepository.countries }
        .stateAsResultIn(viewModelScope)


    fun onUiReady() {
        uiReady.value = true
    }

    fun onCountrySelected(selectedCountry: CountryUi) {
        viewModelScope.launch {
            countriesRepository.selectCountry(selectedCountry)
        }
    }

    fun filterSelectedCountries(): List<String> {
        return (state.value as Result.Success<List<CountryUi>>).data.filter { it.isSelected }.map { it.name }
    }

    fun isAnyCountrySelected(): Boolean {
        return (state.value as Result.Success<List<CountryUi>>).data.any { it.isSelected }
    }

}