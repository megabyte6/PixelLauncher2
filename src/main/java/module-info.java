module org.pixellauncher {
    requires kotlin.stdlib;

    requires javafx.controls;
    requires javafx.fxml;

    requires org.jfxtras.styles.jmetro;

    opens org.pixellauncher to javafx.fxml;
    exports org.pixellauncher;
}
