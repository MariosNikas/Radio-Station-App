package com.example.radiostattion.data.repositories

import com.example.radiostattion.domain.models.RadioStation

interface IRadioStationRepository {
    suspend fun getAllStations(): List<RadioStation>
    suspend fun toggleFavorite(stationId : Long)
    suspend fun getAllFavouriteIds() : List<Long>
    suspend fun isFavourite(stationId : Long) : Boolean

}