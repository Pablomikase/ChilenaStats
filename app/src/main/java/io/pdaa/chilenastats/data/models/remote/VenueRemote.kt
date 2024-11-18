package io.pdaa.chilenastats.data.models.remote

import io.pdaa.chilenastats.data.models.local.VenueUi
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

fun VenueRemote.asUiModel(): VenueUi = VenueUi(
    id = id,
    name = name ?: "",
    address = address ?: "",
    city = city ?: "",
    capacity = capacity ?: 0,
    surface = surface ?: "",
    image = image ?: ""
)
