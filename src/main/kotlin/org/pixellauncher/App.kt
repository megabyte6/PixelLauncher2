package org.pixellauncher

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class App : Application() {
    override fun start(primaryStage: Stage) {
        val fxmlLoader = FXMLLoader(App::class.java.getResource("Main.fxml"))
        val scene = Scene(fxmlLoader.load(), 600.0, 400.0)
        primaryStage.title = "PixelLauncher"
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(App::class.java)
}