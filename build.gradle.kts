plugins {
    // Kotlin support.
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.22"

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
    implementation("org.apache.logging.log4j:log4j-api:3.0.0-alpha1")
    implementation("org.apache.logging.log4j:log4j-core:3.0.0-alpha1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jfxtras:jmetro:11.6.16")

//    implementation("eu.iamgio:animated:0.7.0")
//    implementation("io.github.typhon0:AnimateFX:1.2.3")
//    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")

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
    imageZip.set(file("$buildDir/distributions/${rootProject.name}-${javafx.platform.classifier}.zip"))
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
        noConsole = true
    }

    jpackage {
        imageName = rootProject.name
        installerName = "${rootProject.name}-installer"
        vendor = "Brayden Chan"

        val osName = System.getProperty("os.name").toLowerCase()
        if (osName.indexOf("windows") != -1) {
            icon = "src/main/resources/assets/Pixel Launcher.ico"
            installerOptions = listOf(
                "--win-per-user-install",
                "--win-dir-chooser",
                "--win-menu",
                "--win-menu-group", rootProject.name,
                "--win-shortcut",
                "--win-shortcut-prompt",
            )
        } else if (osName.indexOf("mac") != -1) {
            icon = "src/main/resources/assets/Pixel Launcher.icns"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}