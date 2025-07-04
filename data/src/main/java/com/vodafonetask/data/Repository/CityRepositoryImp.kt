package com.vodafonetask.data.Repository

import android.util.Log
import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.data.local.LocalSourceInterface
import com.vodafonetask.data.mapper.toCityDomainModel
import com.vodafonetask.data.mapper.toCityEntity
import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.repository.CityRepositoryInterface
import javax.inject.Inject

class CityRepositoryImp @Inject constructor(
    private val localSourceInterface: LocalSourceInterface
) : CityRepositoryInterface {
    override suspend fun getAllCities(): DataHolder<List<CityDomainModel>> {
        return try {
            val cityEntities = localSourceInterface.getAllCities()
            val cityDomainModels = cityEntities.map { it.toCityDomainModel() }
            DataHolder.Success(cityDomainModels)
        } catch (e: Exception) {
            Log.e("okhttp", "getAllCities: ${e.message}")
            DataHolder.Failure(e.message ?: "Unknown error")
        }
    }

    override suspend fun insertCity(city: CityDomainModel) {
        localSourceInterface.insertCities(city.toCityEntity())
    }
}