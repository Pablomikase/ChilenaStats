package io.pdaa.chilenastats.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object CountrySelector

@Serializable
data class LeaguesSelector(
    val countryNames: List<String>
)

@Serializable
data class TeamsSelector(
    val countries: List<String> = emptyList(),
    val leagueIds: List<Int> = emptyList()
)

@Serializable
data class Dashboard(
    val countries: List<String> = emptyList(),
    val leagueIds: List<Int> = emptyList(),
    val teamIds: List<Int> = emptyList()
)

