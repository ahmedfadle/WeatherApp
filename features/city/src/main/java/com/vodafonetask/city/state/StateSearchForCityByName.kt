package com.vodafonetask.city.state

import com.vodafonetask.domain.model.CitySearchResponseDomainModel

sealed class StateSearchForCityByName {
    data class Display(
        var cityInfoResponse: List<CitySearchResponseDomainModel> =
            listOf(
                CitySearchResponseDomainModel(
                    name = "", latitude = 0.0, longitude = 0.0, country = "", state = ""
                )
            ),
        val loading: Boolean = true
    ) : StateSearchForCityByName()

    data class Failure(val errorMsg: String = "") : StateSearchForCityByName()
}