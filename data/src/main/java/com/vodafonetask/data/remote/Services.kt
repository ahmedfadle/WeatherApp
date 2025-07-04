package com.vodafonetask.data.remote

import com.vodafonetask.core.util.Constants
import com.vodafonetask.data.model.CitySearchResponse
import com.vodafonetask.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "en",
        @Query("appid") appId: String = Constants.API_KEY
    ): Response<WeatherResponse>

    @GET("/geo/1.0/direct")
    suspend fun searchForCityByName(
        @Query("q") cityName: String,
        @Query("limit") limit: Int = 5,
        @Query("appid") apiKey: String = Constants.API_KEY
    ): Response<List<CitySearchResponse>>
}