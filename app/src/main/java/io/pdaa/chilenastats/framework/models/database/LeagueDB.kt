package io.pdaa.chilenastats.framework.models.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.pdaa.chilenastats.domain.LeagueUi

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
    val isFavourite: Boolean
)

fun LeagueDB.asUiModel() = LeagueUi(
    id = id,
    logo = logo,
    name = name,
    type = type,
    isFavourite = isFavourite,
    country = country.asUiModel(),
    season = season
)