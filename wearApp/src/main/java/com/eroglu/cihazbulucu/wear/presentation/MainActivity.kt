/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.eroglu.cihazbulucu.wear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.* // Wear Material kütüphanesi önemli!
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    // Saatler için özel renk paleti
    val wearColors = Colors(
        primary = Color(0xFF00E5FF), // Neon Mavisi (Saatte şık durur)
        background = Color.Black,    // Saatlerde arka plan hep siyah olmalı
        surface = Color(0xFF202124), // Kartlar için koyu gri
        onPrimary = Color.Black,
        onBackground = Color.White,
        onSurface = Color.White
    )

    MaterialTheme(colors = wearColors) {
        val service = remember { IpService() }
        var text by remember { mutableStateOf("IP Bul") }
        val scope = rememberCoroutineScope()

        Scaffold(
            timeText = { TimeText() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background), // Arka planı siyah yap
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground, // Yazıyı beyaz yap
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Chip(
                    onClick = {
                        text = "..."
                        scope.launch {
                            text = service.getMyIp()
                        }
                    },
                    label = {
                        Text(
                            "Sorgula",
                            color = MaterialTheme.colors.onPrimary
                        )
                    },
                    colors = ChipDefaults.primaryChipColors(
                        backgroundColor = MaterialTheme.colors.primary
                    )
                )
            }
        }
    }
}