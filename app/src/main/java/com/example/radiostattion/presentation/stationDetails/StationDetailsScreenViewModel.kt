package com.example.radiostattion.presentation.stationDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiostattion.data.repositories.IRadioStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationDetailsScreenViewModel @Inject constructor(
    private val repository: IRadioStationRepository
) : ViewModel() {

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun initialise(stationId: Long) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavourite(stationId)
        }
    }

    fun toggleFavourite(stationId: Long) {
        viewModelScope.launch {
            repository.toggleFavorite(stationId)
            _isFavorite.value = !_isFavorite.value
        }

    }
}