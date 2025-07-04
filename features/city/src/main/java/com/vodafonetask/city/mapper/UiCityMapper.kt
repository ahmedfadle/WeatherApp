package com.vodafonetask.city.mapper

import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.model.CitySearchResponseDomainModel


fun CitySearchResponseDomainModel.toCityDomainModel() = CityDomainModel(
    cityName = "$name, $state, $country" , cityLat = latitude , cityLong = longitude
)