package io.pdaa.chilenastats.framework.models.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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


