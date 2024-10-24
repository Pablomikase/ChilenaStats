package io.pdaa.chilenastats.data.models.local

data class LeagueUi(
    val id:Int,
    val name: String,
    val type: String,
    val logo: String
)


val mockLeagues = List(50) { index ->
    LeagueUi(
        id = index,
        name = "League $index",
        type = "Type $index",
        logo = "https://media.api-sports.io/football/leagues/$index.png"
    )
}
