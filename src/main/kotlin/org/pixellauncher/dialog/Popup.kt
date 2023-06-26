package org.pixellauncher.dialog

import javafx.scene.Scene
import javafx.stage.Stage
import org.pixellauncher.Constants

object Popup {
    fun popup(scene: Scene): Stage {
        val stage = Stage()
        stage.scene = scene
        stage.title = Constants.APP_NAME
        stage.icons.add(Constants.APP_ICON)

        return stage
    }
}