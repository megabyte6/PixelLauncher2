package org.pixellauncher.setting.serializer

import javafx.geometry.Point2D
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object Point2DSerializer: KSerializer<Point2D> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Point2D") {
        element("x", Double.serializer().descriptor)
        element("y", Double.serializer().descriptor)
    }

    override fun deserialize(decoder: Decoder): Point2D {
        val json = decoder.decodeSerializableValue(JsonObject.serializer())

        val x = json["x"]?.jsonPrimitive?.double ?: 0.0
        val y = json["y"]?.jsonPrimitive?.double ?: 0.0

        return Point2D(x, y)
    }

    override fun serialize(encoder: Encoder, value: Point2D) {
        val data = buildJsonObject {
            put("x", JsonPrimitive(value.x))
            put("y", JsonPrimitive(value.y))
        }

        encoder.encodeSerializableValue(JsonObject.serializer(), data)
    }
}