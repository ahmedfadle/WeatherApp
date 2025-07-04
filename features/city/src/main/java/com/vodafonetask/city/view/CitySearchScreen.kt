package com.vodafonetask.city.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vodafonetask.city.R
import com.vodafonetask.city.composables.CityList
import com.vodafonetask.city.mapper.toCityDomainModel
import com.vodafonetask.city.view_model.CityViewModel
import com.vodafonetask.core.util.Painters
import com.vodafonetask.domain.model.CityDomainModel
import com.vodafonetask.domain.model.CitySearchResponseDomainModel

@Composable
fun CitySearchScreen(navController: NavHostController) {

    val viewModel: CityViewModel = hiltViewModel()
    viewModel.getCities()

    val state by viewModel.stateGetCities.collectAsStateWithLifecycle()
    val cityInfoState by viewModel.searchForCityByNameState.collectAsStateWithLifecycle()

    var city by remember { mutableStateOf(emptyList<CityDomainModel>()) }
    var cityInfo by remember { mutableStateOf(emptyList<CitySearchResponseDomainModel>()) }
    var text by remember { mutableStateOf("") }

    if (!cityInfoState.loading) {
        cityInfo = cityInfoState.cityInfoResponse
    }

    if (!state.loading) {
        city = state.cityResponse
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = Painters.homeBg,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp)) {
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                    if (text.isNotEmpty()) {
                        viewModel.searchForCityByName(text)
                    }
                },
                label = { Text(stringResource(R.string.enter_city_name)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (text.isNotEmpty()) {
                            viewModel.searchForCityByName(text)
                        }
                    }
                )

            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            if (text.isNotEmpty()) {

                val citySearchResponse = cityInfo.map {
                    it.toCityDomainModel()
                }
                CityList(
                    viewModel = viewModel,
                    navController = navController,
                    city = citySearchResponse
                )
            } else {
                CityList(viewModel = viewModel, navController = navController, city = city)
            }
        }
    }
}

