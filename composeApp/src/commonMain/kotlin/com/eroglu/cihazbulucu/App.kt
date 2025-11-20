package com.eroglu.cihazbulucu

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    // 1. Sistem temasını algıla
    val isDark = isSystemInDarkTheme()

    // 2. Renk Paletlerini Tanımla (Material 3 Colors)
    val colors = if (isDark) {
        // Koyu Mod Renkleri
        darkColorScheme(
            primary = Color(0xFFBB86FC), // Açık mor (koyu modda iyi görünür)
            background = Color(0xFF121212), // Koyu gri/siyah
            surface = Color(0xFF121212),
            onPrimary = Color.Black, // Buton üzerindeki yazı rengi
            onBackground = Color.White // Arka plan üzerindeki yazı rengi
        )
    } else {
        // Açık Mod Renkleri
        lightColorScheme(
            primary = Color(0xFF6200EE), // Koyu mor
            background = Color.White,
            surface = Color.White,
            onPrimary = Color.White,
            onBackground = Color.Black
        )
    }

    MaterialTheme(colorScheme = colors) {
        // 3. Surface: Arka plan rengini ve yazı rengini temadan alır
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Ekranın durumu
            var text by remember { mutableStateOf("IP Öğrenmek için butona bas") }
            val scope = rememberCoroutineScope()
            val service = remember { IpService() }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Yazı rengini elle vermiyoruz, temadan otomatik alıyor
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
}