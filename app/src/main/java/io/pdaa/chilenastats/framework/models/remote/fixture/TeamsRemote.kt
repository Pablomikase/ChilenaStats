package io.pdaa.chilenastats.framework.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.TeamsUi
import kotlinx.serialization.Serializable

@Serializable
data class TeamsRemote(
    val away: AwayRemote,
    val home: HomeRemote
)

fun TeamsRemote.asUiModel(): TeamsUi = TeamsUi(
    away = away.asUiModel(),
    home = home.asUiModel()
)