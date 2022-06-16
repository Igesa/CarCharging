module com.example.carcharcinggui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires org.json;

    opens com.example.carcharcinggui to javafx.fxml;
    exports com.example.carcharcinggui;
}