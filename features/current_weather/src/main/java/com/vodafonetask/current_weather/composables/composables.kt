package com.vodafonetask.current_weather.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(date: String, city: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = date, color = Color.White)
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = city,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CurrentWeatherIcon(icon: Painter) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun WeatherTemp(temp: String, feelLike: String, weatherState: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$temp°C",
            style = TextStyle(
                color = Color.White,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.size(24.dp))
        FeelLikeText("$feelLike°C")
        Spacer(modifier = Modifier.size(48.dp))
        Text(
            text = weatherState,
            style = TextStyle(color = Color.White, fontSize = 14.sp),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun FeelLikeText(temp: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "feel like",
            style = TextStyle(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Light)
        )
        Text(
            text = temp,
            style = TextStyle(color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Light)
        )
    }
}


@Composable
fun StateBar(humidity: String, pressure: String, uvIndex: String, wind: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(modifier = Modifier.width(150.dp)) {
            StateBarValue(state = "Humidity", value = humidity)
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
            StateBarValue(state = "Pressure", value = pressure)
        }
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(Color.Gray)
        )
        Column(modifier = Modifier.width(150.dp)) {
            StateBarValue(state = "UV index", value = uvIndex)
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
            StateBarValue(state = "Wind", value = wind)
        }
    }
}

@Composable
fun StateBarValue(state: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
    ) {
        Text(text = state)
        Text(text = value, fontWeight = FontWeight.Bold)
    }
}