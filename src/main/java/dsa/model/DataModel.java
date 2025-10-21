package dsa.model;
import dsa.structs.*;
import dsa.data.Patient;
import java.util.Random;

public class DataModel {
    private static Graph graphInstance;
    private static Hash hashInstance;
    private static Heap schedule;
    private static int patientId;
    public static final Random RNG = new Random(); // single source RNG obj for ease

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
        graphInstance.addDepartment(1, "Surgery");
        graphInstance.addDepartment(2, "Ward");
        graphInstance.addDepartment(3, "Waiting");
        graphInstance.addDepartment(4, "Emergency");
        graphInstance.addDepartment(5, "Administration");
        graphInstance.addDepartment(6, "Smoko");
        graphInstance.addCorridore(1, 2);
        graphInstance.addCorridore(2, 3);
        graphInstance.addCorridore(4, 2);
        graphInstance.addCorridore(1, 3);
        graphInstance.addCorridore(6, 1);
        graphInstance.addCorridore(5, 2);
        graphInstance.addCorridore(6, 2);
        
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
