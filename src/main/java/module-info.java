module com.example.portfoliomanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.portfoliomanagement to javafx.fxml;
    exports com.example.portfoliomanagement;
}