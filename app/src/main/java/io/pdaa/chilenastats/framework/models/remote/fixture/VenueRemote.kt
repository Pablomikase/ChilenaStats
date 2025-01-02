package io.pdaa.chilenastats.framework.models.remote.fixture


import io.pdaa.chilenastats.domain.fixture.VenueUi
import kotlinx.serialization.Serializable

@Serializable
data class VenueRemote(
    val city: String?,
    val id: Int?,
    val name: String?
)

fun VenueRemote.asUiModel(): VenueUi = VenueUi(
    city = city,
    id = id,
    name = name
)