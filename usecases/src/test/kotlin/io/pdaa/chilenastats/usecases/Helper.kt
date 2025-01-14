package io.pdaa.chilenastats.usecases

import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.VenueUi
import io.pdaa.chilenastats.domain.fixture.FixtureUi
import io.pdaa.chilenastats.domain.fixture.PeriodsUi
import io.pdaa.chilenastats.domain.fixture.StatusUi

fun sampleLeague(id: Int) = LeagueUi(
    id = id,
    name = "League with id $id",
    type = "League type $id",
    logo = "League logo $id",
    isFavourite = false,
    country = sampleCountry(id),
    season = "League season $id"

)

fun sampleCountry(id: Int) = CountryUi(
    code = "ES",
    name = "Country name $id",
    flag = "Country flag $id"
)

fun sampleLeagues(vararg ids: Int) = ids.map { sampleLeague(it) }


fun sampleTeam(id: Int) = TeamUi(
    id = id,
    name = "Team name $id",
    country = "Spain",
    logo = "Team logo $id",
    isSelected = false,
    founded = 1990,
    national = false,
    venue = sampleVenue(id)
)

fun sampleTeams(vararg ids: Int) = ids.map { sampleTeam(it) }

fun sampleVenue(id: Int) = VenueUi(
    name = "Venue name $id",
    id = id,
    city = "Venue city $id",
    address = "Venue address $id",
    capacity = 10000 + id,
    surface = "Venue surface $id",
    image = "Venue image $id"
)

fun sampleTeamsWithFixtures(vararg ids: Int, fixtures: Int) =
    sampleTeams(1, 2).map { team -> team to sampleFixtures(fixtures) }

fun sampleFixtures(fixturesNumber: Int) = (1..fixturesNumber).map {
    FixtureUi(
        date = "10/05/2025",
        venue = io.pdaa.chilenastats.domain.fixture.VenueUi(name = "Venue $it", id = it, city = "City $it"),
        id = it,
        timestamp = 1,
        timezone = "ECT",
        status = StatusUi(long = "long", short = "short", elapsed = null, extra = null),
        periods = PeriodsUi(first = null, second = null),
        referee = null

    )
}