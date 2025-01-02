package io.pdaa.chilenastats.framework.models.remote

import io.pdaa.chilenastats.framework.models.database.CountryDB
import io.pdaa.chilenastats.domain.CountryUi
import kotlinx.serialization.Serializable

@Serializable
data class CountryRemote(
    val code: String?,
    val flag: String?,
    val name: String
)

fun CountryRemote.asDbModel(): CountryDB = CountryDB(
    countryCode = code,
    countryFlag = flag,
    countryName = name,
    countryIsSelected = false
)

fun CountryRemote.asUiModel(): CountryUi = CountryUi(
    code = code,
    flag = flag,
    name = name
)
