package io.pdaa.chilenastats.data.models.remote.fixture

import io.pdaa.chilenastats.data.models.local.fixture.VenueUi
import kotlinx.serialization.Serializable

@Serializable
data class VenueRemote(
    val city: String,
    val id: Int,
    val name: String
)

fun VenueRemote.asUiModel(): VenueUi = VenueUi(
    city = city,
    id = id,
    name = name
)