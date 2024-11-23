package io.pdaa.chilenastats.data.models.remote.fixture

data class FixtureRemoteResponse(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)