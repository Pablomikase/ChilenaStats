package io.pdaa.chilenastats.data.models.local

data class LeagueUi(
    val id:Int,
    val name: String,
    val type: String,
    val logo: String,
    val isSelected: Boolean,
    val country: CountryUi,
    val season: String?
)