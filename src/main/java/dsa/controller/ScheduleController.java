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
        System.out.println("*Sched*"); // xxx: sched init

        // xxx: textarea
//        System.out.println(DataModel.heapInstance.backlog.toString());
//        System.out.println("Controller: " + this);
//        System.out.println("Backlog: " + DataModel.heapInstance.backlog);
//        System.out.println("txtScheduleOutput = " + txtScheduleOutput);

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
        DataModel.heapInstance.remove();
        initialize();
        
    }

    @FXML
    private void onSelectButtonClicked(MouseEvent event) throws IOException {
        var record = DataModel.heapInstance.peek().getValue();
        if (record != null) DataModel.setPatientId( record.getId() );
        else DataModel.setPatientId(1);
        switchToPrimary();
    }
}
