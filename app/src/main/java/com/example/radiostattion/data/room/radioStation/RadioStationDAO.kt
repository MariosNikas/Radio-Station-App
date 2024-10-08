package com.example.radiostattion.data.room.radioStation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RadioStationDAO {
    @Query("SELECT * FROM radio_stations")
    suspend fun getAllStations(): List<RadioStationEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(stations: List<RadioStationEntity>)
}