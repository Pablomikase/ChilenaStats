package io.pdaa.chilenastats.data.models.remote
import io.pdaa.chilenastats.data.models.local.ResponseUi
import kotlinx.serialization.Serializable

@Serializable
data class LeagueRemoteResponse(
    val country: CountryRemote,
    val league: LeagueRemote,
    val seasons: List<SeasonRemote>
)

fun LeagueRemoteResponse.asUiModel(): ResponseUi = ResponseUi(
    league = league.asUiModel(),
    country = country.asUiModel()
)

fun List<LeagueRemoteResponse>.asUiModel(): List<ResponseUi> = map { it.asUiModel() }