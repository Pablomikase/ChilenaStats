package io.pdaa.chilenastats.data.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.data.models.database.TeamDB
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamsDao {

    @Query("SELECT * FROM TeamDB")
    fun getTeams(): Flow<List<TeamDB>>

    @Query("SELECT COUNT(id) FROM TeamDB")
    fun countTeams(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamDB>)

}