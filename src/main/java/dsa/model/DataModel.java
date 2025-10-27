package dsa.model;

import dsa.data.Department;
import dsa.structs.*;
import dsa.data.Patient;
import dsa.data.Request;
import java.util.Random;

public class DataModel {

    private static final Graph graphInstance;
    private static final Hash hashInstance;
    public static final Heap heapInstance;
    public static final LinkedList<Department> deps;
    private static Heap schedule;
    private static int patientId = 1;
    public static final Random RNG = new Random(); // note: RNG obj
    public static final int GRID_SIZE = 32; // note: grid size constant
    public static final int dbSize = 40;  // note: database size constant

    static {
        graphInstance = new Graph();
        hashInstance = new Hash(40);
        heapInstance = new Heap(40);
        deps = graphInstance.getDepartments();
        init();
    }

    // fixme: dont know why im bothering with getters on refs
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
        // fixme: crash if id does not exist

        // ids are incremented
        for (Integer i = 80; i < 100; i++) {
            int rs = DataModel.sampleDepartmentID(); // rng src dep
            int rd = DataModel.sampleDepartmentID(); // rng dest dep
            var newRecord = new Patient(Character.toString(i)); // create
            int priority = newRecord.init(rs, rd); // get priority
            hashInstance.put(newRecord);
            
            if( newRecord.getUrgency() > Patient.Urgency.HIGH.ordinal()  ) {
                Request newReq = new Request(newRecord, priority); // urgent: create a request
                heapInstance.logState("urgent: " + newRecord.getUrgency() , newReq);
                heapInstance.add(newRecord, priority);
            } // add urgent to heap
            else System.out.println("not urgent"); // xxx: not urgent
        }
//        hashInstance.put( new Patient("beth") );
//        hashInstance.put( new Patient("nicola") );
//        hashInstance.put( new Patient("jeff") );
//        hashInstance.put( new Patient("robert") );
//        hashInstance.put( new Patient("bronwyn") );

        patientId = 1; // get the first record
    }

    public static int sampleDepartmentID() {
        int size = deps.getLength();
        int r = DataModel.RNG.nextInt(size);
        int[] samples = new int[size];

        // get ids, fixme: mybe replace wth getArray in deps
        int i = 0;
        for (Department x : deps) {
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
