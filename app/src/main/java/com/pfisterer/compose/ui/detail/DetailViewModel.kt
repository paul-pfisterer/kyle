package com.pfisterer.compose.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pfisterer.compose.manager.BLEManager
import com.pfisterer.compose.manager.ConnectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

data class DetailState(val counter: Int = 0, val connectionState: ConnectionState = ConnectionState.DISCONNECTED)

class DetailViewModel : ViewModel() {
    private val manager = BLEManager()
    private var _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        manager.state.onEach {
            _state.value = _state.value.copy(connectionState = it)
        }.launchIn(viewModelScope)
    }

    fun incrementCounter() {
        _state.value = _state.value.copy(counter = _state.value.counter + 1)
    }

    fun decrementCounter() {
        _state.value = _state.value.copy(counter = _state.value.counter - 1)
    }

    fun connect() = manager.connect()

    fun disconnect() = manager.disconnect()
}