package com.vodafonetask.city.state

import com.vodafonetask.domain.model.CityDomainModel

sealed class GetCityState {
    data class Display(
        var cityResponse: List<CityDomainModel> =
        emptyList(),
        val loading: Boolean = true
    ) : GetCityState()

    data class Failure(val errorMsg: String = "") : GetCityState()
}
