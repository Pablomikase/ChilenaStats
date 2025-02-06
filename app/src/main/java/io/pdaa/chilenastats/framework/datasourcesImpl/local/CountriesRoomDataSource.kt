package io.pdaa.chilenastats.framework.datasourcesImpl.local

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.framework.models.database.CountryDB
import io.pdaa.chilenastats.framework.database.dao.CountriesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CountriesRoomDataSource(private val countriesDao: CountriesDao) : CountriesLocalDataSource {

    override val countries : Flow<List<CountryUi>> = countriesDao.getCountries().map { it.asUiModel() }

    override val userCountry: Flow<CountryUi> = countriesDao.getUserCountry().map { it.asUiModel() }

    override suspend fun insertCountries(countries: List<CountryUi>) = countriesDao.insertCountries(countries.asDbModel())

}

private fun CountryUi.asDbModel():CountryDB = CountryDB(
    countryName = name,
    countryCode = code,
    countryFlag = flag,
    countryIsSelected = isSelected
)

private fun List<CountryUi>.asDbModel(): List<CountryDB> =
    this.map { it.asDbModel() }

private fun List<CountryDB>.asUiModel():List<CountryUi> = this.map { it.asUiModel() }

fun CountryDB.asUiModel() = CountryUi(
    code = countryCode,
    flag = countryFlag,
    name = countryName,
    isSelected = countryIsSelected
)