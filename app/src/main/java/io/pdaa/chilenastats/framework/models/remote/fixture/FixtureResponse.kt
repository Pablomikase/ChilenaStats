package io.pdaa.chilenastats.framework.models.remote.fixture

import kotlinx.serialization.Serializable

@Serializable
data class FixtureResponse(
    val fixture: FixtureRemote,
    val goals: GoalsRemote,
    val league: LeagueRemote,
    val score: ScoreRemote,
    val teams: TeamsRemote
)

