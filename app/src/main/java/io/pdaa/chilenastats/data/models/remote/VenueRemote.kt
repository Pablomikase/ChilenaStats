package io.pdaa.chilenastats.data.models.remote

import io.pdaa.chilenastats.data.models.database.VenueDB
import kotlinx.serialization.Serializable

@Serializable
class VenueRemote {
    val id: Int? = null
    val name: String? = null
    val address: String? = null
    val city: String? = null
    val capacity: Int? = null
    val surface: String? = null
    var image: String? = ""
}

fun VenueRemote.asDBModel(): VenueDB? {
    return if (id != null) {
        VenueDB(
            venueId = id,
            venueName =  name,
            venueAddress = address,
            venueCity = city,
            venueCapacity = capacity,
            venueSurface = surface,
            venueImage = image
        )
    } else {
        null
    }
}
