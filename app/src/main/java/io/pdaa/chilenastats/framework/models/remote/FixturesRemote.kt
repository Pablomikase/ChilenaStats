package io.pdaa.chilenastats.framework.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class FixturesRemote(
    val events: Boolean,
    val lineups: Boolean,
    val statistics_fixtures: Boolean,
    val statistics_players: Boolean
)
