package org.pixellauncher.util

import org.pixellauncher.Constants
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


enum class OS {
    WINDOWS, MAC_OS, LINUX;

    companion object {
        fun getOS(): OS {
            val osName = getName().lowercase()
            return if (osName.contains("windows")) {
                WINDOWS
            } else if (osName.contains("mac")) {
                MAC_OS
            } else {
                LINUX
            }
        }

        fun getName() = System.getProperty("os.name") ?: "UNKNOWN"

        fun getVersion() = System.getProperty("os.version") ?: "UNKNOWN"

        fun isWindows() = getOS() == WINDOWS

        fun isMacOS() = getOS() == MAC_OS

        fun isLinux() = getOS() == LINUX

        fun getStoragePath(): Path {
            val portableStoragePath = Path.of("data")

            if (Files.exists(portableStoragePath)) {
                return portableStoragePath
            }

            return when (getOS()) {
                WINDOWS -> Paths.get(System.getenv("APPDATA"))
                    .resolve(Constants.APP_NAME)

                MAC_OS -> Paths.get(System.getProperty("user.home"))
                    .resolve("Library")
                    .resolve("Application Support")
                    .resolve(Constants.REVERSE_DOMAIN_NAME)

                LINUX -> Paths.get(System.getProperty("user.home"))
                    .resolve(".local")
                    .resolve("share")
                    .resolve(Constants.APP_NAME.replace("\\s+".toRegex(), "").lowercase())
            }
        }

        fun createStorageDirectory(): Boolean {
            if (!Files.exists(Constants.STORAGE_PATH)) {
                return Constants.STORAGE_PATH.toFile().mkdirs()
            }
            return true
        }

        fun isUsingFlatpak(): Boolean {
            return OS.isLinux() && Files.exists(Path.of("/.flatpak-info"))
        }
    }
}