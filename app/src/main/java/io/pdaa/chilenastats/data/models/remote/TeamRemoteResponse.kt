package io.pdaa.chilenastats.data.models.remote

import io.pdaa.chilenastats.data.models.database.TeamDB
import kotlinx.serialization.Serializable

@Serializable
data class TeamRemoteResponse(
    val team: TeamRemote,
    val venue: VenueRemote
)

fun TeamRemoteResponse.asDBModel():TeamDB = TeamDB(
    id = team.id,
    name = team.name ?: "",
    logo = team.logo ?: "",
    country = team.country ?: "",
    founded = team.founded,
    national = team.national,
    venue = venue.asDBModel(),
    isSelected = false
)
