package me.jessehill.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(buildJsonObject { put("message", "Hello, World!") })
        }

        staticResources("/static", "static")
    }
}
