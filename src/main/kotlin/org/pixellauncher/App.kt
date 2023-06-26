package org.pixellauncher

import javafx.application.Application
import javafx.scene.image.Image
import javafx.stage.Stage
import org.apache.logging.log4j.LogManager
import org.pixellauncher.setting.Settings

val logger = LogManager.getLogger(App::class.java)!!

class App : Application() {
    companion object {
        val settings = Settings.load(Constants.CONFIG_PATH)
    }

    override fun start(primaryStage: Stage) {
        primaryStage.scene = ResourceLoader.loadScene("Main")

        if (settings.saveWindowPos) {
            logger.debug("Restoring window location")
            primaryStage.x = settings.windowPos.x
            primaryStage.y = settings.windowPos.y
        }
        if (settings.saveWindowSize) {
            logger.debug("Restoring window size")
            primaryStage.width = settings.windowSize.width
            primaryStage.height = settings.windowSize.height
        }

        logger.debug("Loading icon")
        primaryStage.icons.add(Image(ResourceLoader.loadStream("Pixel Launcher.png")))
        primaryStage.title = Constants.APP_NAME

        logger.info("Showing window")
        primaryStage.show()
    }

    override fun stop() {
        super.stop()

        settings.save(Constants.CONFIG_PATH)

        logger.info("Stopping ${Constants.APP_NAME}")
    }
}

fun main() {
    Application.launch(App::class.java)
}