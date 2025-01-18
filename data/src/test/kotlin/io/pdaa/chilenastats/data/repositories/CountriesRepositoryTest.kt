package io.pdaa.chilenastats.data.repositories

import io.pdaa.chilenastats.data.datasources.local.CountriesLocalDataSource
import io.pdaa.chilenastats.data.datasources.remote.CountriesRemoteDataSource
import io.pdaa.chilenastats.data.datasources.remote.RegionDataSource
import io.pdaa.chilenastats.sampledata.sampleCountries
import io.pdaa.chilenastats.sampledata.sampleCountry
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CountriesRepositoryTest {

    @Mock
    lateinit var regionDataSource: RegionDataSource

    @Mock
    lateinit var remoteDataSource: CountriesRemoteDataSource

    @Mock
    lateinit var localDataSource: CountriesLocalDataSource

    private lateinit var countriesRepository: CountriesRepository

    private val localCountries = sampleCountries(1, 2, 3)
    private val remoteCountries = sampleCountries(4, 5, 6)

    @Before
    fun setUp() {
        countriesRepository =
            CountriesRepository(regionDataSource, remoteDataSource, localDataSource)
    }

    @Test
    fun `Load countries from local data source when available`() = runBlocking {
        whenever(localDataSource.countries).thenReturn(flowOf(localCountries))

        val result = countriesRepository.countries.first()

        assertEquals(localCountries, result)
    }

    @Test
    fun `Save countries from remote when local is empty`(): Unit = runBlocking {
        whenever(localDataSource.countries).thenReturn(flowOf(emptyList()))
        whenever(remoteDataSource.fetchCountries()).thenReturn(remoteCountries)

        countriesRepository.countries.first()

        verify(localDataSource).insertCountries(remoteCountries)
    }

    @Test
    fun `Update a country as selected`(): Unit = runBlocking {
        val sampleCountry = sampleCountry(3)

        countriesRepository.selectCountry(sampleCountry)

        verify(localDataSource).insertCountries(listOf(sampleCountry.copy(isSelected = sampleCountry.isSelected.not())))

    }
}