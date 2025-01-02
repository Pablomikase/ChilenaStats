package io.pdaa.chilenastats.framework.models.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.pdaa.chilenastats.domain.TeamUi

@Entity
data class TeamDB(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val logo: String,
    val founded: Int?,
    val national: Boolean?,
    val country: String,
    @Embedded
    val venue: VenueDB?,
    val isSelected: Boolean,
)

fun TeamDB.asUiModel() = TeamUi(
    id = id,
    name = name,
    logo = logo,
    isSelected = isSelected,
    country = country,
    founded = founded,
    national = national,
    venue = venue?.asUiModel()
)
