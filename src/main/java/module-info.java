module org.pixellauncher {
    requires kotlin.stdlib;

    requires javafx.controls;
    requires javafx.fxml;

    opens org.pixellauncher to javafx.fxml;
    exports org.pixellauncher;
}
