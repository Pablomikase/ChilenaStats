package io.pdaa.chilenastats.framework.datasourcesImpl.local

import io.pdaa.chilenastats.data.datasources.local.FixturesLocalDataSource
import io.pdaa.chilenastats.framework.models.database.fixture.AwayDB
import io.pdaa.chilenastats.framework.models.database.fixture.ExtratimeDB
import io.pdaa.chilenastats.framework.models.database.fixture.FixtureContainerDB
import io.pdaa.chilenastats.framework.models.database.fixture.FixtureDB
import io.pdaa.chilenastats.framework.models.database.fixture.FulltimeDB
import io.pdaa.chilenastats.framework.models.database.fixture.GoalsDB
import io.pdaa.chilenastats.framework.models.database.fixture.HalftimeDB
import io.pdaa.chilenastats.framework.models.database.fixture.HomeDB
import io.pdaa.chilenastats.framework.models.database.fixture.LeagueDB
import io.pdaa.chilenastats.framework.models.database.fixture.PenaltyDB
import io.pdaa.chilenastats.framework.models.database.fixture.PeriodsDB
import io.pdaa.chilenastats.framework.models.database.fixture.ScoreDB
import io.pdaa.chilenastats.framework.models.database.fixture.StatusDB
import io.pdaa.chilenastats.framework.models.database.fixture.TeamsDB
import io.pdaa.chilenastats.framework.models.database.fixture.VenueDB
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
import io.pdaa.chilenastats.framework.database.dao.FixturesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FixturesRoomDataSource(private val fixturesDao: FixturesDao) : FixturesLocalDataSource {

    override val fixtures: Flow<List<FixtureContainerUi>> = fixturesDao.getFixtures().map { it.asUiModel() }

    override suspend fun insertFixtures(fixtures: List<FixtureContainerUi>, teamOwnerId: Int) {
        fixturesDao.insertFixtures(fixtures.asDBModel(teamOwnerId))
    }

    override fun getFixturesByTeam(teamId: Int): Flow<List<FixtureContainerUi>> {
        return fixturesDao.getFixturesByTeam(teamId).map {
            it.asUiModel()
        }
    }

}

//From DB to UI
private fun List<FixtureContainerDB>.asUiModel(): List<FixtureContainerUi> = map { it.asUiModel() }

private fun FixtureContainerDB.asUiModel(): FixtureContainerUi = FixtureContainerUi(
    fixture = this.fixture.asUiModel(),
    league = this.league.asUiModel(),
    goals = this.goals.asUiModel(),
    score = this.score.asUiModel(),
    teams = this.teams.asUiModel()
)

private fun LeagueDB.asUiModel() = LeagueUi(
    country = leagueCountry,
    flag = leagueFlag,
    id = leagueId,
    logo = leagueLogo,
    name = leagueName,
    round = leagueRound,
    season = leagueSeason
)

private fun FixtureDB.asUiModel() = FixtureUi(
    id = id,
    date = date,
    periods = periods.asUiModel(),
    referee = referee,
    status = status.asUiModel(),
    timestamp = timestamp,
    timezone = timezone,
    venue = venue.asUiModel()
)

private fun PeriodsDB.asUiModel() = PeriodsUi(
    first = periodFirst,
    second = periodSecond
)

private fun StatusDB.asUiModel() = StatusUi(
    long = statusLong,
    short = statusShort,
    elapsed = statusElapsed,
    extra = statusExtra
)

private fun VenueDB.asUiModel() = VenueUi(
    city = venueCity,
    id = venueId,
    name = venueName
)

private fun GoalsDB.asUiModel() = GoalsUi(
    away = goalsAway,
    home = goalsHome
)

private fun ScoreDB.asUiModel() = ScoreUi(
    extratime = extratime.asUiModel(),
    fulltime = fulltime.asUiModel(),
    halftime = halftime.asUiModel(),
    penalty = penalty.asUiModel()
)

