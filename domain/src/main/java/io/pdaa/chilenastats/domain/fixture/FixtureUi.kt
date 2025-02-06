package io.pdaa.chilenastats.domain.fixture

data class FixtureUi (
    val date: String,
    val id: Int,
    val periods: PeriodsUi,
    val referee: String?,
    val status: StatusUi,
    val timestamp: Int,
    val timezone: String,
    val venue: VenueUi

)