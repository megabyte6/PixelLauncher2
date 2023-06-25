package org.pixellauncher

import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import jfxtras.styles.jmetro.JMetro
import jfxtras.styles.jmetro.Style
import org.pixellauncher.setting.Settings

class App : Application() {
    private val settings = Settings()
    val themeEngine = JMetro(settings.theme)

    override fun start(primaryStage: Stage) {
        val fxmlLoader = ResourceLoader.loadFXML("Main")
        val root = fxmlLoader.load<Parent>()
        root.style = ResourceLoader.readContents(
            if (settings.theme == Style.DARK) {
                "css/dark.css"
            } else {
                "css/light.css"
            }
        )
        val scene = Scene(root, settings.windowSize.width, settings.windowSize.height)

        themeEngine.scene = scene

        primaryStage.title = Constants.APP_NAME
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(App::class.java)
}