package com.vodafonetask.city.state

import com.vodafonetask.domain.model.CitySearchResponseDomainModel

sealed class StateSearchForCityByName {
    data class Display(
        val cityInfoResponse: List<CitySearchResponseDomainModel> = emptyList(),
        val loading: Boolean = true
    ) : StateSearchForCityByName()

    data class Failure(val errorMsg: String = "") : StateSearchForCityByName()
}