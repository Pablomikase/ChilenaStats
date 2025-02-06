package io.pdaa.chilenastats.framework.models.remote.fixture

import kotlinx.serialization.Serializable

@Serializable
data class Paging(
    val current: Int,
    val total: Int
)