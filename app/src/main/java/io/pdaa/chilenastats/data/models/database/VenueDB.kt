package io.pdaa.chilenastats.data.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.pdaa.chilenastats.domain.VenueUi

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

fun VenueDB.asUiModel() = VenueUi(
    id = venueId,
    name = venueName ?: "",
    city = venueCity ?: "",
    capacity = venueCapacity ?: 0,
    surface = venueSurface ?: "",
    image = venueImage ?: "",
    address = venueAddress ?: ""
)
