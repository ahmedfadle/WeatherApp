package com.vodafonetask.domain.usecase

import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.domain.model.CitySearchResponseDomainModel
import com.vodafonetask.domain.repository.WeatherRepositoryInterface
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class SearchForCityByNameUseCaseTest {

    private lateinit var repositoryInterface: WeatherRepositoryInterface
    private lateinit var searchForCityByNameUseCase: SearchForCityByNameUseCase

    @Before
    fun setUp() {
        repositoryInterface = mockk()
        searchForCityByNameUseCase = SearchForCityByNameUseCase(repositoryInterface)
    }
    @Test
    fun `execute return a list of cities`()=runBlocking {
        //GIVEN
        val cityName = "Cairo"
        val cityResponse = listOf(
            CitySearchResponseDomainModel(
                name = cityName,
                latitude = 31.0409,
                longitude = 31.3785,
                state = "",
                country = ""
            )
        )
        coEvery { searchForCityByNameUseCase.execute(cityName) } returns DataHolder.Success(cityResponse)

        //WHEN
        val result = searchForCityByNameUseCase.execute(cityName)

        //THEN
        assertNotNull(result)
        assertEquals(cityResponse,result.data)
    }
}