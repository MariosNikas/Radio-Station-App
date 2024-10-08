package com.example.radiostattion.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.radiostattion.data.repositories.IRadioStationRepository
import com.example.radiostattion.domain.models.RadioStation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val repository: IRadioStationRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _radioStations = MutableStateFlow<List<RadioStation>>(emptyList())
    val radioStations: StateFlow<List<RadioStation>> = _radioStations

    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _radioStations.value = repository.getAllStations()
                Log.d("test stations vm", _radioStations.value.toString())

            } catch (e: Exception) {
                //TODO handle exception
                Log.d("test stations vm", e.message.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }
}