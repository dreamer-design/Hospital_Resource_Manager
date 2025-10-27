package dsa.structs;
import dsa.data.Patient;
import dsa.data.Request;
import dsa.model.DataModel;

//todo: 1. check logging

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
        int rs = DataModel.sampleDepartmentID();
        int rd = DataModel.sampleDepartmentID();

        int p = record.init(rs, rd);
        Request newRequest = new Request(p, record);

        heap[++count] = newRequest; // start at index 1 for clarity
        trickleUp(count);
        logState("After insert", newRequest);
    }
    
    /**
     * peek at max value
     * @return root node
     */
    public Request peek() {
        if (count == 0) {
            return null;
        }
        return heap[1];
    }

        
    /*
    remove() must return the highest priority element and remove it from the
    heap array. This will involve removing the root element, placing the last
    element at the root and then trickling it down.
    */
    public Request remove() {
        if (count == 0) {
            return null;
        }

        Request root = heap[1];
        heap[1] = heap[count--];
        trickleDown(1);
//        System.out.printf("After remove", root);
        logState("After remove", root);
        return root;
    }
    
    /**
     * trickle up
     * @param index
     * [1] indexing
     */
    private void trickleUp(int index) {
        int parent = index / 2;
        Request bottom = heap[index];

        while (index > 1 && heap[parent].getPriority() < bottom.getPriority()) {
            heap[index] = heap[parent];
            index = parent;
            parent = parent / 2;
        }
        heap[index] = bottom;
    }

    /**
     * trickle down
     * @param index 
     * [1] indexing
     */
    private void trickleDown(int index) {
        int largerChild;
        Request top = heap[index];

        while (index * 2 <= count) {
            int leftChild = index * 2;
            int rightChild = leftChild + 1;

            if (rightChild <= count && heap[leftChild].getPriority() < heap[rightChild].getPriority()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top.getPriority() >= heap[largerChild].getPriority()) {
                break;
            }

            heap[index] = heap[largerChild];
            index = largerChild;
        }
        heap[index] = top;
    }
    
    // will convert the array of Request objects into a max heap.
    public void heapify() {
        for (int i = count / 2; i >= 1; i--) {
            trickleDown(i);
        }
    }

    public void heapSort() {
        int oldCount = count;
        for (int i = count; i > 1; i--) {
            Request temp = heap[1];
            heap[1] = heap[i];
            heap[i] = temp;
            count--;
            trickleDown(1);
        }
        count = oldCount;
    }

    
    public void display() {
        
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
    
    private void logState(String action, Request req) {
        System.out.println("\n" + action + ": " + req.getValue().getName()
                + " priority=" + req.getPriority());
        System.out.println("start log entry:");
        for (int i = 1; i <= count; i++)
            System.out.print(i + ": " + heap[i].getPriority() + " (" + heap[i].getValue().getName() + "), ");
        System.out.println("end log entry");
    }


    public static void main(String[] args) {
//        Patient t = new Patient("joe");
//        t.setUrgency(Patient.Urgency.HIGH);
//        Patient u = new Patient("john");
//        u.setUrgency(Patient.Urgency.MEDIUM);
//        Patient v = new Patient("jacob");
//        v.setUrgency(Patient.Urgency.WAIT);
//        
//        Heap h = new Heap(4);
//        h.add(t); h.add(u); h.add(v);
//        System.out.println(h);
        
        // test
        int s = 20;
        
        Heap h = new Heap(s);
//        System.out.println(h);

        for (int i = 1; i < s; i++) {
            Patient p = new Patient("P" + i);
            h.add(p);
        }

        
        System.out.println("\nTop of heap: " + h.peek().getValue().getName()); //xxx: top of heap

//        for (int i = 0; i < s/2; i++) {
//            Request r = h.remove();
//            System.out.println("Extracted: " + r.getValue().getName() + " (priority=" + r.getPriority() + ")");
//        }
    }
}
