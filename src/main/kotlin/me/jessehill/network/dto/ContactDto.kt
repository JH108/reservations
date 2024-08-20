package me.jessehill.network.dto

import kotlinx.serialization.Serializable
import me.jessehill.models.ContactMethod
import me.jessehill.models.EmailContact
import me.jessehill.models.PhoneContact

@Serializable
data class ContactDto(
    val method: ContactMethod,
    val content: String
)

fun ContactDto.toContact() = when (method) {
    ContactMethod.EMAIL -> EmailContact(content)
    ContactMethod.PHONE -> PhoneContact(content)
}
