package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.FulltimeUi
import kotlinx.serialization.Serializable

@Serializable
data class FulltimeRemote(
    val away: Int?,
    val home: Int?
)

fun FulltimeRemote.asUiModel(): FulltimeUi = FulltimeUi(
    away = away,
    home = home
)