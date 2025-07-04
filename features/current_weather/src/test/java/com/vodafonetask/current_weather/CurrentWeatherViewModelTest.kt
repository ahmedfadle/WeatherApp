package com.vodafonetask.current_weather

import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.current_weather.view_model.CurrentWeatherViewModel
import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.model.WeatherDomainModel
import com.vodafonetask.domain.usecase.GetWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class CurrentWeatherViewModelTest {

    private lateinit var viewModel: CurrentWeatherViewModel
    private lateinit var weatherUseCase: GetWeatherUseCase
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        weatherUseCase = mockk()
        viewModel = CurrentWeatherViewModel(weatherUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getWeather should return expected weather`() = runTest {
        //GIVEN
        val city = CityDomainModel("Cairo", 0.0, 0.0)
        val expectedWeather = WeatherDomainModel(
            "",
            0.0,
            0.0,
            0,
            0,
            0.0,
            0.0,
            "",
            "",
            emptyList()
        )
        coEvery { weatherUseCase.execute(city.cityLat, city.cityLong) } returns DataHolder.Success(
            expectedWeather
        )

        //WHEN
        viewModel.getWeather(city.cityLat, city.cityLong)

        //THEN
        assertEquals(viewModel.stateGetWeather.value.weatherResponse, expectedWeather)
    }

    @Test
    fun `getWeather should return error`() = runTest {
        testScope.launch {
            //GIVEN
            val city = CityDomainModel("Cairo", 0.0, 0.0)
            val errorMessage = "Network error"
            coEvery {
                weatherUseCase.execute(
                    city.cityLat,
                    city.cityLong
                )
            } returns DataHolder.Failure(errorMessage)

            //WHEN
            viewModel.getWeather(city.cityLat, city.cityLong)
            testDispatcher.scheduler.advanceUntilIdle()

            //THEN
            assertEquals(viewModel.getWeatherErrorState.first().errorMsg, errorMessage)
        }
    }

}
