package io.pdaa.chilenastats.data.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.data.models.database.CountryDB

@Dao
interface CountriesDao {

    @Query("SELECT * FROM CountryDB")
    suspend fun getCountries(): List<CountryDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryDB>)

    @Query("SELECT COUNT(countryName) FROM CountryDB")
    suspend fun countCountries(): Int

    // @Query("UPDATE CountryDB SET isSelected = :isSelected WHERE code = :code")
    // suspend fun updateCountry(code: String, isSelected: Boolean)

    // @Query("SELECT * FROM CountryDB WHERE isSelected = 1")
    // suspend fun getSelectedCountries(): List<CountryDB>

    // @Query("SELECT * FROM CountryDB WHERE code = :code")
    // suspend fun getCountry(code: String): CountryDB

    // @Query("SELECT * FROM CountryDB WHERE name = :name")
    // suspend fun getCountryByName(name: String): CountryDB

    // @Query("DELETE FROM CountryDB")
    // suspend fun deleteAll()

    // @Query("SELECT * FROM CountryDB WHERE code = :code")
    // suspend fun getCountryByCode(code: String): CountryDB

    // @Query("SELECT * FROM CountryDB WHERE isSelected = 1")
    // suspend fun getSelectedCountries(): List<CountryDB>

    // @Query("SELECT * FROM CountryDB WHERE code = :code")
    // suspend fun getCountryByCode(code: String): CountryDB

    // @Query("SELECT * FROM CountryDB WHERE isSelected = 1")
    // suspend fun getSelectedCountries(): List<CountryDB>

    // @Query("SELECT * FROM CountryDB WHERE code = :code")
    // suspend fun getCountryByCode(code: String): CountryDB

    // @Query("SELECT * FROM CountryDB WHERE isSelected = 1")
    // suspend fun getSelectedCountries(): List<CountryDB>

    // @Query("SELECT * FROM CountryDB WHERE code = :code")
    // suspend fun getCountryByCode(code: String): CountryDB

    // @Query("SELECT * FROM CountryDB WHERE isSelected = 1")
    // suspend fun getSelectedCountries(): List<CountryDB>

    // @Query("SELECT * FROM CountryDB WHERE code = :code")
    // suspend fun getCountryByCode(code: String): CountryDB

    // @Query("SELECT * FROM CountryDB WHERE isSelected = 1")
    // suspend fun getSelectedCountries(): List<CountryDB>

}