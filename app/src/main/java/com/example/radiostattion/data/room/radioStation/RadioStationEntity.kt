package com.example.radiostattion.data.room.radioStation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "radio_stations")
data class RadioStationEntity(
    @PrimaryKey val station_id: Long,
    val title: String,
    val description: String,
    val logo: String,
    val streaming_url: String,
    val status: Int,
    val updated: Long,
    val ordering: Int,
)