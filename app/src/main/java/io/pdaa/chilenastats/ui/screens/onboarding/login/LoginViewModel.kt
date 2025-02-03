package io.pdaa.chilenastats.ui.screens.onboarding.login


import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import io.pdaa.chilenastats.ui.screens.onboarding.login.service.AccountService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val accountService: AccountService
) : ViewModel(

) {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()


    fun onSignUpWithGoogle(credential: Credential) {
        launchCatching {
            if(credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                accountService.linkAccountWithGoogle(googleIdTokenCredential.idToken)
            }else{
                Log.e("ERROR_TAG", "UNEXPECTED_CREDENTIAL")
            }
        }

    }

    private fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d("ERROR_TAG", throwable.message.orEmpty())
            },
            block = block
        )

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            val hasUser = accountService.hasUser()
            Log.i("LoginViewModel", "hasUser: $hasUser")
            val currentUser = accountService.currentUser
            accountService.signInWithEmail(_email.value, _password.value)
            Log.i("LoginViewModel", "signInWithEmailPasswordFinished")

        //openAndPopUp("NOTES_LIST_SCREEN", "SIGN_IN_SCREEN")
        }
    }

    fun onSignInWithGoogle(credential: Credential, openAndPopUp: Any) {
        TODO("Not yet implemented")
    }

}
