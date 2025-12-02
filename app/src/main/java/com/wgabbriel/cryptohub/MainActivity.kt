package com.wgabbriel.cryptohub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.wgabbriel.cryptohub.ui.nav.BottomNavBar
import com.wgabbriel.cryptohub.ui.nav.BottomNavItem
import com.wgabbriel.cryptohub.ui.nav.MainNavHost
import com.wgabbriel.cryptohub.ui.theme.CryptoHubTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            CryptoHubTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("CryptoHub")
                            },
                            actions = {
                                IconButton(onClick = {
                                    // todo Pesquisa
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search"
                                    )
                                }
                                IconButton(onClick = {
                                    //todo menu lateral
                                }) {
                                    Icon(
                                        imageVector =
                                            Icons.Default.Person,
                                        contentDescription = "Profile"
                                    )
                                }
                            }
                        )
                    },

                    bottomBar = {
                        val items = listOf(
                            BottomNavItem.MarketButton,
                            BottomNavItem.AlertButton,
                        )
                        BottomNavBar(navController = navController, items)
                    },
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainNavHost(navController = navController)
                    }
                }
            }
        }
    }
}