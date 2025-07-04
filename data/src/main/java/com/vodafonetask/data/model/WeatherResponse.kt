package com.vodafonetask.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_offset") val timezoneOffset: Int,
    @SerializedName("current") val current: CurrentWeather,
    @SerializedName("minutely") val minutely: List<Minutely>,
    @SerializedName("hourly") val hourly: List<Hourly>,
    @SerializedName("daily") val daily: List<Daily>,
    @SerializedName("alerts") val alerts: List<Alert>?
) {
    data class CurrentWeather(
        @SerializedName("dt") val dateTime: Long,
        @SerializedName("sunrise") val sunrise: Long,
        @SerializedName("sunset") val sunset: Long,
        @SerializedName("temp") val temperature: Double,
        @SerializedName("feels_like") val feelsLike: Double,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("humidity") val humidity: Int,
        @SerializedName("dew_point") val dewPoint: Double,
        @SerializedName("uvi") val uvi: Double,
        @SerializedName("clouds") val clouds: Int,
        @SerializedName("visibility") val visibility: Int,
        @SerializedName("wind_speed") val windSpeed: Double,
        @SerializedName("wind_deg") val windDegree: Int,
        @SerializedName("wind_gust") val windGust: Double,
        @SerializedName("weather") val weather: List<Weather>
    )

    data class Minutely(
        @SerializedName("dt") val dateTime: Long,
        @SerializedName("precipitation") val precipitation: Double
    )

    data class Hourly(
        @SerializedName("dt") val dateTime: Long,
        @SerializedName("temp") val temperature: Double,
        @SerializedName("feels_like") val feelsLike: Double,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("humidity") val humidity: Int,
        @SerializedName("dew_point") val dewPoint: Double,
        @SerializedName("uvi") val uvi: Double,
        @SerializedName("clouds") val clouds: Int,
        @SerializedName("visibility") val visibility: Int,
        @SerializedName("wind_speed") val windSpeed: Double,
        @SerializedName("wind_deg") val windDegree: Int,
        @SerializedName("wind_gust") val windGust: Double,
        @SerializedName("weather") val weather: List<Weather>,
        @SerializedName("pop") val pop: Double
    )

    data class Daily(
        @SerializedName("dt") val dateTime: Double,
        @SerializedName("sunrise") val sunrise: Long,
        @SerializedName("sunset") val sunset: Long,
        @SerializedName("moonrise") val moonrise: Long,
        @SerializedName("moonset") val moonset: Long,
        @SerializedName("moon_phase") val moonPhase: Double,
        @SerializedName("summary") val summary: String,
        @SerializedName("temp") val temperature: Temperature,
        @SerializedName("feels_like") val feelsLike: FeelsLike,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("humidity") val humidity: Int,
        @SerializedName("dew_point") val dewPoint: Double,
        @SerializedName("wind_speed") val windSpeed: Double,
        @SerializedName("wind_deg") val windDegree: Int,
        @SerializedName("wind_gust") val windGust: Double,
        @SerializedName("weather") val weather: List<Weather>,
        @SerializedName("clouds") val clouds: Int,
        @SerializedName("pop") val pop: Double,
        @SerializedName("rain") val rain: Double?,
        @SerializedName("uvi") val uvi: Double
    )

    data class Temperature(
        @SerializedName("day") val day: Double,
        @SerializedName("min") val min: Double,
        @SerializedName("max") val max: Double,
        @SerializedName("night") val night: Double,
        @SerializedName("eve") val evening: Double,
        @SerializedName("morn") val morning: Double
    )

    data class FeelsLike(
        @SerializedName("day") val day: Double,
        @SerializedName("night") val night: Double,
        @SerializedName("eve") val evening: Double,
        @SerializedName("morn") val morning: Double
    )

    data class Weather(
        @SerializedName("id") val id: Int,
        @SerializedName("main") val main: String,
        @SerializedName("description") val description: String,
        @SerializedName("icon") val icon: String
    )

    data class Alert(
        @SerializedName("sender_name") val senderName: String,
        @SerializedName("event") val event: String,
        @SerializedName("start") val start: Long,
        @SerializedName("end") val end: Long,
        @SerializedName("description") val description: String,
        @SerializedName("tags") val tags: List<Any>
    )
}