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
    public LinkedList<String> backlog; // note: list of request strings

    
    public Heap(int size) {
        this.backlog = new LinkedList();
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
    public void add(Patient record, int priority) {
        Request newRequest = new Request(record, priority);

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
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for(String line: backlog) {
            System.out.println("Heap toString: Line");
//            System.out.println("line"); // xxx: Verbose : Heap : toString : line
            builder.append(line);
        }
        
        return builder.toString();
    }
    
    /**
     * log function for heap ops
     * @param action
     * @param req 
     * xxx: Heap : logState function
     */
    public void logState(String action, Request req) {
        // log the requests in a list
        backlog.insertLast( buildLog(action, req) );
    }

    public String buildLog(String action, Request req) {
        StringBuilder log = new StringBuilder();

        log.append("\n{ ");
        log.append(action)
                .append(": ")
                .append(req.getValue().getName())
                .append(" priority=")
                .append(req.getPriority());

        for (int i = 1; i <= count; i++) {
            log.append(" ")
                    .append(i)
                    .append(": ")
                    .append(heap[i].getPriority())
                    .append(" (")
                    .append(heap[i].getValue().getName())
                    .append("),");
        }

        log.append(" }");

        return log.toString();
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
        
        // xxx: Test : Heap : insert/exctract
        int s = 10;
        
        Heap h = new Heap(s);
        System.out.println("Test heap(10): ");
        System.out.println(h);

        for (int i = 1; i < s; i++) {
            Patient p = new Patient("P" + i);
            h.add(p, 1); // patient, priority
        }

        System.out.println("\nTop of heap: " + h.peek().getValue().getName()); //xxx: Test : Top of heap

        for (int i = 0; i < 3; i++) {
            Request r = h.remove();
            System.out.println("Extracted: " + r.getValue().getName() + " (priority=" + r.getPriority() + ")");
        }
    }
}
