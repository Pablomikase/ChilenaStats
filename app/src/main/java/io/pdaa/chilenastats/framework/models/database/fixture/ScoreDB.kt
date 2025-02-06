package io.pdaa.chilenastats.framework.models.database.fixture

import androidx.room.Embedded

data class ScoreDB(
    @Embedded
    val extratime: ExtratimeDB,
    @Embedded
    val fulltime: FulltimeDB,
    @Embedded
    val halftime: HalftimeDB,
    @Embedded
    val penalty: PenaltyDB
)
