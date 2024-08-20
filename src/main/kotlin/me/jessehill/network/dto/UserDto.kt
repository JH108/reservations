package me.jessehill.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import me.jessehill.models.User
import java.util.UUID

@Serializable
data class UserDto(
    @Contextual
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val contact: ContactDto
)

fun UserDto.toUser() = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    contact = contact.toContact()
)
