package dsa.controller;

import dsa.model.DataModel;
import dsa.rms.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class ScheduleController {
    
    @FXML
    private TextArea txtScheduleOutput;

    
    @FXML
    private void initialize() {
//        txtScheduleOutput.setText( String.valueOf( DataModel.heapInstance ) );
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}