package io.pdaa.chilenastats.data.models.local

import io.pdaa.chilenastats.data.models.database.CountryDB

data class CountryUi(
    val code: String?,
    val flag: String?,
    val name: String,
    var isSelected: Boolean = false
)

fun CountryUi.asDbModel() = CountryDB(
    countryCode = code,
    countryFlag = flag,
    countryName = name,
    countryIsSelected = isSelected
)