private fun ExtratimeDB.asUiModel() = ExtratimeUi(
    away = extratimeAway,
    home = extratimeHome
)

private fun FulltimeDB.asUiModel() = FulltimeUi(
    away = fulltimeAway,
    home = fulltimeHome
)

private fun HalftimeDB.asUiModel() = HalftimeUi(
    away = halftimeAway,
    home = halftimeHome
)

private fun PenaltyDB.asUiModel() = PenaltyUi(
    away = penaltyAway,
    home = penaltyHome
)

private fun TeamsDB.asUiModel() = TeamsUi(
    away = teamAway.asUiModel(),
    home = teamHome.asUiModel()
)

private fun AwayDB.asUiModel() = AwayUi(
    id = teamAwayId,
    name = teamAwayName,
    logo = teamAwayLogo,
    winner = teamAwayWinner
)

private fun HomeDB.asUiModel() = HomeUi(
    id = teamHomeId,
    name = teamHomeName,
    logo = teamHomeLogo,
    winner = teamHomeWinner
)

//From UI to DB
private fun List<FixtureContainerUi>.asDBModel(teamOwnerId: Int): List<FixtureContainerDB> = map { it.asDBModel(teamOwnerId) }

private fun FixtureContainerUi.asDBModel(teamOwnerId: Int): FixtureContainerDB = FixtureContainerDB(
    teamOwnerId = teamOwnerId,
    fixture = this.fixture.asDBModel(),
    league = this.league.asDBModel(),
    goals = this.goals.asDBModel(),
    score = this.score.asDBModel(),
    teams = this.teams.asDBModel(),
)

private fun LeagueUi.asDBModel() = LeagueDB(
    leagueCountry = country,
    leagueFlag = flag,
    leagueId = id,
    leagueLogo = logo,
    leagueName = name,
    leagueRound = round,
    leagueSeason = season
)

private fun FixtureUi.asDBModel() = FixtureDB(
    id = id,
    date = date,
    periods = periods.asDBModel(),
    referee = referee,
    status = status.asDBModel(),
    timestamp = timestamp,
    timezone = timezone,
    venue = venue.asDBModel()
)

private fun PeriodsUi.asDBModel() = PeriodsDB(
    periodFirst = first,
    periodSecond = second
)

private fun StatusUi.asDBModel() = StatusDB(
    statusLong = long,
    statusShort = short,
    statusElapsed = elapsed,
    statusExtra = extra
)

private fun VenueUi.asDBModel() = VenueDB(
    venueCity = city,
    venueId = id,
    venueName = name
)

private fun GoalsUi.asDBModel() = GoalsDB(
    goalsAway = away,
    goalsHome = home
)

private fun ScoreUi.asDBModel() = ScoreDB(
    extratime = extratime.asDBModel(),
    fulltime = fulltime.asDBModel(),
    halftime = halftime.asDBModel(),
    penalty = penalty.asDBModel()
)

private fun ExtratimeUi.asDBModel() = ExtratimeDB(
    extratimeAway = away,
    extratimeHome = home
)

private fun FulltimeUi.asDBModel() = FulltimeDB(
    fulltimeAway = away,
    fulltimeHome = home
)

private fun HalftimeUi.asDBModel() = HalftimeDB(
    halftimeAway = away,
    halftimeHome = home
)

private fun PenaltyUi.asDBModel() = PenaltyDB(
    penaltyAway = away,
    penaltyHome = home
)

private fun TeamsUi.asDBModel() = TeamsDB(
    teamAway = away.asDBModel(),
    teamHome = home.asDBModel()
)

private fun AwayUi.asDBModel() = AwayDB(
    teamAwayId = id,
    teamAwayName = name,
    teamAwayLogo = logo,
    teamAwayWinner = winner
)

private fun HomeUi.asDBModel() = HomeDB(
    teamHomeId = id,
    teamHomeName = name,
    teamHomeLogo = logo,
    teamHomeWinner = winner
)