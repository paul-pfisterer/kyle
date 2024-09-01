package com.pfisterer.compose.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = viewModel(),
    navigateToHome: () -> Unit
) {
    val state = detailViewModel.state.collectAsState()
    Scaffold {
        Row(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { detailViewModel.decrementCounter() }) {
                Text("-")
            }
            Text(text = state.value.counter.toString(), modifier = Modifier.padding(16.dp))
            Button(onClick = { detailViewModel.incrementCounter() }) {
                Text("+")
            }
        }
    }
}