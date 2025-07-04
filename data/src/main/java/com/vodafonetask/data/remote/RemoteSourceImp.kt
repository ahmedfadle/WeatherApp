package com.vodafonetask.data.remote

import com.vodafonetask.data.model.CitySearchResponse
import com.vodafonetask.data.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteSourceImp @Inject constructor(private val services: Services) :
    RemoteSourceInterface {
    override suspend fun getWeather(
        lat: Double,
        lon: Double,

    ): Response<WeatherResponse> {
        return services.getWeather(lat, lon)
    }

    override suspend fun searchForCityByName(cityName: String): Response<List<CitySearchResponse>> {
        return services.searchForCityByName(cityName)
    }

}