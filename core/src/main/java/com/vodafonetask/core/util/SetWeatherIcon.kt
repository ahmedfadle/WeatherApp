package com.vodafonetask.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun setIcon(id: String): Painter {
    return when (id) {
        "01d" -> Painters.clearSky
        "02d" -> Painters.fewClouds
        "03d" -> Painters.scatteredClouds
        "04d" -> Painters.brokenClouds
        "09d" -> Painters.showerRain
        "10d" -> Painters.rain
        "11d" -> Painters.thunderstorm
        "13d" -> Painters.snow
        "50d" -> Painters.mist
        "01n" -> Painters.nightClearSky
        "02n" -> Painters.nightFewClouds
        "03n" -> Painters.nightScatteredClouds
        "04n" -> Painters.nightBrokenClouds
        "09n" -> Painters.nightShowerRain
        "10n" -> Painters.nightRain
        "11n" -> Painters.nightThunderstorm
        "13n" -> Painters.nightSnow
        "50n" -> Painters.nightMist
        else -> Painters.loading
    }
}