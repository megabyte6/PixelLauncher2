package org.pixellauncher

import javafx.scene.image.Image
import org.pixellauncher.util.OS
import java.nio.file.Path

class Constants {
    companion object {
        const val VERSION = "0.1.0"

        const val APP_NAME = "Pixel Launcher"
        const val REVERSE_DOMAIN_NAME = "org.pixellauncher"
        const val APP_WEBSITE = "https://github.com/megabyte6/PixelLauncher"

        val APP_ICON = Image(ResourceLoader.loadStream("Pixel Launcher.png"))

        val STORAGE_PATH = OS.getStoragePath()
        val CONFIG_PATH: Path = STORAGE_PATH.resolve("config.json")
    }
}