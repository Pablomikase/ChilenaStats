package io.pdaa.chilenastats.data.datasources.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.data.models.database.LeagueDB

@Dao
interface LeaguesDao {

    @Query("SELECT * FROM LeagueDB")
    suspend fun getLeagues(): List<LeagueDB>

    @Query("SELECT COUNT(id) FROM LeagueDB")
    suspend fun countLeagues(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagues(leagues: List<LeagueDB>)

}