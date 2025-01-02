package io.pdaa.chilenastats.framework.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class CountriesRemoteResponse (
    val response: List<CountryRemoteResponse>
)