/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.eroglu.cihazbulucu.wear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
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
    MaterialTheme {
        val service = remember { IpService() }
        var text by remember { mutableStateOf("IP Bul") }
        val scope = rememberCoroutineScope()

        // Scaffold, saat arayüzü için temel yapıdır
        Scaffold(
            timeText = { TimeText() }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Saate özel yuvarlak buton (Chip)
                Chip(
                    onClick = {
                        text = "..."
                        scope.launch {
                            text = service.getMyIp()
                        }
                    },
                    label = { Text("Sorgula") },
                    colors = ChipDefaults.primaryChipColors()
                )
            }
        }
    }
}