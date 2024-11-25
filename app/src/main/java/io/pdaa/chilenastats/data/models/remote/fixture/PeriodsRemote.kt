package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.PeriodsUi
import kotlinx.serialization.Serializable

@Serializable
data class PeriodsRemote(
    val first: Int,
    val second: Int
)

fun PeriodsRemote.asUiModel(): PeriodsUi = PeriodsUi(
    first = first,
    second = second
)