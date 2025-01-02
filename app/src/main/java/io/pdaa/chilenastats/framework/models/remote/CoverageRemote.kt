package io.pdaa.chilenastats.framework.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class CoverageRemote(
    val fixtures: FixturesRemote,
    val injuries: Boolean,
    val odds: Boolean,
    val players: Boolean,
    val predictions: Boolean,
    val standings: Boolean,
    val top_assists: Boolean,
    val top_cards: Boolean,
    val top_scorers: Boolean
)