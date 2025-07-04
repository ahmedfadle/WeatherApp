package com.vodafonetask.domain.repository

import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.domain.model.CitySearchResponseDomainModel
import com.vodafonetask.domain.model.WeatherDomainModel

interface WeatherRepositoryInterface {
    suspend fun getWeather(lat: Double, lon: Double): DataHolder<WeatherDomainModel>
    suspend fun searchForCityByName(cityName: String): DataHolder<List<CitySearchResponseDomainModel>>
}