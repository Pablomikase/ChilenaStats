package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.GoalsUi
import kotlinx.serialization.Serializable

@Serializable
data class GoalsRemote(
    val away: Int,
    val home: Int
)

fun GoalsRemote.asUiModel(): GoalsUi = GoalsUi(
    away = away,
    home = home
)