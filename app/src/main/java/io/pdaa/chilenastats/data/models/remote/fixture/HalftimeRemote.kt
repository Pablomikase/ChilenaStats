package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.HalftimeUi
import kotlinx.serialization.Serializable

@Serializable
data class HalftimeRemote(
    val away: Int?,
    val home: Int?
)

fun HalftimeRemote.asUiModel(): HalftimeUi = HalftimeUi(
    away = away,
    home = home
)