package io.pdaa.chilenastats.framework.models.remote

import io.pdaa.chilenastats.domain.VenueUi
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

fun VenueRemote.asUiModel(): VenueUi? {
    return if (id != null) {
        VenueUi(
            id = id,
            name =  name,
            address = address,
            city = city,
            capacity = capacity,
            surface = surface,
            image = image
        )
    } else {
        null
    }
}
