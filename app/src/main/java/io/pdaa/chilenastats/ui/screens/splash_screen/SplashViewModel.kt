package io.pdaa.chilenastats.ui.screens.splash_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.pdaa.chilenastats.data.datasources.services.AccountService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SplashViewModel(
    private val accountService: AccountService,

    ) : ViewModel(){

    fun onAppStart(openAndPopUp: () -> Unit) {
        if (accountService.hasUser()) openAndPopUp()
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: () -> Unit) {
        launchCatching {
            //accountService.createAnonymousAccount()
            openAndPopUp()
        }
    }

    private fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d("SplashViewModel", throwable.message.orEmpty())
            },
            block = block
        )

}