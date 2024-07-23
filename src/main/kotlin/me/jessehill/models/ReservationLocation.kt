package me.jessehill.models

import java.util.UUID

data class ReservationLocation(
    val id: UUID,
    val name: String,
    val address: String,
    val type: ReservationType
)

enum class ReservationType {
    RESTAURANT,
}