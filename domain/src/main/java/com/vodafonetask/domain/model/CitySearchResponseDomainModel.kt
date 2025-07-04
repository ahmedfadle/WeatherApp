package com.vodafonetask.domain.model

data class CitySearchResponseDomainModel(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val state: String
)
