package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.AwayUi
import kotlinx.serialization.Serializable

@Serializable
data class AwayRemote(
    val id: Int,
    val logo: String,
    val name: String,
    val winner: Boolean?
)

fun AwayRemote.asUiModel(): AwayUi = AwayUi(
    id = id,
    logo = logo,
    name = name,
    winner = winner
)