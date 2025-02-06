package io.pdaa.chilenastats.framework.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.LeagueUi
import kotlinx.serialization.Serializable

@Serializable
data class LeagueRemote(
    val country: String,
    val flag: String?,
    val id: Int,
    val logo: String,
    val name: String,
    val round: String,
    val season: Int
)

fun LeagueRemote.asUiModel(): LeagueUi = LeagueUi(
    country = country,
    flag = flag,
    id = id,
    logo = logo,
    name = name,
    round = round,
    season = season
)