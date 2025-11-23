package com.wgabbriel.cryptohub.model

import java.math.BigDecimal

data class Crypto(
    val id: String,
    val symbol: String,
    val name: String,
    val description: String?,
    val currentPrice: BigDecimal,
    val marketCap: BigDecimal,
    val priceChangePercentage24h: Double
)