package org.pixellauncher

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import jfxtras.styles.jmetro.JMetro
import jfxtras.styles.jmetro.Style
import java.io.InputStream
import java.net.URL
import java.nio.file.Files
import kotlin.io.path.toPath


object ResourceLoader {
    /**
     * @param path The location of the resource to load. Excluding the
     * `assets` directory.
     * @return A [String] of the [URL] of the path.
     */
    fun load(path: String): String {
        return loadURL(path).toString()
    }

    /**
     * @param path The location of the resource to load. Excluding the
     *             {@code assets} directory.
     * @return A {@link URL} representing the resource.
     */
    fun loadURL(path: String): URL {
        return ResourceLoader::class.java.getResource("/assets/$path")
            ?: throw IllegalArgumentException("Resource not found: $path")
    }

    /**
     * @param path The location of the resource to load. Excluding the
     * `assets` directory.
     * @return An [InputStream] of the resource.
     */
    fun loadStream(path: String): InputStream {
        return ResourceLoader::class.java.getResourceAsStream("/assets/$path")
            ?: throw IllegalArgumentException("Resource not found: $path")
    }

    /**
     * Call `load()` to load the fxml and call the controller.
     *
     * @param name The name of the `fxml` file to load.
     * @return An [FXMLLoader] representing the `fxml` file
     * given.
     */
    fun loadFXML(name: String): FXMLLoader {
        var path = name
        if (!path.endsWith(".fxml")) {
            path += ".fxml"
        }
        return FXMLLoader(loadURL("fxml/$path"))
    }

    fun loadScene(name: String): Scene {
        logger.debug("Loading $name scene")
        val fxmlLoader = loadFXML(name)
        val root = fxmlLoader.load<Parent>()
        logger.debug("Loading theme")
        root.style = loadStylesheetFromTheme(App.settings.theme)
        val scene = Scene(root)
        logger.debug("Applying theme")
        JMetro(scene, App.settings.theme)

        return scene
    }

    /**
     * Load the correct stylesheet for the given theme.
     *
     * @param theme The theme to load.
     * @return The stylesheet as a [String].
     */
    fun loadStylesheetFromTheme(theme: Style): String {
        return when (theme) {
            Style.LIGHT -> readContents("css/light.css")
            Style.DARK -> readContents("css/dark.css")
        }
    }

    /**
     * Read the contents of a file.
     *
     * @param path The location of the file.
     * @return The contents of the file as a string.
     */
    fun readContents(path: String): String {
        return Files.readString(loadURL(path).toURI().toPath())
    }
}