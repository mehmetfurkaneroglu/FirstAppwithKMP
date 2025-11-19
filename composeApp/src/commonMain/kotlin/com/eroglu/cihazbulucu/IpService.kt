package com.eroglu.cihazbulucu

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import io.ktor.client.*
import kotlinx.serialization.json.Json

// 1. İnternetten gelecek verinin kalıbı (Model)
@Serializable
data class IpResponse(val ip: String)

class IpService {
    // 2. İstemciyi oluşturuyoruz
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }
    }

    // 3. Veriyi çeken fonksiyon
    suspend fun getMyIp(): String {
        return try {
            // ipify.org ücretsiz bir API'dir
            val response: IpResponse = client.get("https://api.ipify.org?format=json").body()
            return "IP Adresin: ${response.ip}"
        } catch (e: Exception) {
            return "Hata: ${e.message}"
        }
    }
}