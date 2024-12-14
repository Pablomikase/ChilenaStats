package io.pdaa.chilenastats.domain

data class ResponseUi(
    val league: io.pdaa.chilenastats.domain.LeagueUi,
    val country: CountryUi? = null,
)
