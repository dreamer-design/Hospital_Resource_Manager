package dsa.controller;

import dsa.data.Patient;
import dsa.model.DataModel;
import dsa.rms.App;
import dsa.structs.Hash;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    private void initialize() {
        
        Hash h = DataModel.getHashInstance();
        var l = h.getHashArray();
        
        List<Patient> li = Arrays.asList( l ); // its not cheating i need it Iterator/gui
        
        ObservableList<Patient> data = FXCollections.observableArrayList();
        Iterator<Patient> iter = li.iterator();
        while (iter.hasNext()) {
            data.add(iter.next());
        } // data = List of Deparments for FXML
        
        // should call getId and getName from Dperatments
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        RecordTable.setItems(data);
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void onSelectButtonClicked(MouseEvent event) throws IOException {
        Patient selected = RecordTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            DataModel.setPatientId( selected.getId() );
        }
        switchToPrimary();
    }
}