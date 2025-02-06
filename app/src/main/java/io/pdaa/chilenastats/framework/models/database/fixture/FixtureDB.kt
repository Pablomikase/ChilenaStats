package io.pdaa.chilenastats.framework.models.database.fixture

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FixtureDB (
    @PrimaryKey
    val id: Int,
    val date: String,
    @Embedded
    val periods: PeriodsDB,
    val referee: String?,
    @Embedded
    val status: StatusDB,
    val timestamp: Int,
    val timezone: String,
    @Embedded
    val venue: VenueDB
)

