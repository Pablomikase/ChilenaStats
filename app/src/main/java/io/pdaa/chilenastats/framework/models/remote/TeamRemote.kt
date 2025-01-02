package io.pdaa.chilenastats.framework.models.remote

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
