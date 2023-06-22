plugins {
    // Kotlin support.
    kotlin("jvm") version "1.8.21"

    // Support for running JavaFX applications.
    application

    // Support for JavaFX applications.
    id("org.openjfx.javafxplugin") version "0.0.14"

    // Support for building this project.
    id("org.beryx.jlink") version "2.26.0"
}

group = "org.pixellauncher"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

application {
    mainModule.set("$group")
    mainClass.set("$group.App")
}

javafx {
    version = "17.0.6"
    modules = listOf("javafx.controls", "javafx.fxml")
}

jlink {
    imageZip.set(project.file("$buildDir/distributions/${rootProject.name}-${javafx.platform.classifier}.zip"))
    options.set(
        listOf(
            "--strip-debug",
            "--compress", "2",
            "--no-header-files",
            "--no-man-pages"
        )
    )
    launcher {
        name = rootProject.name
    }

    jpackage {
        imageName = rootProject.name
        installerName = "${rootProject.name}-installer"
        vendor = "megabyte6"

        val osName = System.getProperty("os.name").toLowerCase()
        if (osName.indexOf("windows") != -1) {
            icon = "src/main/resources/icon.ico"
            installerOptions = listOf(
                "--win-per-user-install",
                "--win-dir-chooser",
                "--win-menu",
                "--win-menu-group", rootProject.name,
                "--win-shortcut",
                "--win-shortcut-prompt",
            )
        } else if (osName.indexOf("mac") != -1) {
            icon = "src/main/resources/icon.icns"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
