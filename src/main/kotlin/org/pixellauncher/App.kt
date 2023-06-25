package org.pixellauncher

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import jfxtras.styles.jmetro.JMetro
import org.pixellauncher.setting.Settings

class App : Application() {
    private val settings = Settings()

    override fun start(primaryStage: Stage) {
        val fxmlLoader = FXMLLoader(App::class.java.getResource("Main.fxml"))
        val scene = Scene(fxmlLoader.load(), 600.0, 400.0)
        JMetro(scene, settings.theme)
        primaryStage.title = Constants.APP_NAME
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(App::class.java)
}