package com.pfisterer.compose.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import no.nordicsemi.android.kotlin.ble.core.ServerDevice

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {

    val state = mainViewModel.state.collectAsState()
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (state.value.connected) {
                DeviceScreen()
            } else {
                ConnectScreen(
                    scan = { mainViewModel.scan() },
                    devices = state.value.devices,
                    connect = { mainViewModel.connect(it) },
                    scanning = state.value.scanning
                )
            }
        }
    }
}

@Composable
fun ConnectScreen(
    scan: () -> Unit,
    scanning: Boolean,
    devices: List<ServerDevice>,
    connect: (ServerDevice) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Connect to device")
        if (!scanning) {
            Button(onClick = scan) {
                Text(text = "Scan")
            }
        } else {
            CircularProgressIndicator()
        }
        devices.forEach { device ->
            device.name?.let { deviceName ->
                Button(onClick = { connect(device) }) {
                    Text(text = "Connect to $deviceName")
                }
            }
        }
    }
}

@Composable
fun DeviceScreen(
) {
    Text(text = "Connected to a Device")
}