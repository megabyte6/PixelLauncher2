package org.pixellauncher

import org.pixellauncher.util.OS

class Constants {
    companion object {
        const val VERSION = "0.1.0"

        const val APP_NAME = "Pixel Launcher"
        const val REVERSE_DOMAIN_NAME = "org.pixellauncher"
        const val APP_WEBSITE = "https://github.com/megabyte6/PixelLauncher"

        val STORAGE_PATH = OS.getStoragePath()
        val CONFIG_PATH = STORAGE_PATH.resolve("config.json")
    }
}