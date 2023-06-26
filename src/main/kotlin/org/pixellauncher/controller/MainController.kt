package org.pixellauncher.controller

import javafx.fxml.FXML
import javafx.scene.layout.FlowPane
import org.pixellauncher.LOGGER
import org.pixellauncher.ResourceLoader
import org.pixellauncher.dialog.Popup
import java.io.IOException

class MainController {
    @FXML
    private lateinit var instanceContainer: FlowPane

    @FXML
    private fun handleAccountButton() {
        try {
            Popup.popup(ResourceLoader.loadScene("AccountManager"))
        } catch (e: IOException) {
            LOGGER.error("Unable to load AccountManager FXML file")
            LOGGER.catching(e)
        }
    }

    @FXML
    private fun handleSettingsButton() {
        try {
            Popup.popup(ResourceLoader.loadScene("Settings"))
        } catch (e: IOException) {
            LOGGER.error("Unable to load Settings FXML file")
            LOGGER.catching(e)
        }
    }
}