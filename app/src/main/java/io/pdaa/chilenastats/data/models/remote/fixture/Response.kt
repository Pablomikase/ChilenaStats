package io.pdaa.chilenastats.data.models.remote.fixture

data class Response(
    val fixture: Fixture,
    val goals: Goals,
    val league: League,
    val score: Score,
    val teams: Teams
)