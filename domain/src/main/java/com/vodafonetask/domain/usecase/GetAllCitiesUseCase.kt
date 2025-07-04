package com.vodafonetask.domain.usecase

import com.vodafonetask.domain.repository.CityRepositoryInterface
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(private val repo: CityRepositoryInterface) {
    suspend fun execute() = repo.getAllCities()
}