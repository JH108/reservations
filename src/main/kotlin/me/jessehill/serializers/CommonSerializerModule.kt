package me.jessehill.serializers

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

object CommonSerializerModule {
    val json = Json {
        serializersModule = SerializersModule {
            contextual(UuidSerializer)
        }
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}