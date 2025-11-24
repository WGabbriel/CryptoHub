@file:OptIn(ExperimentalMaterial3Api::class)

package com.wgabbriel.cryptohub.ui.screen

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wgabbriel.cryptohub.CryptoDetailsActivity
import com.wgabbriel.cryptohub.model.Crypto
import com.wgabbriel.cryptohub.ui.viewmodel.MarketViewModel
import java.text.NumberFormat
import java.util.Locale


@Composable
fun MarketScreen(viewModel: MarketViewModel = viewModel()) {
    val cryptos = viewModel.cryptos
    MarketContent(cryptos)
}

@Composable
private fun MarketContent(cryptos: List<Crypto>) {
    val currencyFormatter = remember {
        NumberFormat.getCurrencyInstance(Locale.US).apply { maximumFractionDigits = 2 }
    }
    val activity = LocalActivity.current as Activity

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cryptos, key = { it.id }) { crypto ->
            CryptoMarketItem(crypto, currencyFormatter, onclick = {
                activity.startActivity(
                    Intent(
                        activity,
                        CryptoDetailsActivity::class.java
                    ).apply {
                        putExtra("CRYPTO_ID", crypto.id)
                        putExtra("CRYPTO_SYMBOL", crypto.symbol)
                        putExtra("CRYPTO_NAME", crypto.name)
                        putExtra("CRYPTO_DESCRIPTION", crypto.description)
                        putExtra("CRYPTO_PRICE", crypto.currentPrice.toString())
                        putExtra("CRYPTO_MARKET_CAP", crypto.marketCap.toString())
                        putExtra("CRYPTO_CHANGE", crypto.priceChangePercentage24h)
                    }.setFlags(FLAG_ACTIVITY_SINGLE_TOP)
                )
            })
        }
    }
}

@Composable
private fun CryptoMarketItem(
    crypto: Crypto,
    currencyFormatter: NumberFormat,
    onclick: () -> Unit
) {
    val isPositive = crypto.priceChangePercentage24h >= 0
    val changeColor =
        if (isPositive) Color.Green else Color.Red
    Card(modifier = Modifier.clickable { onclick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CryptoIcon(symbol = crypto.symbol)

            Spacer(Modifier.width(16.dp))

            CryptoInfo(
                symbol = crypto.symbol,
                name = crypto.name,
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(12.dp))

            PriceInfo(
                price = currencyFormatter.format(crypto.currentPrice),
                color = changeColor,
                isPositive = isPositive,
                crypto = crypto
            )
        }
    }
}

@Composable
private fun CryptoIcon(symbol: String) {
    Surface(
        modifier = Modifier
            .size(48.dp)
            .clip(MaterialTheme.shapes.medium),
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
private fun CryptoInfo(
    symbol: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = symbol,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun PriceInfo(
    price: String,
    isPositive: Boolean,
    crypto: Crypto,
    color: Color
) {
    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = price,
            style = MaterialTheme.typography.titleMedium
        )


        Text(
            text = "${if (isPositive) "+" else ""}${
                crypto.priceChangePercentage24h.format(
                    2
                )
            }%",
            color = color,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )

    }
}

private fun Double.format(decimals: Int) = "%.${decimals}f".format(this)