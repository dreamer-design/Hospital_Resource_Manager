package dsa.data;

import dsa.model.DataModel;

/**
 * This class represents a single patient record
 * 
 */
public class Patient implements Comparable<Patient> {
    static int count = 1; // maintain a count to auto assign id's on start
    int id;
    String name;
    int age;
    Boolean status;
    Urgency urgency;
    int destId; // which department they need to go to get treated
    int eta;    // eta calculated by travel times
    
    public enum Urgency {
    EXTREME, HIGH, MEDIUM, LOW, WAIT
    }

    /**
    * Patient Constructor
    * 
    */
    public Patient(String name) {
        this.id = count++; // auto increment id as object are created
        this.name = name;
        this.age = 20 + DataModel.RNG.nextInt(30);
        this.status = true;
//        this.urgency = Urgency.LOW;
        this.urgency = Urgency.values()[ DataModel.RNG.nextInt(5) ];
        destId = -1;  // unassignd. department list might not be created yet
        eta = -1;
//        System.out.println("patient: " + this.id);
    }

    /**
     * set the destination to department id (if it exists)
     * @param dest 
     */
    public void setDestId(int dest) {
        if( DataModel.getGraphInstance().getDepartments() != null && DataModel.getGraphInstance().getDepartment(dest) != null)
            this.destId = dest;
    }

    /**
     * 
     * @param eta 
     */
    public void setEta(int eta) {
        this.eta = eta;
    }
    
    
    
    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Boolean getStatus() {
        return status;
    }

    public int getUrgency() {
        return urgency.ordinal();
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }
    
    /**
     * compare Patient records by urgency
     * @param other
     * @return (this is less) -ve, 0, +ve (this is greater)
     */
    @Override
    public int compareTo(Patient other) {
        if (other == null) throw new NullPointerException("compare with null");

        if( urgency.ordinal() < other.urgency.ordinal() ) return -1;
        else if( urgency.ordinal() > other.urgency.ordinal() ) return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", name=" + name + ", age=" + age + ", status=" + status + ", urgency=" + urgency + "}\n";
    }
    
}