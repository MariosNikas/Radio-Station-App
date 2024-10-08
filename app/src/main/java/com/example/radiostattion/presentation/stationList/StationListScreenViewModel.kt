package com.example.radiostattion.presentation.stationList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiostattion.data.repositories.IRadioStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class StationListScreenViewModel @Inject constructor(
    private val repository: IRadioStationRepository
) : ViewModel() {

    private val _favourites = MutableStateFlow<List<Long>>(emptyList())
    val favourites: StateFlow<List<Long>> = _favourites

    init {
        viewModelScope.launch {
            _favourites.value = repository.getAllFavouriteIds()
        }
    }

    fun onFavouriteClicked(id: Long) {
        viewModelScope.launch {
            repository.toggleFavorite(id)
            _favourites.value = repository.getAllFavouriteIds()
        }
    }
}
