package io.pdaa.chilenastats.usecases.user

import io.pdaa.chilenastats.data.datasources.services.AccountService

class IsUserLoggedInUseCase(private val accountService: AccountService) {
    operator fun invoke() = accountService.hasUser()
}