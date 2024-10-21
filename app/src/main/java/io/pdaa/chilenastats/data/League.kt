package io.pdaa.chilenastats.data

data class League(
    val id:Int,
    val name: String,
    val type: String,
    val logo: String
)


val mockLeagues = List(10) { index ->
    League(
        id = index,
        name = "League $index",
        type = "Type $index",
        logo = "https://media.api-sports.io/football/leagues/$index.png"
    )
}
