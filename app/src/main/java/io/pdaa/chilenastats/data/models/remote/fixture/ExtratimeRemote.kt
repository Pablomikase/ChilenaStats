package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.ExtratimeUi
import kotlinx.serialization.Serializable

@Serializable
data class ExtratimeRemote(
    val away: Int?,
    val home: Int?
)

fun ExtratimeRemote.asUiModel(): ExtratimeUi = ExtratimeUi(
    away = away,
    home = home
)