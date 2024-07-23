package me.jessehill.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class User(
    @Contextual
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val contact: Contact
)
