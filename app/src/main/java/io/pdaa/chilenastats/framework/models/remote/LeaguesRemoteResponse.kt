package io.pdaa.chilenastats.framework.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class LeaguesRemoteResponse(
    val response: List<LeagueRemoteResponse>
)

