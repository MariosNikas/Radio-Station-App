package com.example.radiostattion.data.repositories

import android.content.Context
import com.example.radiostattion.data.mapping.toRadioStation
import com.example.radiostattion.data.mapping.toRadioStationEntity
import com.example.radiostattion.data.network.APIService
import com.example.radiostattion.data.room.favourite.FavoriteDAO
import com.example.radiostattion.data.room.favourite.FavoriteEntity
import com.example.radiostattion.data.room.radioStation.RadioStationDAO
import com.example.radiostattion.data.room.radioStation.RadioStationEntity
import com.example.radiostattion.domain.models.RadioStation
import com.example.radiostattion.util.isConnectedToInternet
import javax.inject.Inject

class RadioStationRepository @Inject constructor(
    private val apiService: APIService,
    private val radioStationDAO: RadioStationDAO,
    private val favoriteDAO: FavoriteDAO,
    private val context: Context,
) : IRadioStationRepository {

    override suspend fun getAllStations(): List<RadioStation> {
        return if (!isConnectedToInternet(context)) {
            val localStations = radioStationDAO.getAllStations()
            localStations.map { it.toRadioStation() }
        } else {
            val remoteStations = apiService.getAllStations()
            radioStationDAO.insertAll(remoteStations.map { it.toRadioStationEntity() })
            remoteStations
        }
    }

    override suspend fun toggleFavorite(stationId: Long) {
        if (favoriteDAO.isFavorite(stationId)) {
            favoriteDAO.delete(FavoriteEntity(stationId))
        } else {
            favoriteDAO.insert(FavoriteEntity(stationId))
        }
    }

    override suspend fun getAllFavouriteIds(): List<Long> {
        return favoriteDAO.getAllFavoriteStationIds()
    }

    override suspend fun isFavourite(stationId: Long): Boolean {
        return favoriteDAO.isFavorite(stationId)
    }
}


