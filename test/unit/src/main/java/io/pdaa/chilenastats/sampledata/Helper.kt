package io.pdaa.chilenastats.sampledata

import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.VenueUi

fun sampleCountries(vararg countryIds: Int) = countryIds.map {
    sampleCountry(it)
}

fun sampleCountry(id: Int) = CountryUi(
    code = "code$id",
    flag = "flag$id",
    name = "name$id",
    isSelected = id == 1
)

fun sampleLeagues(vararg ids: Int) = ids.map {
    sampleLeague(it)
}

fun sampleLeague(id: Int) = LeagueUi(
    id = id,
    name = "name$id",
    isFavourite = id % 2 == 0,
    country = sampleCountry(id),
    type = "type$id",
    logo = "logo$id",
    season = "season$id"
)

fun sampleTeam(id: Int) = TeamUi(
    id = id,
    name = "name$id",
    logo = "logo$id",
    country = "country $id",
    national = false,
    isFavourite = id == 1,
    founded = 144,
    venue = sampleVenue(id)
)

fun sampleVenue(id: Int) = VenueUi(
    id = id,
    name = "name $id",
    surface = "surface $id",
    address = "address $id",
    capacity = 123,
    image = "image $id",
    city = "city $id"
)

fun sampleTeams(vararg id: Int) = id.map { sampleTeam(it) }


