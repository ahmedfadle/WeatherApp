package com.vodafonetask.current_weather.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.vodafonetask.core.R
import com.vodafonetask.core.util.Constants.DAYS_FORECAST_SCREEN
import com.vodafonetask.core.util.setIcon
import com.vodafonetask.current_weather.composables.CurrentWeatherIcon
import com.vodafonetask.current_weather.composables.StateBar
import com.vodafonetask.current_weather.composables.TopBar
import com.vodafonetask.current_weather.composables.WeatherTemp
import com.vodafonetask.current_weather.view_model.CurrentWeatherViewModel


@Composable
fun HomeScreen(
    navController: NavHostController,
    cityName: String,
    cityLat: String,
    cityLon: String
) {
    val viewModel: CurrentWeatherViewModel = hiltViewModel()

    LaunchedEffect(viewModel) {
        viewModel.getWeather(lat = cityLat.toDouble(), lon = cityLon.toDouble())
    }

    val weatherState by viewModel.stateGetWeather.collectAsStateWithLifecycle()

    val error by viewModel.getWeatherErrorState.collectAsStateWithLifecycle("")
    if (error.toString() != "") {
        Toast.makeText(LocalContext.current, error.toString(), Toast.LENGTH_SHORT).show()
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.homebg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (weatherState.loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            weatherState.weatherResponse?.let { weatherResult ->
                Column(modifier = Modifier.padding(top = 48.dp, start = 16.dp, end = 16.dp)) {
                    TopBar(weatherResult.timezone, cityName)
                    Spacer(modifier = Modifier.height(32.dp))
                    CurrentWeatherIcon(setIcon(id = weatherResult.iconSet ?: "01d"))
                    Spacer(modifier = Modifier.height(32.dp))
                    WeatherTemp(
                        temp = weatherResult.temperature.toInt().toString(),
                        feelLike = weatherResult.feelsLike.toInt().toString(),
                        weatherState = weatherResult.description
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                    StateBar(
                        humidity = "${weatherResult.humidity}%",
                        pressure = weatherResult.pressure.toString(),
                        uvIndex = weatherResult.uvi.toString(),
                        wind = "${weatherResult.windSpeed} m/s"
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                navController.navigate("$DAYS_FORECAST_SCREEN/$cityLat/$cityLon")
                            },
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Text(
                                text = "10 days forecast",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

