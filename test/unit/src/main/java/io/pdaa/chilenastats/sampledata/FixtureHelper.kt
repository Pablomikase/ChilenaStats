package io.pdaa.chilenastats.sampledata

import io.pdaa.chilenastats.domain.fixture.AwayUi
import io.pdaa.chilenastats.domain.fixture.ExtratimeUi
import io.pdaa.chilenastats.domain.fixture.FixtureContainerUi
import io.pdaa.chilenastats.domain.fixture.FixtureUi
import io.pdaa.chilenastats.domain.fixture.FulltimeUi
import io.pdaa.chilenastats.domain.fixture.GoalsUi
import io.pdaa.chilenastats.domain.fixture.HalftimeUi
import io.pdaa.chilenastats.domain.fixture.HomeUi
import io.pdaa.chilenastats.domain.fixture.LeagueUi
import io.pdaa.chilenastats.domain.fixture.PenaltyUi
import io.pdaa.chilenastats.domain.fixture.PeriodsUi
import io.pdaa.chilenastats.domain.fixture.ScoreUi
import io.pdaa.chilenastats.domain.fixture.StatusUi
import io.pdaa.chilenastats.domain.fixture.TeamsUi
import io.pdaa.chilenastats.domain.fixture.VenueUi

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

fun sampleFixtureVenue(id: Int) = VenueUi(
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

fun sampleFixtureLeague(id: Int) = LeagueUi(
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