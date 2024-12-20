package io.pdaa.chilenastats.data

import io.pdaa.chilenastats.data.models.remote.CountriesRemoteResponse
import io.pdaa.chilenastats.data.models.remote.LeaguesRemoteResponse
import io.pdaa.chilenastats.data.models.remote.TeamsRemoteResponse
import io.pdaa.chilenastats.data.models.remote.fixture.FixtureRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FreeFootballDataService {


    @GET("countries")
    suspend fun fetchCountries(): CountriesRemoteResponse

    @GET("leagues")
    suspend fun fetchLeaguesByCountry(@Query("code") countryCode: String): LeaguesRemoteResponse

    @GET("football-league-detail")
    suspend fun fetchLeagueById(@Path("leagueId") leagueId: String): LeaguesRemoteResponse

    @GET("leagues")
    suspend fun fetchLeagues(): LeaguesRemoteResponse

    @GET("teams")
    suspend fun fetchTeamsByCountryName(@Query("country") countryName: String): TeamsRemoteResponse

    @GET("fixtures")
    suspend fun fetchFixturesByLeague(@Query("league") leagueId: Int): TeamsRemoteResponse

    @GET("fixtures")
    suspend fun fetchFixturesByTeam(
        @Query("team") teamId: Int,
        @Query("season") season: Int = 2022,
    ): FixtureRemoteResponse


}