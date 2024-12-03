package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.CountriesDao
import io.pdaa.chilenastats.data.models.database.CountryDB
import kotlinx.coroutines.flow.Flow

class CountriesLocalDataSource(private val countriesDao: CountriesDao) {

    val countries : Flow<List<CountryDB>> = countriesDao.getCountries()

    suspend fun insertCountries(countries: List<CountryDB>) = countriesDao.insertCountries(countries)

}