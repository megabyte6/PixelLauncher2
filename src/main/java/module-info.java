module org.pixellauncher {
    requires kotlin.stdlib;
    requires kotlinx.serialization.json;

    requires javafx.controls;
    requires javafx.fxml;
    opens org.pixellauncher.controller to javafx.fxml;

    requires org.apache.logging.log4j;

    requires org.jfxtras.styles.jmetro;

    exports org.pixellauncher;
}
