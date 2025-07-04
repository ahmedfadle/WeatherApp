package com.vodafonetask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vodafonetask.data.model.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM CityEntity")
    suspend fun getAllCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCity(city: CityEntity)
}