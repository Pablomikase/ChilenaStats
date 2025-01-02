package io.pdaa.chilenastats.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.framework.models.database.TeamDB
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamsDao {

    @Query("SELECT * FROM TeamDB")
    fun getTeams(): Flow<List<TeamDB>>

    @Query("SELECT COUNT(id) FROM TeamDB")
    fun countTeams(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamDB>)

    @Query("SELECT * FROM TeamDB WHERE isSelected = 1")
    fun getFavoriteTeams(): Flow<List<TeamDB>>

}