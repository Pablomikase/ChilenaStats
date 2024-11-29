package io.pdaa.chilenastats.data.models.database.fixture

import androidx.room.Entity

@Entity
data class FixtureContainerDB(
    val fixture: FixtureDB,
    val goals: GoalsDB,
    val league: LeagueDB,
    val score: ScoreDB,
    val teams: TeamsDB
)