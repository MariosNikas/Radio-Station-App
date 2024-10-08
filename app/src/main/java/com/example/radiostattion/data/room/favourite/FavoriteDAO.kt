package com.example.radiostattion.data.room.favourite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteStation: FavoriteEntity)

    @Delete
    suspend fun delete(favoriteStation: FavoriteEntity)

    @Query("SELECT * FROM favorite_stations")
    suspend fun getAllFavoriteStationIds(): List<Long>

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_stations WHERE stationId = :stationId)")
    suspend fun isFavorite(stationId: Long): Boolean
}