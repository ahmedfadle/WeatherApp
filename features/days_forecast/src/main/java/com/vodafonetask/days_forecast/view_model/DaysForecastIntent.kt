package com.vodafonetask.days_forecast.view_model

sealed class DaysForecastIntent {
    data object Load : DaysForecastIntent()
}