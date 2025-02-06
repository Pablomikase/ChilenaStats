package io.pdaa.chilenastats.framework.models.remote.fixture

import kotlinx.serialization.Serializable

@Serializable
data class HomeRemote(
    val id: Int,
    val logo: String,
    val name: String,
    val winner: Boolean?
)

fun HomeRemote.asUiModel(): io.pdaa.chilenastats.domain.fixture.HomeUi =
    io.pdaa.chilenastats.domain.fixture.HomeUi(
        id = id,
        logo = logo,
        name = name,
        winner = winner
    )