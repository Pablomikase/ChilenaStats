package io.pdaa.chilenastats.domain


data class LeagueUi(
    val id:Int,
    val name: String,
    val type: String,
    val logo: String,
    val isFavourite: Boolean,
    val country: CountryUi,
    val season: String?
)