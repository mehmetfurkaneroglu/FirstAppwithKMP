package com.eroglu.cihazbulucu

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.eroglu.cihazbulucu.IpService // Paket adınız farklıysa düzeltin

@Composable
@Preview
fun App() {
    MaterialTheme {
        // Ekranın durumu (State)
        var text by remember { mutableStateOf("IP Öğrenmek için butona bas") }
        // Asenkron işlemler için scope
        val scope = rememberCoroutineScope()
        // Servisimiz
        val service = remember { IpService() }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Text(text = text, style = MaterialTheme.typography.h5)
            Text(text = text, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                text = "Yükleniyor..."
                // İnternet işlemi ana thread'i kitlemesin diye coroutine içinde
                scope.launch {
                    text = service.getMyIp()
                }
            }) {
                Text("IP Adresimi Bul")
            }
        }
    }
}