package io.pdaa.chilenastats.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.pdaa.chilenastats.domain.CountryUi

@Entity
data class CountryDB (
    @PrimaryKey(autoGenerate = false)
    val countryName: String,
    val countryCode: String?,
    val countryFlag: String?,
    val countryIsSelected: Boolean

)

fun CountryDB.asUiModel() = CountryUi(
    code = countryCode,
    flag = countryFlag,
    name = countryName,
    isSelected = countryIsSelected
)