package com.vodafonetask.domain.repository

import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.domain.model.CityDomainModel

interface CityRepositoryInterface {
    suspend fun getAllCities(): DataHolder<List<CityDomainModel>>
    suspend fun insertCity(city: CityDomainModel)
}