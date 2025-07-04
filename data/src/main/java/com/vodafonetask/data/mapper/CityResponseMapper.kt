package com.vodafonetask.data.mapper

import com.vodafonetask.data.model.CitySearchResponse
import com.vodafonetask.domain.model.CitySearchResponseDomainModel

fun CitySearchResponse.toCityResponseDomainModel() = CitySearchResponseDomainModel(
    name = name ?: "",
    latitude = latitude ?: 0.0,
    longitude = longitude ?: 0.0,
    country = country ?: "",
    state = state ?: ""
)