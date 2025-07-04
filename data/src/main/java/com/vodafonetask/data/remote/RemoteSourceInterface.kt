package com.vodafonetask.data.remote

import com.vodafonetask.data.model.CitySearchResponse
import com.vodafonetask.data.model.WeatherResponse
import retrofit2.Response


interface RemoteSourceInterface {
    suspend fun getWeather(
        lat: Double,
        lon: Double,
    ): Response<WeatherResponse>

    suspend fun searchForCityByName(cityName:String):Response<List<CitySearchResponse>>
}