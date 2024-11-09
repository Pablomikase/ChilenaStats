package io.pdaa.chilenastats.data.models.remote

import kotlinx.serialization.Serializable

@Serializable
data class CountriesRemoteResponse (
    val response: List<CountryRemoteResponse>
)