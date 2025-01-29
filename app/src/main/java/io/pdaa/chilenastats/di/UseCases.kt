package io.pdaa.chilenastats.di

import io.pdaa.chilenastats.usecases.FetchFixturesFromFavouriteTeamsUseCase
import io.pdaa.chilenastats.usecases.FetchLeaguesUseCase
import io.pdaa.chilenastats.usecases.teams.FetchTeamsUseCase
import io.pdaa.chilenastats.usecases.SelectLeagueUseCase
import io.pdaa.chilenastats.usecases.SelectTeamUseCase
import io.pdaa.chilenastats.usecases.UserIsLoggedInUseCase
import io.pdaa.chilenastats.usecases.teams.SaveTeamsFromFavouriteLeaguesUseCase
import io.pdaa.chilenastats.usecases.teams.SaveTeamsFromUserCountryUseCase
import io.pdaa.chilenastats.usecases.teams.SearchTeamUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::FetchLeaguesUseCase)
    factoryOf(::SelectLeagueUseCase)
    factoryOf(::UserIsLoggedInUseCase)
    factoryOf(::FetchTeamsUseCase)
    factoryOf(::SelectTeamUseCase)
    factoryOf(::FetchFixturesFromFavouriteTeamsUseCase)
    factoryOf(::SaveTeamsFromFavouriteLeaguesUseCase)
    factoryOf(::SaveTeamsFromUserCountryUseCase)
    factoryOf(::SearchTeamUseCase)
}