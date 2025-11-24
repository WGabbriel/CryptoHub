package com.wgabbriel.cryptohub.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wgabbriel.cryptohub.R
import com.wgabbriel.cryptohub.model.Crypto
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CryptoDetailsScreen(crypto: Crypto?) {
    if (crypto == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            val colors = MaterialTheme.colorScheme
            val priceFormat = NumberFormat.getCurrencyInstance(Locale.US)

            // Cards superiores
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoCard(
                    title = "Cap. de Mercado",
                    value = formatValue(crypto.marketCap.toLong()),
                    icon = { ImageVector.vectorResource(id = R.drawable.trending_up) },
                    modifier = Modifier.weight(1f)
                )

                InfoCard(
                    title = "Volume 24h",
                    value = formatValue(crypto.marketCap.toLong() / 50),
                    icon = { ImageVector.vectorResource(id = R.drawable.trending_up) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Estatísticas
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Estatísticas",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    StatRow("Máxima 24h", priceFormat.format(crypto.currentPrice.toDouble() * 1.04))
                    Spacer(modifier = Modifier.height(16.dp))

                    StatRow("Mínima 24h", priceFormat.format(crypto.currentPrice.toDouble() * 0.94))
                    Spacer(modifier = Modifier.height(16.dp))

                    StatRow("Máxima Histórica", priceFormat.format(crypto.currentPrice.toDouble() * 1.5))
                    Spacer(modifier = Modifier.height(16.dp))

                    StatRow(
                        "Fornecimento Circulante",
                        "${(crypto.marketCap.toLong() / crypto.currentPrice.toLong())} ${crypto.symbol}"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Portfólio
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Seu Portfólio",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    StatRow("Quantidade", "0.5 ${crypto.symbol}")
                    Spacer(modifier = Modifier.height(16.dp))

                    StatRow("Valor Atual", priceFormat.format(crypto.currentPrice.toDouble() * 0.5))
                    Spacer(modifier = Modifier.height(16.dp))

                    StatRow("Investido", "$30,000.00")
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lucro/Prejuízo",
                            style = MaterialTheme.typography.bodyLarge,
                            color = colors.onSurface
                        )
                        Text(
                            text = "$3,617.25(12.06%)",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botões de ação
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        text = "+ Adicionar Mais",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                OutlinedButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = colors.error
                    ),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        text = "Remover",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Criar alerta
            OutlinedButton(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colors.secondary
                ),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Criar Alerta",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun InfoCard(
    title: String,
    value: String,
    icon: @Composable () -> ImageVector,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon(),
                    contentDescription = null,
                    tint = colors.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = colors.onSurface,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
private fun StatRow(label: String, value: String) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = colors.outline
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = colors.onSurface
        )
    }
}

private fun formatValue(value: Long): String {
    return when {
        value >= 1_000_000_000_000 -> String.format("$%.2fT", value / 1_000_000_000_000.0)
        value >= 1_000_000_000 -> String.format("$%.2fB", value / 1_000_000_000.0)
        value >= 1_000_000 -> String.format("$%.2fM", value / 1_000_000.0)
        else -> String.format("$%,d", value)
    }
}
