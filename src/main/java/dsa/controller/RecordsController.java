package dsa.controller;

import dsa.data.Patient;
import dsa.model.DataModel;
import dsa.model.Sorts;
import dsa.rms.App;
import dsa.structs.Hash;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecordsController {
    
    @FXML
    private TableView<Patient> RecordTable;
    @FXML
    private TableColumn<Patient, Integer> colId;
    @FXML
    private TableColumn<Patient, String> colName;
    @FXML
    private TableColumn<Patient, Integer> colAge;
    @FXML
    private TableColumn<Patient, Boolean> colStatus;
    @FXML
    private TableColumn<Patient, Enum> colUrgency;
    @FXML
    private TableColumn<Patient, Integer> colDuration;
    
    @FXML
    private void initialize() {
        // get records
        Hash h = DataModel.getHashInstance();
        var l = h.getHashArray();
        
        List<Patient> li = Arrays.asList( l ); // its not cheating i need it Iterator/gui
        ObservableList<Patient> data = FXCollections.observableArrayList();
        Iterator<Patient> iter = li.iterator();
        while (iter.hasNext()) {
            data.add(iter.next());
        } // data = List of Deparments for FXML
        
        // should call getters
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colUrgency.setCellValueFactory(new PropertyValueFactory<>("urgency"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        
        RecordTable.setItems(data);
    }
    
    @FXML
    private void sortRecords() throws IOException {
        System.out.println("Records: Sort Records Button: "); // xxx: Records : Sort button
        Patient[] A = DataModel.getHashInstance().getHashArray();
        Patient[] B = DataModel.getHashInstance().getSortedArray();
//        System.out.println(ref);
        B = Sorts.createSorted(A); // copy the hash array into the sorted array
//        System.out.println("Debug: Records: Sort: length"); // xxx: Debug: RecordsController: Sort: length
//        System.out.println(B.length);

        // refresh table
//        initialize();
        // create new window
        Stage newWindow = new Stage();
        newWindow.setTitle("Sorted Patient Records");

        TableView<Patient> tableView = new TableView<>();
        TableColumn<Patient, Integer> durationCol = new TableColumn<>("Duration");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        TableColumn<Patient, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Patient, String> firstNameCol = new TableColumn<>("Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<Patient, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<Patient, Integer> urgencyCol = new TableColumn<>("Urgency");
        urgencyCol.setCellValueFactory(new PropertyValueFactory<>("urgency"));

        // Add all columns to the TableView
        tableView.getColumns().addAll(durationCol, urgencyCol, idCol, firstNameCol, ageCol);

        // 4. Populate the TableView with your sorted Patient data
        ObservableList<Patient> sortedPatients = FXCollections.observableArrayList(B);
        tableView.setItems(sortedPatients);

        // 5. Create a layout for the new window. VBox is simple for a single TableView.
        VBox root = new VBox(tableView);
        root.setPrefSize(600, 1000); // Set a preferred size for the content of the window
        tableView.setPrefSize(600, 1000); // Let the table fill the VBox

        // 6. Create a Scene and set it on the new Stage
        Scene scene = new Scene(root);
        newWindow.setScene(scene);

        // 7. Show the new window
        newWindow.show();
        }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void onSelectButtonClicked(MouseEvent event) throws IOException {
        
        System.out.println("Select Button Clicked: "); // xxx: Button : Records : Select
        Patient selected = RecordTable.getSelectionModel().getSelectedItem();
        System.out.print("Record selected: "); // xxx: Records : Select : Patient
        System.out.println(selected);
        if (selected != null) {
            DataModel.setPatientId( selected.getId() );
        }
        else
        {
            DataModel.setPatientId(1); // fixme: BUG : if ID 1 changed in primary
        }
        
        System.out.print("Records: getPatientID: "); // xxx: Records: Select: PatientId set (null==1)
        System.out.println(DataModel.getPatientId());

        switchToPrimary();
    }
}