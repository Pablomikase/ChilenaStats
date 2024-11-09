package io.pdaa.chilenastats.data

import io.pdaa.chilenastats.data.models.remote.CountriesRemoteResponse
import io.pdaa.chilenastats.data.models.remote.LeaguesRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FreeFootballDataService {

    @GET("leagues")
    suspend fun fetchLeaguesByCountry(@Query("code") countryCode: String): LeaguesRemoteResponse

    @GET("football-league-detail")
    suspend fun fetchLeagueById(@Path("leagueId") leagueId: String): LeaguesRemoteResponse

    @GET("countries")
    suspend fun fetchCountries(): CountriesRemoteResponse

}