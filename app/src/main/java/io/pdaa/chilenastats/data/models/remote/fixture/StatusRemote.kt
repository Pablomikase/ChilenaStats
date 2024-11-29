package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.StatusUi
import kotlinx.serialization.Serializable

@Serializable
data class StatusRemote(
    val long: String,
    val short: String,
    val elapsed: Int?,
    val extra: Int?
)

fun StatusRemote.asUiModel(): StatusUi = StatusUi(
    elapsed = elapsed,
    long = long,
    short = short,
    extra = extra
)