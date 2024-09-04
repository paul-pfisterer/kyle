package com.pfisterer.compose

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.pfisterer.compose.ui.detail.MainScreen
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
                    Scaffold {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Text("Garant permissions to continue", modifier = Modifier.padding(it))
                            Button(onClick = { multiplePermissionsState.launchMultiplePermissionRequest() }) {
                                Text("Request permissions")
                            }
                        }
                    }
                }
            }
        }
    }
}