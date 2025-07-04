package com.vodafonetask.domain.usecase

import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.repository.CityRepositoryInterface
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class InsertCityUseCaseTest {

    private lateinit var repositoryInterface: CityRepositoryInterface
    private lateinit var insertCityUseCase: InsertCityUseCase

    @Before
    fun setUp() {
        repositoryInterface = mockk()
        insertCityUseCase = InsertCityUseCase(repositoryInterface)
    }

    @Test
    fun `execute should insert City`()=runBlocking {
        // Given
        val city = CityDomainModel("Cairo", 0.0, 0.0)
        coEvery { repositoryInterface.insertCity(city) } returns Unit

        // When
        insertCityUseCase.execute(city)

        // Then
        coVerify { repositoryInterface.insertCity(city) }
    }
}