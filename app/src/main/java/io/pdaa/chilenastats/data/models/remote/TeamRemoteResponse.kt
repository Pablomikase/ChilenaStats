package io.pdaa.chilenastats.data.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class TeamRemoteResponse(
    val team: TeamRemote,
    val venue: VenueRemote
)
