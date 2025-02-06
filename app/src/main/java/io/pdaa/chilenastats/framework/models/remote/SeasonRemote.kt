package io.pdaa.chilenastats.framework.models.remote


import kotlinx.serialization.Serializable

@Serializable
data class SeasonRemote(
    val coverage: CoverageRemote,
    val current: Boolean,
    val end: String,
    val start: String,
    val year: Int
)
