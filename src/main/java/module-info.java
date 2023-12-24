module org.example.javafx_kzbo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires java.sql;

    opens org.example.javafx_kzbo to javafx.fxml;
    opens org.example.javafx_kzbo.controller to javafx.fxml;
    exports org.example.javafx_kzbo.controller;
    exports org.example.javafx_kzbo;
}
