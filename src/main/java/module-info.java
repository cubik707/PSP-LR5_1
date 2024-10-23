module org.example.lr5_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lr5_1 to javafx.fxml;
    exports org.example.lr5_1;
}