package io.pdaa.chilenastats.framework.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.HalftimeUi
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