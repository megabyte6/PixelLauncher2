module org.pixellauncher {
    requires kotlin.stdlib;
    requires kotlinx.serialization.json;

    requires javafx.controls;
    requires javafx.fxml;

    requires org.jfxtras.styles.jmetro;

    opens org.pixellauncher.controller to javafx.fxml;
    exports org.pixellauncher;
}
