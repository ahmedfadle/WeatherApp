package com.vodafonetask.city

import com.vodafonetask.city.view_model.CityViewModel
import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.model.CitySearchResponseDomainModel
import com.vodafonetask.domain.usecase.GetAllCitiesUseCase
import com.vodafonetask.domain.usecase.InsertCityUseCase
import com.vodafonetask.domain.usecase.SearchForCityByNameUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CityViewModelTest {
    private lateinit var getAllCitiesUseCase: GetAllCitiesUseCase
    private lateinit var searchForCityByNameUseCase: SearchForCityByNameUseCase
    private lateinit var insertCityUseCase: InsertCityUseCase
    private lateinit var viewModel: CityViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        getAllCitiesUseCase = mockk()
        searchForCityByNameUseCase = mockk()
        insertCityUseCase = mockk()
        viewModel = CityViewModel(searchForCityByNameUseCase, getAllCitiesUseCase, insertCityUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchForCityByName should return city info`() = runTest {
        // Given
        val expected = listOf(CitySearchResponseDomainModel("Cairo", 0.0, 0.0, "", ""))
        coEvery { searchForCityByNameUseCase.execute("Cairo") } returns DataHolder.Success(expected)

        // When
        viewModel.searchForCityByName("Cairo")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(expected, viewModel.searchForCityByNameState.value.cityInfoResponse)
        coVerify { searchForCityByNameUseCase.execute("Cairo") }
    }

    @Test
    fun `searchForCityByName should return network error`() = runTest {
        testScope.launch {
            // Given
            val errorMessage = "Network error"
            coEvery { searchForCityByNameUseCase.execute("Cairo") } returns DataHolder.Failure(
                errorMessage
            )

            // When
            viewModel.searchForCityByName("Cairo")
            testDispatcher.scheduler.advanceUntilIdle()

            // Then
            val errorState =
                viewModel.searchForCityByNameErrorState.first() // Collect the first emitted value
            assertEquals(errorMessage, errorState.errorMsg)
            coVerify { searchForCityByNameUseCase.execute("Cairo") }
        }
    }

    @Test
    fun `getCities should return cities`() = runTest {
        // GIVEN
        val expected = listOf(CityDomainModel("Cairo", 0.0, 0.0))
        coEvery { getAllCitiesUseCase.execute() } returns DataHolder.Success(expected)

        // WHEN
        viewModel.getCities()
        testDispatcher.scheduler.advanceUntilIdle()

        // THEN
        assertEquals(expected, viewModel.stateGetCities.value.cityResponse)
        coVerify { getAllCitiesUseCase.execute() }
    }

    @Test
    fun `getCities should return error`() = runTest {
        testScope.launch {
            // GIVEN
            val errorMassage = "error"
            coEvery { getAllCitiesUseCase.execute() } returns DataHolder.Failure(errorMassage)

            // WHEN
            viewModel.getCities()
            testDispatcher.scheduler.advanceUntilIdle()

            // THEN
            val errorState = viewModel.getCitiesErrorState.first().errorMsg
            assertEquals(errorMassage, errorState)
            coVerify { getAllCitiesUseCase.execute() }
        }
    }

    @Test
    fun `insertCity should return success`() = runTest {
        // GIVEN
        val city = CityDomainModel("Cairo", 0.0, 0.0)
        coEvery { insertCityUseCase.execute(city) } returns Unit

        // WHEN
        viewModel.insertCity(city)
        testDispatcher.scheduler.advanceUntilIdle()

        // THEN
        coVerify { insertCityUseCase.execute(city) }
    }
}
