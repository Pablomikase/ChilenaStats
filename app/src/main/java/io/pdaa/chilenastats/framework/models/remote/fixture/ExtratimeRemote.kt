package io.pdaa.chilenastats.framework.models.remote.fixture


import io.pdaa.chilenastats.domain.fixture.ExtratimeUi
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