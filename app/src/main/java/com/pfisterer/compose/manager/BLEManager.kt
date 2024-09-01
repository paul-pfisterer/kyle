package com.pfisterer.compose.manager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ConnectionState {
    CONNECTED,
    DISCONNECTED
}

class BLEManager {
    private var _state = MutableStateFlow(ConnectionState.DISCONNECTED)
    val state = _state.asStateFlow()
    fun connect() {
        _state.value = ConnectionState.CONNECTED
    }

    fun disconnect() {
        _state.value = ConnectionState.DISCONNECTED
    }
}