package io.pdaa.chilenastats.data.models.local

import io.pdaa.chilenastats.data.models.database.TeamDB

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

fun TeamUi.asDBModel(): TeamDB {
    return TeamDB(
        id = this.id,
        name = this.name,
        logo = this.logo,
        isSelected = this.isSelected,
        country = this.country,
        venue = this.venue?.asDBModel(),
        founded = this.founded,
        national = this.national
    )
}
