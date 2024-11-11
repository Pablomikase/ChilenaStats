package io.pdaa.chilenastats.ui.screens.onboarding.countrySelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.countries.CountriesRepository
import io.pdaa.chilenastats.data.models.local.CountryUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountrySelectionViewModel : ViewModel() {

    private val countriesRepository = CountriesRepository()

    data class UiState(
        val isLoading: Boolean = false,
        val countries: List<CountryUi> = emptyList(),
    )

    private val _state = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()

    fun onUiReady(countryCode: String) {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            val countriesList = countriesRepository.fetchCountries().toMutableList().apply {
                find { it.code == countryCode }?.let {
                    it.isSelected = true
                    remove(it)
                    add(0, it)
                }
            }.toList()
            _state.value = UiState(
                isLoading = false,
                countries = countriesList
            )
        }
    }

}