package dsa.model;
import dsa.data.Department;
import dsa.structs.*;
import dsa.data.Patient;
import java.util.Random;

public class DataModel {
    private static final Graph graphInstance;
    private static final Hash hashInstance;
    public static final LinkedList<Department> deps;
    private static Heap schedule;
    private static int patientId = 1;
    public static final Random RNG = new Random(); // single source RNG obj for ease
    public static final int GRID_SIZE = 32;

    static {
        graphInstance = new Graph();
        hashInstance = new Hash(40);
        deps = graphInstance.getDepartments();
        init();
    }

    // dont know why im bothering with getters on these but cbf refactoring so late
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
    // Department nodes:
    graphInstance.addDepartment(0, "Node1", 0, 0);
    graphInstance.addDepartment(6, "Node2", 6, 0);
    graphInstance.addDepartment(11, "Node6", 1, 1);
    graphInstance.addDepartment(20, "Node4", 0, 2);
    graphInstance.addDepartment(22, "Node5", 2, 2);
    graphInstance.addDepartment(24, "Node8", 4, 2);
    graphInstance.addDepartment(33, "Node7", 3, 3);
    graphInstance.addDepartment(41, "Node13", 1, 4);
    graphInstance.addDepartment(50, "Node14", 0, 5);
    graphInstance.addDepartment(52, "Node12", 2, 5);
    graphInstance.addDepartment(54, "Node10", 4, 5);
    graphInstance.addDepartment(70, "Node15", 0, 7);
    graphInstance.addDepartment(72, "Node11", 2, 7);
    graphInstance.addDepartment(74, "Node9", 4, 7);
    graphInstance.addDepartment(76, "Node3", 6, 7);

    // Corridors (edges):
    graphInstance.addCorridor(0, 6, 2);
    graphInstance.addCorridor(0, 11, 2);
    graphInstance.addCorridor(0, 20, 2);
    graphInstance.addCorridor(6, 24, 2);
    graphInstance.addCorridor(6, 76, 2);
    graphInstance.addCorridor(20, 22, 2);
    graphInstance.addCorridor(22, 33, 2);
    graphInstance.addCorridor(41, 50, 2);
    graphInstance.addCorridor(41, 52, 2);
    graphInstance.addCorridor(50, 70, 2);
    graphInstance.addCorridor(52, 54, 2);
    graphInstance.addCorridor(54, 72, 2);
    graphInstance.addCorridor(54, 74, 2);
    graphInstance.addCorridor(74, 76, 2);
        // crash if id does not exist
        
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
    
    public static int sampleDepartmentID() {
        int size = deps.getLength();
        int r = DataModel.RNG.nextInt( size );
        int[] samples = new int[ size ];
        
        // get ids, fixme: mybe replace wth getArray in deps
        int i = 0;
        for(Department x: deps) {
            samples[i++] = x.getId();
        }

//        System.out.println(samples[r]); // xxx: samples selected
        return samples[r];
    }

    public static void setPatientId(int patientId) {
        DataModel.patientId = patientId;
    }

    public static int getPatientId() {
        return patientId;
    }
    
    
}
