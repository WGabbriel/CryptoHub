package com.wgabbriel.cryptohub.ui.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.wgabbriel.cryptohub.model.Crypto
import java.math.BigDecimal

class MarketViewModel : ViewModel() {

    private val _cryptos = getCrypto().toMutableStateList()
    val cryptos
        get() = _cryptos.toList()
}

private fun getCrypto() = listOf(
    Crypto(
        id = "bitcoin",
        symbol = "BTC",
        name = "Bitcoin",
        description = "Principal cripto do mercado",
        currentPrice = BigDecimal("67950.23"),
        marketCap = BigDecimal("1345000000000"),
        priceChangePercentage24h = 2.15
    ),
    Crypto(
        id = "ethereum",
        symbol = "ETH",
        name = "Ethereum",
        description = "Rede voltada para smart contracts",
        currentPrice = BigDecimal("3580.41"),
        marketCap = BigDecimal("430000000000"),
        priceChangePercentage24h = -0.84
    ),
    Crypto(
        id = "binancecoin",
        symbol = "BNB",
        name = "BNB Chain",
        description = "Token utilitário da Binance",
        currentPrice = BigDecimal("418.77"),
        marketCap = BigDecimal("62000000000"),
        priceChangePercentage24h = 1.02
    ),
    Crypto(
        id = "solana",
        symbol = "SOL",
        name = "Solana",
        description = "Blockchain focada em alta performance",
        currentPrice = BigDecimal("156.38"),
        marketCap = BigDecimal("70000000000"),
        priceChangePercentage24h = 4.67
    ),
    Crypto(
        id = "ripple",
        symbol = "XRP",
        name = "Ripple",
        description = "Rede para pagamentos internacionais",
        currentPrice = BigDecimal("0.62"),
        marketCap = BigDecimal("34000000000"),
        priceChangePercentage24h = -1.45
    ),
    Crypto(
        id = "cardano",
        symbol = "ADA",
        name = "Cardano",
        description = "Blockchain baseada em pesquisa acadêmica",
        currentPrice = BigDecimal("0.78"),
        marketCap = BigDecimal("27000000000"),
        priceChangePercentage24h = 3.11
    ),
    Crypto(
        id = "dogecoin",
        symbol = "DOGE",
        name = "Dogecoin",
        description = "Meme coin com ampla comunidade",
        currentPrice = BigDecimal("0.17"),
        marketCap = BigDecimal("23000000000"),
        priceChangePercentage24h = 0.76
    ),
    Crypto(
        id = "tron",
        symbol = "TRX",
        name = "Tron",
        description = "Plataforma para dApps e conteúdo digital",
        currentPrice = BigDecimal("0.11"),
        marketCap = BigDecimal("9600000000"),
        priceChangePercentage24h = -0.34
    ),
    Crypto(
        id = "polkadot",
        symbol = "DOT",
        name = "Polkadot",
        description = "Rede multichain interoperável",
        currentPrice = BigDecimal("8.24"),
        marketCap = BigDecimal("10700000000"),
        priceChangePercentage24h = 5.02
    ),
    Crypto(
        id = "polygon",
        symbol = "MATIC",
        name = "Polygon",
        description = "Camada 2 para Ethereum",
        currentPrice = BigDecimal("0.89"),
        marketCap = BigDecimal("8200000000"),
        priceChangePercentage24h = -2.55
    ),
    Crypto(
        id = "litecoin",
        symbol = "LTC",
        name = "Litecoin",
        description = "Fork mais leve do Bitcoin",
        currentPrice = BigDecimal("95.11"),
        marketCap = BigDecimal("7000000000"),
        priceChangePercentage24h = 0.44
    ),
    Crypto(
        id = "chainlink",
        symbol = "LINK",
        name = "Chainlink",
        description = "Oráculos descentralizados",
        currentPrice = BigDecimal("17.35"),
        marketCap = BigDecimal("10100000000"),
        priceChangePercentage24h = 6.89
    ),
    Crypto(
        id = "stellar",
        symbol = "XLM",
        name = "Stellar",
        description = "Pagamentos globais rápidos",
        currentPrice = BigDecimal("0.14"),
        marketCap = BigDecimal("3900000000"),
        priceChangePercentage24h = -1.12
    ),
    Crypto(
        id = "vechain",
        symbol = "VET",
        name = "VeChain",
        description = "Supply chain inteligente",
        currentPrice = BigDecimal("0.035"),
        marketCap = BigDecimal("2500000000"),
        priceChangePercentage24h = 1.63
    ),
    Crypto(
        id = "cosmos",
        symbol = "ATOM",
        name = "Cosmos",
        description = "Rede de blockchains interoperáveis",
        currentPrice = BigDecimal("11.52"),
        marketCap = BigDecimal("4400000000"),
        priceChangePercentage24h = 2.98
    ),
    Crypto(
        id = "monero",
        symbol = "XMR",
        name = "Monero",
        description = "Foco total em privacidade",
        currentPrice = BigDecimal("152.66"),
        marketCap = BigDecimal("2800000000"),
        priceChangePercentage24h = -0.25
    ),
    Crypto(
        id = "aptos",
        symbol = "APT",
        name = "Aptos",
        description = "Blockchain baseada em Move",
        currentPrice = BigDecimal("9.73"),
        marketCap = BigDecimal("3800000000"),
        priceChangePercentage24h = 4.21
    ),
    Crypto(
        id = "arbitrum",
        symbol = "ARB",
        name = "Arbitrum",
        description = "Rollup otimista para Ethereum",
        currentPrice = BigDecimal("1.32"),
        marketCap = BigDecimal("3400000000"),
        priceChangePercentage24h = -3.47
    ),
    Crypto(
        id = "optimism",
        symbol = "OP",
        name = "Optimism",
        description = "Escalabilidade L2 para Ethereum",
        currentPrice = BigDecimal("2.11"),
        marketCap = BigDecimal("3200000000"),
        priceChangePercentage24h = 0.95
    ),
    Crypto(
        id = "hedera",
        symbol = "HBAR",
        name = "Hedera",
        description = "Governo corporativo e consenso hashgraph",
        currentPrice = BigDecimal("0.11"),
        marketCap = BigDecimal("3600000000"),
        priceChangePercentage24h = 1.57
    )
)