package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.HomeUi
import kotlinx.serialization.Serializable

@Serializable
data class HomeRemote(
    val id: Int,
    val logo: String,
    val name: String,
    val winner: Boolean?
)

fun HomeRemote.asUiModel(): HomeUi = HomeUi(
    id = id,
    logo = logo,
    name = name,
    winner = winner
)