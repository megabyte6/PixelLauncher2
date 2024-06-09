package org.pixellauncher

import javafx.application.Application
import javafx.geometry.Dimension2D
import javafx.geometry.Point2D
import javafx.scene.image.Image
import javafx.stage.Stage
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.pixellauncher.setting.Settings
import org.pixellauncher.util.OS
import java.io.IOException

val LOGGER: Logger = LogManager.getLogger("Main")

class App : Application() {
    companion object {
        val settings = Settings.loadElseDefault(Constants.CONFIG_PATH)

        lateinit var stage: Stage
    }

    override fun start(primaryStage: Stage) {
        stage = primaryStage

        primaryStage.scene = ResourceLoader.loadScene("Main")

        if (settings.saveWindowPos) {
            LOGGER.debug("Restoring window location")
            primaryStage.x = settings.windowPos.x
            primaryStage.y = settings.windowPos.y
        }
        if (settings.saveWindowSize) {
            LOGGER.debug("Restoring window size")
            primaryStage.width = settings.windowSize.width
            primaryStage.height = settings.windowSize.height
        }

        LOGGER.debug("Loading icon")
        primaryStage.icons.add(Image(ResourceLoader.loadStream("Pixel Launcher.png")))
        primaryStage.title = Constants.APP_NAME

        LOGGER.info("Showing window")
        primaryStage.show()
    }

    override fun stop() {
        super.stop()

        saveSettings()

        LOGGER.info("Stopping ${Constants.APP_NAME}")
    }

    private fun saveSettings() {
        // Update settings with new window position and size before saving to file.
        settings.windowPos = Point2D(stage.x, stage.y)
        settings.windowSize = Dimension2D(stage.width, stage.height)

        try {
            LOGGER.info("Saving settings")
            settings.save(Constants.CONFIG_PATH)
        } catch (e: IOException) {
            LOGGER.warn("Failed to save settings :(", e)
        }
    }
}

fun main() {
    LOGGER.info("Starting ${Constants.APP_NAME}")
    if (!OS.createStorageDirectory()) {
        LOGGER.fatal("Failed to create the app config and data directory at '${OS.getStoragePath().toAbsolutePath()}'")
        LOGGER.info("Stopping ${Constants.APP_NAME}")
        return
    }

    Application.launch(App::class.java)
}