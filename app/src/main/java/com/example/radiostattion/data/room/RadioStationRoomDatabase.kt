package com.example.radiostattion.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.radiostattion.data.room.favourite.FavoriteEntity
import com.example.radiostattion.data.room.favourite.FavoriteDAO
import com.example.radiostattion.data.room.radioStation.RadioStationDAO
import com.example.radiostattion.data.room.radioStation.RadioStationEntity

@Database(entities = [RadioStationEntity::class, FavoriteEntity::class], version = 2)
abstract class RadioStationRoomDatabase : RoomDatabase() {
    abstract fun radioStationDao(): RadioStationDAO
    abstract fun favoriteStationDao(): FavoriteDAO
}