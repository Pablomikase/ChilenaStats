package io.pdaa.chilenastats.framework.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class LeagueRemote(
    val id: Int,
    val logo: String,
    val name: String,
    val type: String
)