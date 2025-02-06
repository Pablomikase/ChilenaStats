package io.pdaa.chilenastats.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.pdaa.chilenastats.framework.models.database.LeagueDB
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaguesDao {

    @Query("SELECT * FROM LeagueDB")
    fun getLeagues(): Flow<List<LeagueDB>>

    @Query("SELECT COUNT(id) FROM LeagueDB")
    fun countLeagues(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagues(leagues: List<LeagueDB>)

}