package io.pdaa.chilenastats.data.datasources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.pdaa.chilenastats.data.datasources.database.converters.Converters
import io.pdaa.chilenastats.data.datasources.database.dao.CountriesDao
import io.pdaa.chilenastats.data.datasources.database.dao.LeaguesDao
import io.pdaa.chilenastats.data.datasources.database.dao.TeamsDao
import io.pdaa.chilenastats.data.models.database.CountryDB
import io.pdaa.chilenastats.data.models.database.LeagueDB
import io.pdaa.chilenastats.data.models.database.TeamDB
import io.pdaa.chilenastats.data.models.database.VenueDB

@Database(
    entities = [CountryDB::class, LeagueDB::class, TeamDB::class, VenueDB::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FootballDatabase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
    abstract fun leaguesDao(): LeaguesDao
    abstract fun teamsDao(): TeamsDao
}