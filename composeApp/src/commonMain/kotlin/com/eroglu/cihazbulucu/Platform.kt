package com.eroglu.cihazbulucu

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform