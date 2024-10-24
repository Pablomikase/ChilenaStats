package io.pdaa.chilenastats.data.models.remote



import io.pdaa.chilenastats.data.models.local.ResponseUi
import kotlinx.serialization.Serializable

@Serializable
data class ResponseRemote(
    val country: CountryRemote,
    val league: LeagueRemote,
    val seasons: List<SeasonRemote>
)

fun ResponseRemote.asUiModel(): ResponseUi = ResponseUi(
    league = league.asUiModel(),
)

fun List<ResponseRemote>.asUiModel(): List<ResponseUi> = map { it.asUiModel() }