package io.pdaa.chilenastats.domain

import io.pdaa.chilenastats.data.models.database.VenueDB

data class VenueUi(
    val id: Int?,
    val name: String,
    val city: String,
    val capacity: Int,
    val surface: String,
    val image: String,
    val address: String
)

fun VenueUi.asDBModel(): VenueDB {
    return VenueDB(
        venueId = this.id?:0,
        venueName = this.name,
        venueCity = this.city,
        venueCapacity = this.capacity,
        venueSurface = this.surface,
        venueImage = this.image,
        venueAddress = this.address
    )
}
