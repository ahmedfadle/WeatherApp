package com.vodafonetask.core.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
fun formatDayOfWeek(timestamp: Double): String {
    val sdf = SimpleDateFormat("EEE,d MMM", Locale("en"))
    val calendar: Calendar = Calendar.getInstance()
    val currentDay = calendar.get(Calendar.DAY_OF_YEAR)
    calendar.timeInMillis = timestamp.toLong() * 1000
    return when (calendar.get(Calendar.DAY_OF_YEAR)) {
        currentDay -> "Today"
        currentDay + 1 -> "Tomorrow"
        else -> sdf.format(calendar.time).uppercase(Locale.ROOT)
    }
}