package com.wgabbriel.cryptohub.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.wgabbriel.cryptohub.R
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Market : Route

    @Serializable
    data object Portfolio : Route

}

sealed class BottomNavItem(
    val title: String,
    val icon: @Composable () -> ImageVector,
    val route: Route
) {
    data object MarketButton :
        BottomNavItem(
            "Mercado",
            { ImageVector.vectorResource(id = R.drawable.trending_up) },
            Route.Market
        )

    data object PortfolioButton :
        BottomNavItem(
            "Portfólio",
            { ImageVector.vectorResource(id = R.drawable.wallet) },
            Route.Portfolio
        )

}