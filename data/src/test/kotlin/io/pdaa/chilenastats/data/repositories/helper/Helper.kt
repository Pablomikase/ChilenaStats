package io.pdaa.chilenastats.data.repositories.helper

import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.domain.LeagueUi
import io.pdaa.chilenastats.domain.TeamUi
import io.pdaa.chilenastats.domain.VenueUi
import io.pdaa.chilenastats.domain.fixture.AwayUi
import io.pdaa.chilenastats.domain.fixture.ExtratimeUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import io.pdaa.chilenastats.domain.fixture.FixtureUi
import io.pdaa.chilenastats.domain.fixture.FulltimeUi
import io.pdaa.chilenastats.domain.fixture.GoalsUi
import io.pdaa.chilenastats.domain.fixture.HalftimeUi
import io.pdaa.chilenastats.domain.fixture.HomeUi
import io.pdaa.chilenastats.domain.fixture.PenaltyUi
import io.pdaa.chilenastats.domain.fixture.PeriodsUi
import io.pdaa.chilenastats.domain.fixture.ScoreUi
import io.pdaa.chilenastats.domain.fixture.StatusUi
import io.pdaa.chilenastats.domain.fixture.TeamsUi
import io.pdaa.chilenastats.domain.fixture.VenueUi as FixtureVenueUi
import io.pdaa.chilenastats.domain.fixture.LeagueUi as FixtureLeague

fun sampleCountries(vararg Ids: Int) = Ids.map {
    sampleCountry(it)
}

fun sampleCountry(id: Int) = CountryUi(
    code = "code$id",
    flag = "flag$id",
    name = "name$id",
    isSelected = id == 1
)

fun sampleLeagues(vararg Ids: Int) = Ids.map {
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
    isSelected = id == 1,
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

fun sampleFixture(id: Int) = FixtureUi(
    id = id,
    date = "date $id",
    referee = "Yo",
    timestamp = 1234,
    timezone = "asdf",
    venue = sampleFixtureVenue(id),
    periods = samplePeriod(),
    status = sampleStatus()
)

fun sampleFixtureVenue(id: Int) = FixtureVenueUi(
    id = id,
    name = "name $id",
    city = "city $id",
)

fun samplePeriod() = PeriodsUi(
    first = 1,
    second = 2
)

fun sampleStatus() = StatusUi(
    long = "long",
    short = "short",
    elapsed = 1,
    extra = 2
)

fun sampleFixtures(vararg id: Int) = id.map { sampleFixture(it) }

fun sampleFixtureContainer(id: Int) = FixtureContainerUi(
    fixture = sampleFixture(id),
    goals = sampleGoals(),
    league = sampleFixtureLeague(id),
    score = sampleScore(),
    teams = sampleTeamsUi()
)

fun sampleGoals() = GoalsUi(
    away = 1,
    home = 2
)

fun sampleFixtureLeague(id: Int) = FixtureLeague(
    id = id,
    name = "name",
    logo = "logo",
    country = "country",
    season = 2024,
    flag = "flag",
    round = "asdf"
)

fun sampleScore() = ScoreUi(
    extratime = ExtratimeUi(away = 1, home = 2),
    fulltime = FulltimeUi(1,2),
    halftime = HalftimeUi(1,2),
    penalty = PenaltyUi(null, null)
)

fun sampleTeamsUi() = TeamsUi(
    away = AwayUi(id = 1, name = "name", logo = "logo", winner = false),
    home = HomeUi(id = 2, name = "name", logo = "logo", winner = false)
)

fun sampleFixtureContainer(vararg id: Int) = id.map { sampleFixtureContainer(it) }
