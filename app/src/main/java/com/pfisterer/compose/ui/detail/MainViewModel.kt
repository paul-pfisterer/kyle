package com.pfisterer.compose.ui.detail

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import no.nordicsemi.android.kotlin.ble.core.ServerDevice
import no.nordicsemi.android.kotlin.ble.scanner.BleScanner
import no.nordicsemi.android.kotlin.ble.scanner.aggregator.BleScanResultAggregator

data class DetailState(val connected: Boolean = false, val devices: List<ServerDevice> = emptyList())

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    @SuppressLint("MissingPermission") //Permission check is done in MainActivity
    fun scan() {
        val aggregator = BleScanResultAggregator()
        BleScanner(getApplication()).scan()
            .map {
                aggregator.aggregateDevices(it)
            }
            .onEach {
               _state.value = _state.value.copy(devices = it)
            }
            .launchIn(viewModelScope)
    }

    fun connect(device: ServerDevice) {
        //Connect to the device and do stuff
        _state.value = _state.value.copy(connected = true)
    }
}