package io.pdaa.chilenastats.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.pdaa.chilenastats.data.models.local.fixture.PeriodsUi
import io.pdaa.chilenastats.data.models.local.fixture.StatusUi
import io.pdaa.chilenastats.data.models.local.fixture.VenueUi

@Entity
data class FixtureDB(
    @PrimaryKey
    val id: Int,
    val date: String,
    val periods: PeriodsUi,
    val referee: String?,
    val status: StatusUi,
    val timestamp: Int,
    val timezone: String,
    val venue: VenueUi
)