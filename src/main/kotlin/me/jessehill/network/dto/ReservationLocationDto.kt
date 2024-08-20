package me.jessehill.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import me.jessehill.models.ReservationLocation
import me.jessehill.models.ReservationType
import java.util.UUID

@Serializable
data class ReservationLocationDto(
    @Contextual
    val id: UUID,
    val name: String,
    val address: String,
    val type: ReservationType
)

fun ReservationLocationDto.toReservationLocation() = ReservationLocation(
    id = id,
    name = name,
    address = address,
    type = type
)
