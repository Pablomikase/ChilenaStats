package io.pdaa.chilenastats.framework.models.remote
import io.pdaa.chilenastats.framework.models.database.LeagueDB
import kotlinx.serialization.Serializable

@Serializable
data class LeagueRemoteResponse(
    val country: CountryRemote,
    val league: LeagueRemote,
    val seasons: List<SeasonRemote>
)

fun LeagueRemoteResponse.asDbModel(): LeagueDB = LeagueDB(
    id = league.id,
    logo = league.logo,
    name = league.name,
    type = league.type,
    country = country.asDbModel(),
    season = null,
    isFavourite = false
)

