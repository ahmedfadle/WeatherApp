package com.vodafonetask.domain.usecase

import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.repository.CityRepositoryInterface
import javax.inject.Inject

class InsertCityUseCase @Inject constructor(private val repo: CityRepositoryInterface) {
    suspend fun execute(city: CityDomainModel) = repo.insertCity(city)
}