package me.jessehill.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.jessehill.serializers.CommonSerializerModule

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            json = CommonSerializerModule.json
        )
    }
    routing {
        get("/json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
