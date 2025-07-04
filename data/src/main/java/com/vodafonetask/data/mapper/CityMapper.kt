package com.vodafonetask.data.mapper

import com.vodafonetask.data.model.CityEntity
import com.vodafonetask.domain.model.CityDomainModel

fun CityEntity.toCityDomainModel() = CityDomainModel(
    cityName = cityName, cityLat = cityLat, cityLong = cityLong
)

fun CityDomainModel.toCityEntity() = CityEntity(
    cityName = cityName, cityLat = cityLat, cityLong = cityLong
)