package me.jessehill

import io.ktor.server.testing.*
import io.ktor.test.dispatcher.*
import kotlinx.coroutines.flow.firstOrNull
import me.jessehill.controllers.SimpleReservationController
import me.jessehill.database.SimpleReservationDatabase
import me.jessehill.models.ContactMethod
import me.jessehill.models.ReservationType
import me.jessehill.network.dto.*
import java.time.OffsetDateTime
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ReservationTests {
    @Test
    fun `test reservation creation`() = testApplication {
        val database = SimpleReservationDatabase()
        val controller = SimpleReservationController(database)
        val reservationId = UUID.randomUUID()
        val userId = UUID.randomUUID()
        val locationId = UUID.randomUUID()
        val reservationTime = OffsetDateTime.now()

        val dto = ReservationDto(
            id = reservationId,
            location = makeFakeLocation(locationId),
            user = makeFakeUser(userId),
            time = reservationTime,
            partySize = 4
        )
        controller.createReservation(dto)

        val reservation = database.getReservation(reservationId).firstOrNull()
        val expected = dto.toReservation()
        assertEquals(reservationId, reservation?.id)
        assertEquals(expected, reservation)
    }

    companion object {
        fun makeFakeUser(userId: UUID = UUID.randomUUID()) =  UserDto(
            id = userId,
            firstName = "Test",
            lastName = "User",
            contact = ContactDto(
                method = ContactMethod.EMAIL,
                content = "acme.hey@acme.com"
            )
        )

        fun makeFakeLocation(locationId: UUID = UUID.randomUUID()) = ReservationLocationDto(
            id = locationId,
            name = "Test Location",
            address = "123 Test St",
            type = ReservationType.RESTAURANT
        )
    }
}