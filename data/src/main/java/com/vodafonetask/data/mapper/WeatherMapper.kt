package com.vodafonetask.data.mapper

import com.vodafonetask.data.model.WeatherResponse
import com.vodafonetask.domain.model.WeatherDomainModel


fun WeatherResponse.toWeatherDomainModel(): WeatherDomainModel = WeatherDomainModel(
    timezone = timezone,
    temperature = current.temperature,
    feelsLike = current.feelsLike,
    pressure = current.pressure,
    humidity = current.humidity,
    uvi = current.uvi,
    windSpeed = current.windSpeed,
    description = current.weather[0].description,
    iconSet = current.weather[0].icon,
    daily = daily.map { it.toWeatherDomainModelDaily() }
)

fun WeatherResponse.Daily.toWeatherDomainModelDaily(): WeatherDomainModel.DailyWeather = WeatherDomainModel.DailyWeather(
    temp = temperature.day,
    feelsLike = feelsLike.day,
    description = weather[0].description,
    dateTime = dateTime,
    iconSet = weather[0].icon

)
