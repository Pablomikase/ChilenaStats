package io.pdaa.chilenastats.data.models.remote.fixture


import io.pdaa.chilenastats.data.models.local.fixture.FixtureResponseUi
import kotlinx.serialization.Serializable

@Serializable
data class FixtureResponse(
    val fixture: FixtureRemote,
    val goals: GoalsRemote,
    val league: LeagueRemote,
    val score: ScoreRemote,
    val teams: TeamsRemote
)

fun FixtureResponse.asUiModel(): FixtureResponseUi = FixtureResponseUi(
    fixture = fixture.asUiModel(),
    goals = goals.asUiModel(),
    league = league.asUiModel(),
    score = score.asUiModel(),
    teams = teams.asUiModel()
)

