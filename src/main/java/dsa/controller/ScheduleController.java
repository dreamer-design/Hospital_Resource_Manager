package dsa.controller;

import dsa.model.DataModel;
import dsa.rms.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ScheduleController {

    @FXML private TextArea txtScheduleOutput;
    @FXML private TextField txtHighest;

    @FXML
    private void initialize() {
        System.out.println("*Schedule initialisation: "); // xxx: Init: ScheduleController
        String s = DataModel.heapInstance.backlog.toString();
        txtScheduleOutput.setText( s );
        String h = DataModel.heapInstance.peek().toString();
        txtHighest.setText( h );
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void onRemovedButtonClicked() throws IOException {
        System.out.println("Schedule: Remove Button Clicked: Highest Priority Removed"); // xxx: Button : Schedule: Remove
        DataModel.heapInstance.remove();
        System.out.println("Scehdule: Reinitialising output");
        initialize();
        
    }

    @FXML
    private void onSelectButtonClicked(MouseEvent event) throws IOException {
        System.out.println("Schedule: Select Button Clicked"); // xxx: Button: Schedule : Select
        var record = DataModel.heapInstance.peek().getValue();
        if (record != null) DataModel.setPatientId( record.getId() );
        else DataModel.setPatientId(1);
        System.out.println("Shedule: Patient Record Selected:");
        switchToPrimary();
    }
}
