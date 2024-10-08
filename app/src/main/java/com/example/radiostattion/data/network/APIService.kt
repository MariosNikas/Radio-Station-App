package com.example.radiostattion.data.network

import com.example.radiostattion.domain.models.RadioStation
import retrofit2.http.GET

interface APIService {
    @GET("stations.json")
    suspend fun getAllStations(): List<RadioStation>
}