package me.jessehill.serializers

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import me.jessehill.models.Contact
import me.jessehill.models.EmailContact
import me.jessehill.models.PhoneContact

object CommonSerializerModule {
    val json = Json {
        serializersModule = SerializersModule {
            contextual(UuidSerializer)
            contextual(OffsetDateTimeSerializer)
            polymorphic(Contact::class) {
                subclass(EmailContact::class)
                subclass(PhoneContact::class)
            }
        }
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}