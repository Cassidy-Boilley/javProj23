module com.example.javproj23 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javproj23 to javafx.fxml;
    exports com.example.javproj23;
}