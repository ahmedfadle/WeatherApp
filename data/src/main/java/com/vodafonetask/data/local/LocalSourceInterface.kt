package com.vodafonetask.data.local

import com.vodafonetask.data.model.CityEntity

interface LocalSourceInterface {
    suspend fun getAllCities(): List<CityEntity>
    suspend fun insertCities(city: CityEntity)
}