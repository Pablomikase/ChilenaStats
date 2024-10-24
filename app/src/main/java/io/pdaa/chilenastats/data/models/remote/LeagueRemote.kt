package io.pdaa.chilenastats.data.models.remote

import io.pdaa.chilenastats.data.models.local.LeagueUi
import kotlinx.serialization.Serializable

@Serializable
data class LeagueRemote(
    val id: Int,
    val logo: String,
    val name: String,
    val type: String
)

fun LeagueRemote.asUiModel(): LeagueUi = LeagueUi(
    id = id,
    logo = logo,
    name = name,
    type = type
)
