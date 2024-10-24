package io.pdaa.chilenastats.data.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class CountryRemote(
    val code: String?,
    val flag: String?,
    val name: String
)
