package io.pdaa.chilenastats.framework.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class TeamsRemoteResponse(
    val response: List<TeamRemoteResponse>
)