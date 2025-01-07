package io.pdaa.chilenastats.di

import io.pdaa.chilenastats.data.repositories.CountriesRepository
import io.pdaa.chilenastats.data.repositories.FixturesRepository
import io.pdaa.chilenastats.data.repositories.LeaguesRepository
import io.pdaa.chilenastats.data.repositories.TeamRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val repositoryModule = module {

    factoryOf(::CountriesRepository)
    factoryOf(::FixturesRepository)
    factoryOf(::LeaguesRepository)
    factoryOf(::TeamRepository)

    /*single {
        CountriesRepository(
            regionDataSource = get(),
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single {
        FixturesRepository(
            remoteDataSource = get(),
            teamsLocalDataSource = get(),
            fixturesLocalDataSource = get()
        )
    }

    single {
        LeaguesRepository(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single{
        TeamRepository(
            remoteDataSource = get(),
            localDataSource = get(),
            regionDataSource = get(),
            countryLocalDataSource = get(),
            countriesRemoteDataSource = get()
        )
    }*/
}