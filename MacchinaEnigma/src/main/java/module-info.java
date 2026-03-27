module com.albo.macchinaenigma {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.albo.macchinaenigma to javafx.fxml;
    exports com.albo.macchinaenigma;
}