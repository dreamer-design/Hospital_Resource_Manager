package dsa.structs;
import dsa.data.Patient;
import dsa.data.Request;

/**
 *  a max heap
 * to do: set and get travel times for status = department?
 */
public class Heap {       
    private Request[] heap;
    private int count;
    
    public Heap(int size) {
        this.count = 0;
        heap = new Request[size];
    }

    /**
     * add() must add the priority-value pair at the correct place in the heap.
     * remember that we start off at the end of the array, then trickle it
     * up (by using arithmetic to get its parent) until the parent is of equal or
     * higher priority.
     * @param record
     */
    public void add(Patient record) {
        // U = UrgencyLevel (1 = highest priority, 5 = lowest).
        // urgency 0-4
        int U = record.getUrgency() + 1; // 1 - 5
        // T (expected time in mins) provided by scenario or by shortest-path estimates
        int T = 10; // constant atm
        
        // Higher priority values should be treated first (Max Heap recommended).
        int priority = (6 - U) + 1000 / T;
//        System.out.println(priority);
        heap[++count] = (new Request(priority, record));  // edit
    }

    public void get(Patient key, Patient value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
    /*
    remove() must return the highest priority element and remove it from the
    heap array. This will involve removing the root element, placing the last
    element at the root and then trickling it down.
    */
    public Request remove() {
        
        return null;
    }
    
    public void display() {
        
    }
    
    private void trickleUp(int index) {
        
    }
    
    private void trickleDown(int index) {
        
    }
    
    public void heapSort() {
        
    }
    
    // will convert the array of Request objects into a max heap.
    public void heapify() {
        
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < heap.length; i++)
            if( heap[i] != null )
                builder .append(i).append(" : ")
                        .append( heap[i].getPriority() ).append(" : ")
                        .append(heap[i]
                        );
        
        return builder.toString();
    }

    public static void main(String[] args) {
        Patient t = new Patient("joe");
        t.setUrgency(Patient.Urgency.HIGH);
        Patient u = new Patient("john");
        u.setUrgency(Patient.Urgency.MEDIUM);
        Patient v = new Patient("jacob");
        v.setUrgency(Patient.Urgency.WAIT);
        
        Heap h = new Heap(4);
        h.add(t); h.add(u); h.add(v);
        System.out.println(h);
    }
}
