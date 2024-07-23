package me.jessehill

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.utils.io.charsets.*
import me.jessehill.plugins.configureRouting
import me.jessehill.plugins.configureSerialization
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
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
}
