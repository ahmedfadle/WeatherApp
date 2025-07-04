package com.vodafonetask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(
    @PrimaryKey
    val cityName: String,
    val cityLat: Double,
    val cityLong: Double
)