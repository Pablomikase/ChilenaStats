package io.pdaa.chilenastats.framework.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.AwayUi
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