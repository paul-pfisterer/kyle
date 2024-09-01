package com.pfisterer.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pfisterer.compose.ui.detail.DetailScreen
import com.pfisterer.compose.ui.home.HomeScreen
import com.pfisterer.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            ComposeTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = currentRoute == "home",
                                onClick = { navController.navigate("home") },
                                icon = { Icon(Icons.Default.Home, contentDescription = "home") }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "detail",
                                onClick = { navController.navigate("detail") },
                                icon = { Icon(Icons.Default.Build, contentDescription = "detail") }
                            )
                        }
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                HomeScreen(
                                    navigateToDetail = {
                                        navController.navigate("detail")
                                    }
                                )
                            }
                            composable("detail") {
                                DetailScreen(
                                    navigateToHome = {
                                        navController.navigate("home")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}