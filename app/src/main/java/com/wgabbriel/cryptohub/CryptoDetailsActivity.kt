package com.wgabbriel.cryptohub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.wgabbriel.cryptohub.model.Crypto
import com.wgabbriel.cryptohub.ui.screen.CryptoDetailsScreen
import com.wgabbriel.cryptohub.ui.theme.CryptoHubTheme
import com.wgabbriel.cryptohub.ui.viewmodel.CryptoDetailsViewModel
import java.math.BigDecimal

class CryptoDetailsActivity : ComponentActivity() {

    private val viewModel: CryptoDetailsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cryptoId = intent.getStringExtra("CRYPTO_ID") ?: ""
        val cryptoSymbol = intent.getStringExtra("CRYPTO_SYMBOL") ?: ""
        val cryptoName = intent.getStringExtra("CRYPTO_NAME") ?: ""
        val cryptoDescription = intent.getStringExtra("CRYPTO_DESCRIPTION") ?: ""
        val cryptoPrice = intent.getStringExtra("CRYPTO_PRICE") ?: "0"
        val cryptoMarketCap = intent.getStringExtra("CRYPTO_MARKET_CAP") ?: "0"
        val cryptoChange = intent.getDoubleExtra("CRYPTO_CHANGE", 0.0)

        val crypto = Crypto(
            id = cryptoId,
            symbol = cryptoSymbol,
            name = cryptoName,
            description = cryptoDescription,
            currentPrice = BigDecimal(cryptoPrice),
            marketCap = BigDecimal(cryptoMarketCap),
            priceChangePercentage24h = cryptoChange
        )

        viewModel.updateCrypto(crypto)

        setContent {
            CryptoHubTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(cryptoName) },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Voltar"
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        CryptoDetailsScreen(crypto = viewModel.crypto)
                    }
                }
            }
        }
    }
}