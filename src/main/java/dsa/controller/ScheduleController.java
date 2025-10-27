package dsa.controller;

import dsa.rms.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class ScheduleController {
    
    @FXML
    private void initialize() {
    }
    
    @FXML
    private void scheduleOutput() throws IOException {
        
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}