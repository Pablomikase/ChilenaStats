package io.pdaa.chilenastats.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.framework.models.database.fixture.FixtureContainerDB
import kotlinx.coroutines.flow.Flow

@Dao
interface FixturesDao {

    @Query("SELECT * FROM FixtureContainerDB")
    fun getFixtures(): Flow<List<FixtureContainerDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFixtures(fixtures: List<FixtureContainerDB>)

    @Query(
        """
            SELECT * 
FROM FixtureContainerDB 
WHERE teamOwnerId = :teamId
ORDER BY date DESC
        """
    )
    fun getFixturesByTeam(teamId: Int): Flow<List<FixtureContainerDB>>

}