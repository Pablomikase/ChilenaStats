package io.pdaa.chilenastats.framework.models.database.fixture

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FixtureContainerDB(

    @PrimaryKey(autoGenerate = true)
    val fixtureContainerId: Int = 0,
    val teamOwnerId: Int,
    @Embedded
    val fixture: FixtureDB,
    @Embedded
    val goals: GoalsDB,
    @Embedded
    val league: LeagueDB,
    @Embedded
    val score: ScoreDB,
    @Embedded
    val teams: TeamsDB
)