package com.vodafonetask.data.Repository

import com.vodafonetask.data.local.CityDao
import com.vodafonetask.data.local.LocalSourceImp
import com.vodafonetask.data.mapper.toCityEntity
import com.vodafonetask.domain.model.CityDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class CityRepositoryImpTest {
    private lateinit var cityRepositoryImp: CityRepositoryImp
    private lateinit var localSourceImp: LocalSourceImp

    @Before
    fun setUp() {
        localSourceImp = mockk()
        cityRepositoryImp = CityRepositoryImp(localSourceImp)
    }

    @Test
    fun `getAllCities() should return all cities from localSource`() = runBlocking {
        // Given
        val expected = listOf(
            CityDomainModel("Cairo", 5.5, 5.5),
            CityDomainModel("mansoura", 35.5, 37.0),
            CityDomainModel("new york", 0.0, 2.2)
        )
        coEvery { localSourceImp.getAllCities() } returns expected.map { it.toCityEntity() }

        // When
        val result = cityRepositoryImp.getAllCities().data

        // Then
        assertEquals(expected, result)

    }

    @Test
    fun `insertCity() should insert city to localSource`() = runBlocking {
        // Given
        val city = CityDomainModel("Cairo", 5.5, 5.5)
        coEvery { localSourceImp.insertCities(city.toCityEntity()) } returns Unit
        // When
        cityRepositoryImp.insertCity(city)
        // Then
        coVerify { localSourceImp.insertCities(city.toCityEntity()) }
    }
}