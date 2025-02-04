package io.pdaa.chilenastats.ui.navigation

import kotlinx.serialization.Serializable


const val LOCALHOST = "10.0.2.2"
const val AUTH_PORT = 9099
const val FIRESTORE_PORT = 8080

@Serializable
object SplashScreen

@Serializable
object SignUp

@Serializable
object SignIn

@Serializable
object AfterLogin

@Serializable
object CountrySelector

@Serializable
object LeaguesSelector

@Serializable
object TeamsSelector

@Serializable
object Dashboard

