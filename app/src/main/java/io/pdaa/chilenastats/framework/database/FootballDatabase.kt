package io.pdaa.chilenastats.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.pdaa.chilenastats.framework.database.converters.Converters
import io.pdaa.chilenastats.framework.database.dao.CountriesDao
import io.pdaa.chilenastats.framework.database.dao.FixturesDao
import io.pdaa.chilenastats.framework.database.dao.LeaguesDao
import io.pdaa.chilenastats.framework.database.dao.TeamsDao
import io.pdaa.chilenastats.framework.models.database.CountryDB
import io.pdaa.chilenastats.framework.models.database.LeagueDB
import io.pdaa.chilenastats.framework.models.database.TeamDB
import io.pdaa.chilenastats.framework.models.database.fixture.FixtureContainerDB

@Database(
    entities = [CountryDB::class, LeagueDB::class, TeamDB::class, FixtureContainerDB::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FootballDatabase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
    abstract fun leaguesDao(): LeaguesDao
    abstract fun teamsDao(): TeamsDao
    abstract fun fixturesDao(): FixturesDao
}