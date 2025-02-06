package io.pdaa.chilenastats.domain.user

data class UserUi (
    val id: String = "",
    val email: String = "",
    val provider: String = "",
    val displayName: String = "",
    val isAnonymous: Boolean = true
)