module dsa.rms {
    requires javafx.controls;
    requires javafx.fxml;

    opens dsa.rms to javafx.fxml;
    opens dsa.controller to javafx.fxml;
    opens dsa.data to javafx.fxml;
    opens dsa.structs to javafx.fxml;
    exports dsa.controller;
    exports dsa.rms;
    exports dsa.data;
    exports dsa.structs;
}
