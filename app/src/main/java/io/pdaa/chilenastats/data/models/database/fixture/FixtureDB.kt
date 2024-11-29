package io.pdaa.chilenastats.data.models.database.fixture

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FixtureDB (
    @PrimaryKey
    val id: Int,
    val date: String,
    val periods: PeriodsDB,
    val referee: String?,
    val status: StatusDB,
    val timestamp: Int,
    val timezone: String,
    val venue: VenueDB

)