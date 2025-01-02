package io.pdaa.chilenastats.framework.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import kotlinx.serialization.Serializable

@Serializable
data class FixtureResponse(
    val fixture: FixtureRemote,
    val goals: GoalsRemote,
    val league: LeagueRemote,
    val score: ScoreRemote,
    val teams: TeamsRemote
)

fun FixtureResponse.asUiModel(): FixtureContainerUi = FixtureContainerUi(
    fixture = fixture.asUiModel(),
    goals = goals.asUiModel(),
    league = league.asUiModel(),
    score = score.asUiModel(),
    teams = teams.asUiModel()
)

