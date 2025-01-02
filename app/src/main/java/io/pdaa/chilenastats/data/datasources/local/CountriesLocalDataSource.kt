package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.CountriesDao
import io.pdaa.chilenastats.data.models.database.CountryDB
import kotlinx.coroutines.flow.Flow

interface CountriesLocalDataSource {
    val countries : Flow<List<CountryDB>>
    val userCountry: Flow<CountryDB>

    suspend fun insertCountries(countries: List<CountryDB>)
}

class CountriesRoomDataSource(private val countriesDao: CountriesDao) : CountriesLocalDataSource {

    override val countries : Flow<List<CountryDB>> = countriesDao.getCountries()

    override val userCountry: Flow<CountryDB> = countriesDao.getUserCountry()

    override suspend fun insertCountries(countries: List<CountryDB>) = countriesDao.insertCountries(countries)

}