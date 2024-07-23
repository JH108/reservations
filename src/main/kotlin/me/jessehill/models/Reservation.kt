package me.jessehill.models

import java.time.OffsetDateTime
import java.util.UUID

data class Reservation(
    val id: UUID,
    val name: String,
    val location: ReservationLocation,
    val time: OffsetDateTime,
)
