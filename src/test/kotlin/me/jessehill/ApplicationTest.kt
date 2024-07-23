package me.jessehill

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import io.ktor.utils.io.charsets.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import me.jessehill.models.Reservation
import me.jessehill.models.ReservationLocation
import me.jessehill.models.ReservationType
import me.jessehill.plugins.configureRouting
import me.jessehill.plugins.configureSerialization
import me.jessehill.serializers.CommonSerializerModule
import java.time.OffsetDateTime
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
   val httpClient = HttpClient {
       install(ContentNegotiation) {
           json(
               CommonSerializerModule.json
           )
       }
   }

    @Test
    fun `test that home returns JSON`() = testApplication {
        application {
            configureSerialization()
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), contentType())
        }
    }


    @Test
    fun `test that a reservation can be made`() = testApplication {
        application {
            configureSerialization()
            configureRouting()
        }
        val reservation = Reservation(
            id = UUID.randomUUID(),
            name = "Jesse Hill",
            location = ReservationLocation(
                id = UUID.randomUUID(),
                name = "The Coffee Shop",
                address = "1234 Elm St. Springfield, IL 62701",
                type = ReservationType.RESTAURANT
            ),
            time = OffsetDateTime.now().plusDays(1)
        )

        httpClient.post("/reservations") {
            contentType(ContentType.Application.Json)
            setBody(CommonSerializerModule.json.encodeToJsonElement(reservation))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), contentType())
        }
    }
}
