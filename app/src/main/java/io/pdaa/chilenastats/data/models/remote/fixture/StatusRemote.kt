package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.StatusUi
import kotlinx.serialization.Serializable

@Serializable
data class StatusRemote(
    val elapsed: Int,
    val long: String,
    val short: String
)

fun StatusRemote.asUiModel(): StatusUi = StatusUi(
    elapsed = elapsed,
    long = long,
    short = short
)