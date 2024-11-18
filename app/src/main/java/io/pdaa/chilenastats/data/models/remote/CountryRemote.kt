package io.pdaa.chilenastats.data.models.remote

import io.pdaa.chilenastats.data.models.local.CountryUi
import kotlinx.serialization.Serializable

@Serializable
data class CountryRemote(
    val code: String?,
    val flag: String?,
    val name: String
)

fun CountryRemote.asUiModel(): CountryUi = CountryUi(
    code = code,
    flag = flag,
    name = name
)
