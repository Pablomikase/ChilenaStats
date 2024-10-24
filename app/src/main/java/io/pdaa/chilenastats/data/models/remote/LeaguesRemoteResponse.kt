package io.pdaa.chilenastats.data.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class LeaguesRemoteResponse(
    val response: List<ResponseRemote>
)

