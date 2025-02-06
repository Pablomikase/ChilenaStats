package io.pdaa.chilenastats.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.framework.models.database.CountryDB
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {

    @Query("SELECT * FROM CountryDB")
    fun getCountries(): Flow<List<CountryDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryDB>)

    @Query("SELECT COUNT(countryName) FROM CountryDB")
    fun countCountries(): Flow<Int>

    @Query("SELECT * FROM CountryDB WHERE countryIsSelected = 1")
    fun getUserCountry(): Flow<CountryDB>

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