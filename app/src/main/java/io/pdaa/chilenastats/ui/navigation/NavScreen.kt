package io.pdaa.chilenastats.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object CountrySelector

@Serializable
data class LeaguesSelector(
    val countryCodes: List<String>
)

@Serializable
data class TeamsSelector(
    val countries: List<String> = emptyList(),
    val leagueIds: List<Int> = emptyList()
)