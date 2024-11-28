package io.pdaa.chilenastats.data.datasources.local

import io.pdaa.chilenastats.data.datasources.database.dao.CountriesDao
import io.pdaa.chilenastats.data.models.database.CountryDB

class CountriesLocalDataSource(private val countriesDao: CountriesDao) {

    suspend fun getCountries() = countriesDao.getCountries()

    suspend fun insertCountries(countries: List<CountryDB>) = countriesDao.insertCountries(countries)

    suspend fun isCountriesEmpty() = countriesDao.countCountries() == 0

}