package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.PenaltyUi
import kotlinx.serialization.Serializable

@Serializable
data class PenaltyRemote(
    val away: Int?,
    val home: Int?
)

fun PenaltyRemote.asUiModel(): PenaltyUi = PenaltyUi(
    away = away,
    home = home
)