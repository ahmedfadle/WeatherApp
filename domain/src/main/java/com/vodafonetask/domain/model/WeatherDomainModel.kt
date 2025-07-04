package com.vodafonetask.domain.model


data class WeatherDomainModel(
    val timezone: String,
    val temperature: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val uvi: Double,
    val windSpeed: Double,
    val description: String,
    val iconSet:String,
    val daily: List<DailyWeather>
){
    data class DailyWeather(
        val temp: Double,
        val feelsLike: Double,
        val description: String,
        val dateTime:Double,
        val iconSet:String
    )
}
