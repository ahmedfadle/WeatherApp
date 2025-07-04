package com.vodafonetask.city.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vodafonetask.city.state.GetCityState
import com.vodafonetask.city.state.StateSearchForCityByName
import com.vodafonetask.core.util.DataHolder
import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.usecase.GetAllCitiesUseCase
import com.vodafonetask.domain.usecase.InsertCityUseCase
import com.vodafonetask.domain.usecase.SearchForCityByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val searchForCityByNameUseCase: SearchForCityByNameUseCase,
    private val getAllCitiesUseCase: GetAllCitiesUseCase,
    private val insertCityUseCase: InsertCityUseCase,
) : ViewModel() {

    private val _searchForCityByNameState: MutableStateFlow<StateSearchForCityByName.Display> =
        MutableStateFlow(StateSearchForCityByName.Display())
    val searchForCityByNameState = _searchForCityByNameState.asStateFlow()
    private val _searchForCityByNameErrorState: MutableSharedFlow<StateSearchForCityByName.Failure> =
        MutableSharedFlow()
    val searchForCityByNameErrorState = _searchForCityByNameErrorState.asSharedFlow()

    private val _stateGetCities: MutableStateFlow<GetCityState.Display> =
        MutableStateFlow(GetCityState.Display())
    val stateGetCities = _stateGetCities.asStateFlow()
    private val _getCitiesErrorState: MutableSharedFlow<GetCityState.Failure> =
        MutableSharedFlow()
    val getCitiesErrorState = _getCitiesErrorState.asSharedFlow()

    fun searchForCityByName(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = searchForCityByNameUseCase.execute(cityName)) {
                is DataHolder.Failure -> {
                    _searchForCityByNameErrorState.emit(StateSearchForCityByName.Failure(result.error!!))
                }

                is DataHolder.Success -> {
                    result.data?.let {
                        _searchForCityByNameState.value =
                            StateSearchForCityByName.Display(it, loading = false)
                    }
                }
            }
        }
    }

    fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getAllCitiesUseCase.execute()) {
                is DataHolder.Failure -> {
                    _getCitiesErrorState.emit(GetCityState.Failure(result.error!!))
                }

                is DataHolder.Success -> {
                    result.data?.let {
                        _stateGetCities.value = GetCityState.Display(it, loading = false)
                    }
                }
            }
        }
    }

    fun insertCity(city: CityDomainModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertCityUseCase.execute(city)
        }
    }
}