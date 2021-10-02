package com.github.wnuk

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.github.wnuk.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureMonitoring()
        configureSerialization()
    }.start(wait = true)
}
