package io.pdaa.chilenastats.framework.models.remote.fixture

import kotlinx.serialization.Serializable

@Serializable
data class FulltimeRemote(
    val away: Int?,
    val home: Int?
)

fun FulltimeRemote.asUiModel(): io.pdaa.chilenastats.domain.fixture.FulltimeUi =
    io.pdaa.chilenastats.domain.fixture.FulltimeUi(
        away = away,
        home = home
    )