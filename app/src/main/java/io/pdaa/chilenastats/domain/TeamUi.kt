package io.pdaa.chilenastats.domain

data class TeamUi(
    val id: Int,
    val name: String,
    val logo: String,
    val founded: Int?,
    val national: Boolean?,
    val isSelected: Boolean,
    val country: String,
    val venue: VenueUi?,
)


