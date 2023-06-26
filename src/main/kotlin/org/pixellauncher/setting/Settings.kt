package org.pixellauncher.setting

import javafx.geometry.Dimension2D
import javafx.geometry.Point2D
import javafx.stage.Screen
import jfxtras.styles.jmetro.Style
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.pixellauncher.LOGGER
import org.pixellauncher.setting.serializer.Dimension2DSerializer
import org.pixellauncher.setting.serializer.Point2DSerializer
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

@Serializable
data class Settings(
    var saveWindowSize: Boolean = true,
    @Serializable(with = Dimension2DSerializer::class)
    var windowSize: Dimension2D = Dimension2D(1000.0, 600.0),
    var saveWindowPos: Boolean = true,
    @Serializable(with = Point2DSerializer::class)
    var windowPos: Point2D = Point2D(
        (Screen.getPrimary().bounds.width - windowSize.width) / 2,
        (Screen.getPrimary().bounds.height - windowSize.height) / 2
    ),

    var theme: Style = Style.DARK
) {
    companion object {
        private val json = Json {
            prettyPrint = true
            encodeDefaults = true
        }

        private fun load(path: Path): Settings {
            if (!Files.exists(path) || !Files.isReadable(path)) {
                throw IOException("Cannot read settings. Path: $path is not readable.")
            }

            val data = Files.readString(path)
            return json.decodeFromString(data)
        }

        fun loadElseDefault(path: Path): Settings {
            return try {
                load(path)
            } catch (e: IOException) {
                LOGGER.warn("Settings failed to load. Using default.")
                LOGGER.catching(e)
                Settings()
            }
        }
    }

    fun save(path: Path) {
        if (!Files.isWritable(path.parent)) {
            throw IOException("Cannot write settings. Path: $path is not writable.")
        }

        val data = json.encodeToString(this)
        Files.writeString(path, data)
    }
}