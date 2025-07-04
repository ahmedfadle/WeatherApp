package com.vodafonetask.days_forecast.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vodafonetask.core.R
import com.vodafonetask.core.util.formatDayOfWeek
import com.vodafonetask.core.util.setIcon
import com.vodafonetask.days_forecast.composables.WeatherListItems
import com.vodafonetask.days_forecast.view_model.DaysForecastIntent
import com.vodafonetask.days_forecast.view_model.DaysForecastViewModel
import com.vodafonetask.domain.model.WeatherDomainModel

@Composable
fun DaysTempScreen(
    cityLat: String,
    cityLon: String
) {

    val viewModel: DaysForecastViewModel = hiltViewModel()
    LaunchedEffect(viewModel) {
        viewModel.processIntent(
            DaysForecastIntent.Load,
            lat = cityLat.toDouble(),
            lon = cityLon.toDouble()
        )
    }
    val weatherState = viewModel.stateGetWeather.collectAsStateWithLifecycle()


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.days_temp_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(modifier = Modifier.padding(top = 48.dp, start = 16.dp, end = 16.dp)) {
            Text(
                text = "8 days forecast",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                itemsIndexed(
                    weatherState.value.weatherResponse.daily
                ) { _, item ->
                    Column(modifier = Modifier.padding(top = 16.dp)) {
                        WeatherListItems(
                            date = formatDayOfWeek(item.dateTime),
                            icon = setIcon(id = item.iconSet), // Adjust icon based on item
                            temp = "${item.temp.toInt()}°C",
                            description = item.description
                        )
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .background(Color.Gray)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
