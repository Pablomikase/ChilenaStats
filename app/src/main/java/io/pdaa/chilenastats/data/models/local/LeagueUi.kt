package io.pdaa.chilenastats.data.models.local

import io.pdaa.chilenastats.data.models.database.LeagueDB

data class LeagueUi(
    val id:Int,
    val name: String,
    val type: String,
    val logo: String,
    val isSelected: Boolean,
    val country: CountryUi,
    val season: String?
)

fun LeagueUi.asDBModel(): LeagueDB {
    return LeagueDB(
        id = this.id,
        name = this.name,
        type = this.type,
        logo = this.logo,
        isSelected = this.isSelected,
        country = this.country.asDbModel(),
        season = this.season
    )
}