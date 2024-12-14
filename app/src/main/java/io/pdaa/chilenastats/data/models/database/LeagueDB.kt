package io.pdaa.chilenastats.data.models.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LeagueDB(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val logo: String,
    val name: String,
    val type: String,
    @Embedded()
    val country: CountryDB,
    val season: String? = null,
    val isSelected: Boolean
)

fun LeagueDB.asUiModel() = io.pdaa.chilenastats.domain.LeagueUi(
    id = id,
    logo = logo,
    name = name,
    type = type,
    isSelected = isSelected,
    country = country.asUiModel(),
    season = season
)