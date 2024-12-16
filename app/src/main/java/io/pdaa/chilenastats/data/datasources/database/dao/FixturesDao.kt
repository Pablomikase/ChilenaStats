package io.pdaa.chilenastats.data.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.data.models.database.fixture.FixtureContainerDB
import kotlinx.coroutines.flow.Flow

@Dao
interface FixturesDao {

    @Query("SELECT * FROM FixtureContainerDB")
    fun getFixtures(): Flow<List<FixtureContainerDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFixtures(fixtures: List<FixtureContainerDB>)

}