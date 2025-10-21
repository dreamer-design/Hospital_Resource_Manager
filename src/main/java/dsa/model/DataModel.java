package dsa.model;
import dsa.structs.*;
import dsa.data.Patient;
import java.util.Random;

public class DataModel {
    private static Graph graphInstance;
    private static Hash hashInstance;
    private static Heap schedule;
    private static int patientId;
    
    // constants
    public static final Random RNG = new Random(); // single source RNG obj for ease
    public static final int GRID_SIZE = 30; // grid/node size


    static {
        graphInstance = new Graph();
        hashInstance = new Hash(20);
        init();
    }

    public static Graph getGraphInstance() {
        return graphInstance;
    }

    public static Hash getHashInstance() {
        return hashInstance;
    }

    public static Heap getSchedule() {
        return schedule;
    }
    
    public static void init() {
//        graphInstance.addDepartment(1, "Surgery", 0, 0);
//        graphInstance.addDepartment(2, "Ward", 2, 2);
//        graphInstance.addDepartment(3, "Waiting", 3 , 4);
//        graphInstance.addDepartment(4, "Emergency", 4, 6);
//        graphInstance.addDepartment(5, "Administration", 7, 8);
//        graphInstance.addDepartment(6, "Smoko", 2, 5);
//        graphInstance.addCorridore(1, 2);
//        graphInstance.addCorridore(2, 3);
//        graphInstance.addCorridore(4, 2);
//        graphInstance.addCorridore(1, 3);
//        graphInstance.addCorridore(6, 1);
//        graphInstance.addCorridore(5, 2);
//        graphInstance.addCorridore(6, 2);
        
        // Department nodes:
        graphInstance.addDepartment(11, "Surgery", 1, 1);
        graphInstance.addDepartment(17, "Ward", 7, 1);
        graphInstance.addDepartment(44, "Waiting", 4, 4);
        graphInstance.addDepartment(51, "Emergency", 1, 5);
        graphInstance.addDepartment(55, "Administration", 5, 5);
        graphInstance.addDepartment(77, "Smoko", 7, 7);
        graphInstance.addDepartment(81, "Entrance", 1, 8);
        graphInstance.addDepartment(85, "Parking Lot", 5, 8);

        // Corridors (edges):
        graphInstance.addCorridore(11, 17);
        graphInstance.addCorridore(11, 51);
        graphInstance.addCorridore(17, 44);
        graphInstance.addCorridore(17, 77);
        graphInstance.addCorridore(44, 55);
        graphInstance.addCorridore(51, 81);
        graphInstance.addCorridore(51, 85);
        
        // ids are incremented
        for(Integer i = 90; i < 120; i ++)
            hashInstance.put( new Patient( Character.toString(i) ) );
//        hashInstance.put( new Patient("beth") );
//        hashInstance.put( new Patient("nicola") );
//        hashInstance.put( new Patient("jeff") );
//        hashInstance.put( new Patient("robert") );
//        hashInstance.put( new Patient("bronwyn") );
        
        patientId = 1; // get the first record
    }

    public void reset() {
        graphInstance = new Graph();
    }

    public static void setPatientId(int patientId) {
        DataModel.patientId = patientId;
    }

    public static int getPatientId() {
        return patientId;
    }
    
    
}
