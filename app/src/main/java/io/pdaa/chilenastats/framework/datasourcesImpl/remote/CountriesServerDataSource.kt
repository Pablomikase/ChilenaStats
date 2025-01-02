package io.pdaa.chilenastats.framework.datasourcesImpl.remote

import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.domain.CountryUi
import io.pdaa.chilenastats.framework.models.remote.CountryRemoteResponse
import io.pdaa.chilenastats.framework.server.FootballDataService

class CountriesServerDataSource(private val footballDataService: FootballDataService) :
    CountriesRemoteDataSource {

    override suspend fun fetchCountries(): List<CountryUi> =
        footballDataService.fetchCountries()
            .response
            .map {
                it.asUiModel()
            }.filter { it.code != null }

}

private fun CountryRemoteResponse.asUiModel(): CountryUi = CountryUi(
    code = this.code,
    flag = this.flag,
    name = this.name
)
