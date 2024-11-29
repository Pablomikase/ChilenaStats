package io.pdaa.chilenastats.data.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.data.models.database.TeamDB

@Dao
interface TeamsDao {

    @Query("SELECT * FROM TeamDB")
    suspend fun getTeams(): List<TeamDB>

    @Query("SELECT COUNT(id) FROM TeamDB")
    suspend fun countTeams(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamDB>)

}