package io.pdaa.chilenastats.framework.models.remote.fixture

import kotlinx.serialization.Serializable

@Serializable
data class FixtureRemoteResponse(
    /*val errors: String,*/
    val `get`: String,
    val paging: Paging,
    /*val parameters: Parameters,*/
    val response: List<FixtureResponse>,
    val results: Int
)