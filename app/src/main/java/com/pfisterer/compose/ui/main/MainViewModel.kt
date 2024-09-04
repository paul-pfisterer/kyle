package com.pfisterer.compose.ui.main

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import no.nordicsemi.android.kotlin.ble.core.ServerDevice
import no.nordicsemi.android.kotlin.ble.scanner.BleScanner
import no.nordicsemi.android.kotlin.ble.scanner.aggregator.BleScanResultAggregator

data class MainState(
    val connected: Boolean = false,
    val devices: List<ServerDevice> = emptyList(),
    val scanning: Boolean = false,
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
    private var scanJob: Job? = null

    @SuppressLint("MissingPermission") //Permission check is done in MainActivity
    fun scan() {
        _state.value = _state.value.copy(scanning = true)
        val aggregator = BleScanResultAggregator()
        scanJob = BleScanner(getApplication()).scan()
            .map {
                aggregator.aggregateDevices(it)
            }
            .onEach {
                _state.value = _state.value.copy(devices = it)
            }
            .launchIn(viewModelScope)
    }

    fun cancelScan() {
        scanJob?.cancel()
        _state.value = _state.value.copy(scanning = false)
    }

    fun connect(device: ServerDevice) {
        //Connect to the device and do stuff
        scanJob?.cancel()
        _state.value = _state.value.copy(connected = true, scanning = false)
    }
}