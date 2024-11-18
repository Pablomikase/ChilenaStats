package io.pdaa.chilenastats.data.models.remote

import io.pdaa.chilenastats.data.models.local.TeamUi
import kotlinx.serialization.Serializable

@Serializable
class TeamRemote {
    val id: Int = 0
    val name: String? = null
    val code: String? = null
    val country: String? = null
    val founded: Int? = null
    val national: Boolean? = null
    val logo: String? = null
}

fun TeamRemote.asUiModel(): TeamUi = TeamUi(
    id = id,
    name = name ?: "",
    logo = logo ?: "",
    isSelected = false,
    country = country ?: ""
)
