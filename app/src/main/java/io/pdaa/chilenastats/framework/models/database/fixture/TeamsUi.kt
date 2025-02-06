package io.pdaa.chilenastats.framework.models.database.fixture

import androidx.room.Embedded

data class TeamsDB(
    @Embedded
    val teamAway: AwayDB,
    @Embedded
    val teamHome: HomeDB
)
