package io.pdaa.chilenastats.di

import android.content.Context
import android.location.Geocoder
import androidx.room.Room
import com.google.android.gms.location.LocationServices
import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.FixturesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.LeaguesLocalDataSource
import io.pdaa.chilenastats.data.datasources.local.TeamsLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.FixturesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.LeaguesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.LocationDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.data.datasources.remote.TeamsRemoteDataSource
import io.pdaa.chilenastats.framework.database.FootballDatabase
import io.pdaa.chilenastats.framework.datasourcesImpl.local.CountriesRoomDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.local.FixturesRoomDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.local.LeaguesRoomDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.local.TeamsRoomDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.remote.CountriesServerDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.remote.LeaguesServerDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.remote.TeamsServerDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.remote.FixturesServerDataSource
import io.pdaa.chilenastats.framework.datasourcesImpl.sensors.GeocoderRegionSource
import io.pdaa.chilenastats.framework.datasourcesImpl.sensors.PlayServicesLocationDataSource
import io.pdaa.chilenastats.framework.server.FreeFootballDataClient
import io.pdaa.chilenastats.data.datasources.services.AccountService
import io.pdaa.chilenastats.framework.datasourcesImpl.services.FirebaseAuthenticator
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {

    //Database
    single { Room.databaseBuilder(get(), FootballDatabase::class.java, "football.db").build() }

    //Daos
    factory { get<FootballDatabase>().countriesDao() }
    factory { get<FootballDatabase>().leaguesDao() }
    factory { get<FootballDatabase>().teamsDao() }
    factory { get<FootballDatabase>().fixturesDao() }

    //Footbal Api client dependency
    single { FreeFootballDataClient.instance }

    //DataSources
    factoryOf(::CountriesRoomDataSource) bind CountriesLocalDataSource::class
    factoryOf(::LeaguesRoomDataSource) bind LeaguesLocalDataSource::class
    factoryOf(::TeamsRoomDataSource) bind TeamsLocalDataSource::class
    factoryOf(::FixturesRoomDataSource) bind FixturesLocalDataSource::class
    factoryOf(::CountriesServerDataSource) bind CountriesRemoteDataSource::class
    factoryOf(::LeaguesServerDataSource) bind LeaguesRemoteDataSource::class
    factoryOf(::TeamsServerDataSource) bind TeamsRemoteDataSource::class
    factoryOf(::FixturesServerDataSource) bind FixturesRemoteDataSource::class

    //Sensors
    factoryOf(::PlayServicesLocationDataSource) bind LocationDataSource::class
    factory {
        LocationServices.getFusedLocationProviderClient(get<Context>())
    }
    factoryOf(::GeocoderRegionSource) bind RegionDataSource::class
    factory { Geocoder(get()) }

    //Services
    factory { FirebaseAuthenticator() } bind AccountService::class

}