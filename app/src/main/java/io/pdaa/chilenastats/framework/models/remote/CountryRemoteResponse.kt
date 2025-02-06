package io.pdaa.chilenastats.framework.models.remote

import io.pdaa.chilenastats.framework.models.database.CountryDB
import kotlinx.serialization.Serializable

@Serializable
data class CountryRemoteResponse(
    val code: String?,
    val flag: String?,
    val name: String
)

fun CountryRemoteResponse.asDbModel(): CountryDB = CountryDB(
    countryCode = code,
    countryFlag = flag,
    countryName = name,
    countryIsSelected = false
)