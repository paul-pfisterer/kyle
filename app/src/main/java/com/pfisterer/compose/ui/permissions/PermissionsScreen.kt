package com.pfisterer.compose.ui.permissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfisterer.compose.ui.theme.AppButton
import com.pfisterer.compose.ui.theme.AppText

@Composable
fun PermissionsScreen(
    onGarantPermissions: () -> Unit
) {
    Scaffold {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(24.dp),
        ) {
            AppText(
                "You need to garant permissions to scan and connect with Bluetooth to use this app",
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
            AppButton(text = "Request permissions", modifier = Modifier.padding(top = 16.dp)) {
                onGarantPermissions()
            }
        }
    }
}