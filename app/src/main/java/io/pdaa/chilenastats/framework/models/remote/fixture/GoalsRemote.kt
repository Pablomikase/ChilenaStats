package io.pdaa.chilenastats.framework.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.GoalsUi
import kotlinx.serialization.Serializable

@Serializable
data class GoalsRemote(
    val away: Int?,
    val home: Int?
)

fun GoalsRemote.asUiModel(): GoalsUi = GoalsUi(
    away = away,
    home = home
)