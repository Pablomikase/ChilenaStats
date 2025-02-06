package io.pdaa.chilenastats.framework.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryDB (
    @PrimaryKey(autoGenerate = false)
    val countryName: String,
    val countryCode: String?,
    val countryFlag: String?,
    val countryIsSelected: Boolean

)

