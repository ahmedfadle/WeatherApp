package com.vodafonetask.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.vodafonetask.core.R


object Painters {
    val homeBg
        @Composable
        get() = painterResource(id = R.drawable.homebg)

    val daysTempBg
        @Composable
        get() = painterResource(id = R.drawable.days_temp_bg)

    val fewClouds
        @Composable
        get() = painterResource(id = R.drawable._02d) // Ensure this name matches your drawable file name

    val clearSky
        @Composable
        get() = painterResource(id = R.drawable.sun)

    val scatteredClouds
        @Composable
        get() = painterResource(id = R.drawable._03d)

    val brokenClouds
        @Composable
        get() = painterResource(id = R.drawable._04n)

    val showerRain
        @Composable
        get() = painterResource(id = R.drawable._09n)

    val rain
        @Composable
        get() = painterResource(id = R.drawable._10n)

    val thunderstorm
        @Composable
        get() = painterResource(id = R.drawable._11d)

    val snow
        @Composable
        get() = painterResource(id = R.drawable._13d)

    val mist
        @Composable
        get() = painterResource(id = R.drawable._50d)

    val nightClearSky
        @Composable
        get() = painterResource(id = R.drawable._01n)

    val nightFewClouds
        @Composable
        get() = painterResource(id = R.drawable._04n)

    val nightScatteredClouds
        @Composable
        get() = painterResource(id = R.drawable._04n)

    val nightBrokenClouds
        @Composable
        get() = painterResource(id = R.drawable._04n)

    val nightShowerRain
        @Composable
        get() = painterResource(id = R.drawable._09n)

    val nightRain
        @Composable
        get() = painterResource(id = R.drawable._09n)

    val nightThunderstorm
        @Composable
        get() = painterResource(id = R.drawable._11d)

    val nightSnow
        @Composable
        get() = painterResource(id = R.drawable._09n)

    val nightMist
        @Composable
        get() = painterResource(id = R.drawable._50d)

    val loading
        @Composable
        get() = painterResource(id = R.drawable._load)
}