package org.pixellauncher.controller

import javafx.fxml.FXML
import javafx.scene.layout.FlowPane
import org.pixellauncher.ResourceLoader
import org.pixellauncher.dialog.Popup

class MainController {
    @FXML
    private lateinit var instanceContainer: FlowPane

    @FXML
    private fun handleAccountButton() {
        Popup.popup(ResourceLoader.loadScene("AccountManager"))
    }

    @FXML
    private fun handleSettingsButton() {
        Popup.popup(ResourceLoader.loadScene("Settings"))
    }
}