package org.pixellauncher.setting.serializer

import javafx.geometry.Dimension2D
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object Dimension2DSerializer : KSerializer<Dimension2D> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Dimension2D") {
        element("width", Double.serializer().descriptor)
        element("height", Double.serializer().descriptor)
    }

    override fun deserialize(decoder: Decoder): Dimension2D {
        val json = decoder.decodeSerializableValue(JsonObject.serializer())

        val width = json["width"]?.jsonPrimitive?.double ?: 0.0
        val height = json["height"]?.jsonPrimitive?.double ?: 0.0

        return Dimension2D(width, height)
    }

    override fun serialize(encoder: Encoder, value: Dimension2D) {
        val data = buildJsonObject {
            put("width", JsonPrimitive(value.width))
            put("height", JsonPrimitive(value.height))
        }

        encoder.encodeSerializableValue(JsonObject.serializer(), data)
    }

}