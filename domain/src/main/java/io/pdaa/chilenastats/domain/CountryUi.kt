package io.pdaa.chilenastats.domain

data class CountryUi(
    val code: String?,
    val flag: String?,
    val name: String,
    var isSelected: Boolean = false
)
