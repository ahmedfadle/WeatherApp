package com.vodafonetask.days_forecast.state

import com.vodafonetask.domain.model.WeatherDomainModel


sealed class GetWeatherState {
    data class Display(
        var weatherResponse: WeatherDomainModel =
            WeatherDomainModel
                (
                timezone = "",
                temperature = 0.0,
                feelsLike = 0.0,
                humidity = 0,
                windSpeed = 0.0,
                pressure = 0,
                description = "",
                uvi = 0.0,
                iconSet="",
                daily = emptyList()
            ),
        val loading: Boolean = true
    ) : GetWeatherState()

    data class Failure(val errorMsg: String = "") : GetWeatherState()
}