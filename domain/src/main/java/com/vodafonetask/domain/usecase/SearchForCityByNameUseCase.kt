package com.vodafonetask.domain.usecase

import com.vodafonetask.domain.repository.WeatherRepositoryInterface
import javax.inject.Inject

class SearchForCityByNameUseCase @Inject constructor(private val repo: WeatherRepositoryInterface) {
    suspend fun execute(citName: String) = repo.searchForCityByName(citName)
}