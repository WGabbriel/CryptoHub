package com.wgabbriel.cryptohub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

                var isFavorite by rememberSaveable { mutableStateOf(false) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { TopBarLabel(cryptoSymbol, cryptoName) },
                            navigationIcon = {
                                BackToMarket { finish() }
                            },
                            actions = {
                                FavoriteButton(isFavorite) {
                                    isFavorite = !isFavorite
                                    // TODO persistir favorito
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

@Composable
private fun BackToMarket(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Voltar"
        )
    }
}

@Composable
private fun TopBarLabel(symbol: String, name: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CryptoIcon(symbol)
        Spacer(Modifier.width(12.dp))
        Column() {
            Text(name, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.width(12.dp))
            Text(symbol, style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
private fun CryptoIcon(symbol: String) {
    Surface(
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = symbol,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FavoriteButton(isFavorite: Boolean, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
            contentDescription = "Favoritar",
            tint = if (isFavorite) Color.Yellow else Color.LightGray
        )
    }
}