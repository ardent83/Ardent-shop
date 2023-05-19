module com.example.fistprojectphase2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fistprojectphase2 to javafx.fxml;
    exports com.example.fistprojectphase2;
}