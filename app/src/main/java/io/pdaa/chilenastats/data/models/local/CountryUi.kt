package io.pdaa.chilenastats.data.models.local

data class CountryUi(
    val code: String?,
    val flag: String?,
    val name: String,
    var isSelected: Boolean = false
)
