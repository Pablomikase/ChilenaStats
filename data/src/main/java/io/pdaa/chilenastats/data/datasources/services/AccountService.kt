package io.pdaa.chilenastats.data.datasources.services

import io.pdaa.chilenastats.domain.user.UserUi
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUser: Flow<UserUi?>
    val currentUserId: String
    fun hasUser(): Boolean
    fun getUserProfile(): UserUi
    suspend fun createAnonymousAccount()
    suspend fun updateDisplayName(newDisplayName: String)
    suspend fun linkAccountWithGoogle(idToken: String)
    suspend fun linkAccountWithEmail(email: String, password: String)
    suspend fun signInWithGoogle(idToken: String)
    suspend fun signInWithEmail(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
    suspend fun createNewUser(email: String, password: String)
}