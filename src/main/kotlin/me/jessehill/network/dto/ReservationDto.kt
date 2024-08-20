package me.jessehill.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import me.jessehill.models.Reservation
import java.time.OffsetDateTime
import java.util.UUID

@Serializable
data class ReservationDto(
    @Contextual
    val id: UUID,
    val user: UserDto,
    val location: ReservationLocationDto,
    @Contextual
    val time: OffsetDateTime,
    val partySize: Int
)

fun ReservationDto.toReservation() = Reservation(
    id = id,
    user = user.toUser(),
    location = location.toReservationLocation(),
    time = time,
    partySize = partySize
)
