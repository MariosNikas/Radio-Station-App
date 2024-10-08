package com.example.radiostattion.data.mapping

import com.example.radiostattion.data.room.radioStation.RadioStationEntity
import com.example.radiostattion.domain.models.RadioStation

fun RadioStationEntity.toRadioStation(): RadioStation = RadioStation(
    station_id,
    title,
    description,
    logo,
    streaming_url,
    status,
    updated,
    ordering,
)