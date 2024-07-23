package me.jessehill.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ReservationLocation(
    @Contextual
    val id: UUID,
    val name: String,
    val address: String,
    val type: ReservationType
)

enum class ReservationType {
    RESTAURANT,
}