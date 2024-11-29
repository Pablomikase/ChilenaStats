package io.pdaa.chilenastats.data.models.database.fixture

data class ScoreDB(
    val extratime: ExtratimeDB,
    val fulltime: FulltimeDB,
    val halftime: HalftimeDB,
    val penalty: PenaltyDB
)
