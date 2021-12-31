module com.ale.tubespbo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ale.tubespbo to javafx.fxml;
    exports com.ale.tubespbo;
}