package io.pdaa.chilenastats.data.models.local.fixture


data class FixtureResponseUi(
    val fixture: FixtureUi,
    val goals: GoalsUi,
    val league: LeagueUi,
    val score: ScoreUi,
    val teams: TeamsUi
)