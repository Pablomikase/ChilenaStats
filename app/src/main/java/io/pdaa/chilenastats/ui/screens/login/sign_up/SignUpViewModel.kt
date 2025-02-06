package io.pdaa.chilenastats.ui.screens.login.sign_up

import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import io.pdaa.chilenastats.data.datasources.services.AccountService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val accountService: AccountService
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    fun onSignUpClick(onSuccessSignUp: () -> Unit) {
        launchCatching {
            if (!_email.value.isValidEmail()) {
                throw IllegalArgumentException("Invalid email format")
            }

            if (!_password.value.isValidPassword()) {
                throw IllegalArgumentException("Invalid password format")
            }

            if (_password.value != _confirmPassword.value) {
                throw IllegalArgumentException("Passwords do not match")
            }


            accountService.createNewUser(_email.value, _password.value)
            onSuccessSignUp()


        }
    }

    fun onSignUpWithGoogle(credential: Credential, onSuccessSignUp: () -> Unit) {
        launchCatching {
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                accountService.linkAccountWithGoogle(googleIdTokenCredential.idToken)
                onSuccessSignUp()
            } else {
                Log.e("SignUpViewModel", "UNEXPECTED_CREDENTIAL")
            }
        }
    }

    private fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d("SignUpViewModel", throwable.message.orEmpty())
            },
            block = block
        )

}

