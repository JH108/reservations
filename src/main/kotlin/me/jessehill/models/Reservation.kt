package me.jessehill.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime
import java.util.UUID

@Serializable
data class Reservation(
    @Contextual
    val id: UUID,
    val name: String,
    val email: String,
    val location: ReservationLocation,
    @Contextual
    val time: OffsetDateTime,
    val partySize: Int
)
