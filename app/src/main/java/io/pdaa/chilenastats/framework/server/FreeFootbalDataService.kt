package io.pdaa.chilenastats.framework.server

import io.pdaa.chilenastats.framework.models.remote.CountriesRemoteResponse
import io.pdaa.chilenastats.framework.models.remote.LeaguesRemoteResponse
import io.pdaa.chilenastats.framework.models.remote.TeamsRemoteResponse
import io.pdaa.chilenastats.framework.models.remote.fixture.FixtureRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballDataService {


    @GET("countries")
    suspend fun fetchCountries(): CountriesRemoteResponse

    @GET("leagues")
    suspend fun fetchLeaguesByCountry(@Query("code") countryCode: String): LeaguesRemoteResponse

    @GET("football-league-detail")
    suspend fun fetchLeagueById(@Path("leagueId") leagueId: String): LeaguesRemoteResponse

    @GET("leagues")
    suspend fun fetchLeagues(): LeaguesRemoteResponse

    //Teams
    @GET("teams")
    suspend fun fetchTeamsByCountryName(@Query("country") countryName: String): TeamsRemoteResponse

    @GET("teams")
    suspend fun fetchTeamsByLeagueId(
        @Query("league") leagueId: Int,
        @Query("season") season: Int,
    ): TeamsRemoteResponse

    @GET("teams")
    suspend fun fetchTeamsByQuery(@Query("search") query: String): TeamsRemoteResponse


    @GET("fixtures")
    suspend fun fetchFixturesByLeague(@Query("league") leagueId: Int): TeamsRemoteResponse

    @GET("fixtures")
    suspend fun fetchFixturesByTeam(
        @Query("team") teamId: Int,
        @Query("season") season: Int = 2022,
    ): FixtureRemoteResponse


}