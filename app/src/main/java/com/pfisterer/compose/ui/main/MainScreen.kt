package com.pfisterer.compose.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pfisterer.compose.ui.theme.AppButton
import com.pfisterer.compose.ui.theme.AppText
import no.nordicsemi.android.kotlin.ble.core.ServerDevice

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {

    val state = mainViewModel.state.collectAsState()
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (state.value.connected) {
                DeviceView()
            } else {
                ScanView(
                    toggleScan = { mainViewModel.toggleScan() },
                    devices = state.value.devices,
                    connect = { mainViewModel.connect(it) },
                    scanning = state.value.scanning
                )
            }
        }
    }
}

@Composable
fun ScanView(
    toggleScan: () -> Unit,
    scanning: Boolean,
    devices: List<ServerDevice>,
    connect: (ServerDevice) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            devices.forEach { device ->
                device.name?.let { deviceName ->
                    HorizontalDivider()
                    AppText(
                        text = deviceName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { connect(device) }
                            .padding(vertical = 24.dp, horizontal = 16.dp)
                    )
                }
            }
            if (devices.isNotEmpty())
                HorizontalDivider()
            if (scanning)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .size(24.dp),
                        strokeWidth = 2.dp,
                    )
                }
        }
        Spacer(modifier = Modifier.weight(1f))
        AppButton(text = if (scanning) "Stop Search" else "Search", onClick = toggleScan, modifier = Modifier.padding(24.dp))
    }
}

@Composable
fun DeviceView(
) {
}