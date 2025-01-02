package io.pdaa.chilenastats.framework.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VenueDB(
    @PrimaryKey
    val venueId: Int,
    val venueName: String?,
    val venueAddress: String?,
    val venueCity: String?,
    val venueCapacity: Int?,
    val venueSurface: String?,
    var venueImage: String?
)
