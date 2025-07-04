package com.vodafonetask.data.Repository

import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.data.mapper.toCityResponseDomainModel
import com.vodafonetask.data.mapper.toWeatherDomainModel
import com.vodafonetask.data.remote.RemoteSourceInterface
import com.vodafonetask.domain.model.CitySearchResponseDomainModel
import com.vodafonetask.domain.model.WeatherDomainModel
import com.vodafonetask.domain.repository.WeatherRepositoryInterface
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val remoteSourceInterface: RemoteSourceInterface
) :
    WeatherRepositoryInterface {
    override suspend fun getWeather(lat: Double, lon: Double): DataHolder<WeatherDomainModel> {
        remoteSourceInterface.getWeather(
            lat = lat,
            lon = lon
        ).also { result ->
            return if (result.isSuccessful) {
                result.body()?.let {
                    DataHolder.Success(it.toWeatherDomainModel())
                } ?: DataHolder.Failure("No body in response")
            } else {
                DataHolder.Failure(result.message())
            }
        }
    }

    override suspend fun searchForCityByName(cityName: String): DataHolder<List<CitySearchResponseDomainModel>> {
        remoteSourceInterface.searchForCityByName(cityName).also { result ->
            return if (result.isSuccessful) {
                result.body()?.let {
                    DataHolder.Success(it.map { cityResponse ->
                        cityResponse.toCityResponseDomainModel()
                    })
                } ?: DataHolder.Failure("No body in response")
            } else {
                DataHolder.Failure(result.message())
            }
        }
    }

}