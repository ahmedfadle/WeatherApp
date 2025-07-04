package com.vodafonetask.days_forecast

import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.days_forecast.view_model.DaysForecastViewModel
import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.model.WeatherDomainModel
import com.vodafonetask.domain.usecase.GetWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DaysForecastViewModelTest {

    private lateinit var viewModel: DaysForecastViewModel
    private lateinit var weatherUseCase: GetWeatherUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        weatherUseCase = mockk()
        viewModel = DaysForecastViewModel(weatherUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getWeather should return expected weather`() = runBlocking {
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
        coEvery { weatherUseCase.execute(city.cityLat, city.cityLong) } returns DataHolder.Success(expectedWeather)

        //WHEN
        viewModel.getWeather(city.cityLat, city.cityLong)

        //THEN
        assertEquals(viewModel.stateGetWeather.value.weatherResponse, expectedWeather)
    }

    @Test
    fun `getWeather should return error`() = runBlocking (testDispatcher){
        //GIVEN
        val city = CityDomainModel("Cairo", 0.0, 0.0)
        val errorMessage = "Network error"
        coEvery { weatherUseCase.execute(city.cityLat, city.cityLong) } returns DataHolder.Failure(errorMessage)

        //WHEN
        viewModel.getWeather(city.cityLat, city.cityLong)

        //THEN
        assertEquals(viewModel.getWeatherErrorState.first().errorMsg, errorMessage)
    }
}