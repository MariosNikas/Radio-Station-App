package com.example.radiostattion.data.room.favourite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_stations")
data class FavoriteEntity(
    @PrimaryKey val stationId: Long
)