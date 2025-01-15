package io.pdaa.chilenastats.data.repositories.helper

import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.domain.LeagueUi

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
    LeagueUi(
        id = it,
        name = "name$it",
        isFavourite = it % 2 == 0,
        country = sampleCountry(it),
        type = "type$it",
        logo = "logo$it",
        season = "season$it"
    )
}