package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.FixtureUi
import kotlinx.serialization.Serializable

@Serializable
data class FixtureRemote(
    val date: String,
    val id: Int,
    val periods: PeriodsRemote,
    val referee: String,
    val status: StatusRemote,
    val timestamp: Int,
    val timezone: String,
    val venue: VenueRemote
)

fun FixtureRemote.asUiModel(): FixtureUi {
    return FixtureUi(
        date = date,
        id = id,
        periods = periods.asUiModel(),
        referee = referee,
        status = status.asUiModel(),
        timestamp = timestamp,
        timezone = timezone,
        venue = venue.asUiModel()
    )
}