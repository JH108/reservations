package me.jessehill.models

import kotlinx.serialization.Serializable

interface Contact {
    val method: ContactMethod
    val content: String
    fun isValid(): Boolean
}

enum class ContactMethod {
    EMAIL,
    PHONE
}

@Serializable
data class EmailContact(
    override val content: String
) : Contact {
    override val method = ContactMethod.EMAIL

    override fun isValid(): Boolean {
        return content.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
    }
}

@Serializable
data class PhoneContact(
    override val content: String
) : Contact {
    override val method = ContactMethod.PHONE

    override fun isValid(): Boolean {
        return content.matches(Regex("^\\+?[0-9]{1,3}[0-9]{3,14}$"))
    }
}