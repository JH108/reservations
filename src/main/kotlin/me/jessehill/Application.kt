package me.jessehill

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import me.jessehill.database.configureDatabases
import me.jessehill.plugins.*

fun main() {
    embeddedServer(CIO, port = 8082, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureTemplating()
    configureSerialization()
    configureMonitoring()
//    configureDatabases()
    configureRouting()
}
