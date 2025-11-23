package com.wgabbriel.cryptohub.ui.nav


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.wgabbriel.cryptohub.ui.screen.MarketScreen
import com.wgabbriel.cryptohub.ui.screen.PortfolioScreen

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Route.Market) {
        composable<Route.Market> { MarketScreen() }
        composable<Route.Portfolio> { PortfolioScreen() }
    }
}