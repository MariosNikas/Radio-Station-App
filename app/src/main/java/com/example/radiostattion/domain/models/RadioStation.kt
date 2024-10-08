package com.example.radiostattion.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RadioStation(
    val station_id: Long,
    val title: String,
    val description: String,
    val logo: String,
    val streaming_url: String,
    val status: Int,
    val updated: Long,
    val ordering: Int,
) : Parcelable
