package com.pfisterer.compose

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.pfisterer.compose.ui.main.MainScreen
import com.pfisterer.compose.ui.permissions.PermissionsScreen
import com.pfisterer.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val multiplePermissionsState = rememberMultiplePermissionsState(
                listOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                )
            )

            ComposeTheme {
                if (multiplePermissionsState.allPermissionsGranted) {
                    MainScreen()
                } else {
                    PermissionsScreen(
                        onGarantPermissions = {
                            multiplePermissionsState.launchMultiplePermissionRequest()
                        }
                    )
                }
            }
        }
    }
}