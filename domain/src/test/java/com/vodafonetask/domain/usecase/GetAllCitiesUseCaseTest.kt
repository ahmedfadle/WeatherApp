package com.vodafonetask.domain.usecase

import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.repository.CityRepositoryInterface
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class GetAllCitiesUseCaseTest {

    private lateinit var repositoryInterface: CityRepositoryInterface
    private lateinit var allCitiesUseCase: GetAllCitiesUseCase

    @Before
    fun setUp() {
        repositoryInterface = mockk()
        allCitiesUseCase = GetAllCitiesUseCase(repositoryInterface)
    }

    @Test
    fun `execute should return all cities`() = runBlocking{
        //GIVEN
        val expectedCities = listOf(CityDomainModel("Cairo",0.0,0.0))
        coEvery { repositoryInterface.getAllCities() } returns DataHolder.Success(expectedCities)

        //WHEN
        val result = allCitiesUseCase.execute()

        //THEN
        assertEquals(expectedCities, result.data)
    }
}