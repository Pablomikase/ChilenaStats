package io.pdaa.chilenastats.framework.datasourcesImpl.local

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.framework.models.database.CountryDB
import io.pdaa.chilenastats.framework.database.dao.CountriesDao
import kotlinx.coroutines.flow.Flow

class CountriesRoomDataSource(private val countriesDao: CountriesDao) : CountriesLocalDataSource {

    override val countries : Flow<List<CountryDB>> = countriesDao.getCountries()

    override val userCountry: Flow<CountryDB> = countriesDao.getUserCountry()

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