package io.pdaa.chilenastats.data.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class TeamsRemoteResponse(
    val response: List<TeamRemoteResponse>
)