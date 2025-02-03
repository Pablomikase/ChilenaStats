package io.pdaa.chilenastats.ui.screens.onboarding.login.service

data class User(
    val id: String = "",
    val email: String = "",
    val provider: String = "",
    val displayName: String = "",
    val isAnonymous: Boolean = true
)
