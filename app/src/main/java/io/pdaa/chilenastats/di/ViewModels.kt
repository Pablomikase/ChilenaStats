package io.pdaa.chilenastats.di

import io.pdaa.chilenastats.ui.screens.dashboard.DashboardViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.countrySelection.CountrySelectionViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.leagueSelection.LeaguesViewModel
import io.pdaa.chilenastats.ui.screens.onboarding.teamSelection.TeamSelectionViewModel
import io.pdaa.chilenastats.ui.screens.login.LoginViewModel
import io.pdaa.chilenastats.ui.screens.login.sign_up.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::CountrySelectionViewModel)
    viewModelOf(::LeaguesViewModel)
    viewModelOf(::TeamSelectionViewModel)
    viewModelOf(::DashboardViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignUpViewModel)
}