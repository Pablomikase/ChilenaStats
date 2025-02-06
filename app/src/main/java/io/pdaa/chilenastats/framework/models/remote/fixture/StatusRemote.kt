package io.pdaa.chilenastats.framework.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.StatusUi
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