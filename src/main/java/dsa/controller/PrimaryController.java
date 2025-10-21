package dsa.controller;

import dsa.data.Department;
import dsa.data.Patient;
import dsa.rms.App;
import java.io.IOException;
import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import dsa.model.*; // for DataModel instance reference
import dsa.structs.*; // for Graph reference
import java.util.Iterator;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {
    
    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    private static final Graph graphData = DataModel.getGraphInstance();
    
    final int circleSZ = 20; // node size
    
    /**
     * Patient Record pane
     * these must match the fx:ids of the elements
     */
    @FXML
    private TextField txtPatientId;
    @FXML
    private TextField txtPatientName;
    @FXML
    private TextField txtPatientAge;
    @FXML
    private TextField txtPatientStatus;

    
    /**
     * Department Table FXML defs
     * these must match the fx:ids of the elements
     */
    @FXML
    private TableView<Department> DepartmentTable;
    @FXML
    private TableColumn<Department, Integer> colId;
    @FXML
    private TableColumn<Department, String> colName;
    
    @FXML
    private void initialize() {
        int i = DataModel.getPatientId();
        Patient rec = DataModel.getHashInstance().get( i );
        txtPatientId.setText( String.valueOf( rec.getId() ) );
        txtPatientName.setText( String.valueOf( rec.getName() ) );
        txtPatientAge.setText( String.valueOf( rec.getAge() ) );
        txtPatientStatus.setText( String.valueOf( rec.getStatus() ) );
        
        Graph graph = DataModel.getGraphInstance();

        ObservableList<Department> data = FXCollections.observableArrayList();
        Iterator<Department> iter = graph.getDepartments().iterator();
        while (iter.hasNext()) {
            data.add(iter.next());
        } // data = List of Deparments for FXML
        
        // should call getId and getName from Dperatments
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        DepartmentTable.setItems(data);
        
        // get Canvas and draw the graph
        gc = canvas.getGraphicsContext2D();
        drawSomething();
    }
    
    @FXML
    private void drawNode(int x, int y) {

        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.strokeOval(x, y, circleSZ, circleSZ); // x,y , width, height
    }
    
    @FXML
    private void drawEdge(int x1, int y1, int x2, int y2) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeLine(x1 + 10, y1 + 10, x2 - 10, y2 - 10); // adjust for circle
    }
    
    @FXML
    private void drawEdge(int[] loc1, int[] loc2) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeLine(loc1[0] + circleSZ/2, loc1[1] + circleSZ/2, loc2[0] + circleSZ/2, loc2[1] + circleSZ/2); // adjust for circle
    }

    private void drawSomething() {
        // Clear the canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        // nodes text
        gc.setFill(Color.BLACK);
        gc.fillText(graphData.toString(), 10, 400);
        
//        draw circles for nodes
        for(Department i : graphData.getDepartments() ) {
            drawNode( i.getLoc()[0], i.getLoc()[1] ); // just draw the circle at the id for now
        }
        
        // draw links between nodes
        for(Department i : graphData.getDepartments() ) {
            for(Department v : i.getAdjList()) {
                drawEdge( i.getLoc(), v.getLoc() );
            }
        }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("schedule");
    }
    
    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("records");
    }
}
