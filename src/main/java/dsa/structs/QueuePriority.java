package dsa.structs;
// FIFO, default shuffling queue

import dsa.data.Department;

public class QueuePriority {

    protected int size;
    protected int count;
    protected LinkedList<Department> queue;

    //constructor
    public QueuePriority() {
        queue = new LinkedList();
        count = 0; // top
    }

    // equiv. to enqueue, add to end
    public void enqueue(Department obj) {
        queue.insertLast(obj);
        count++;
    }

    // equiv to dequeue, take from front
    public Department dequeue() {
        Department value;
        
        if( count >= 0 ) { // if there are items
            value = queue.removeFirst(); // first value to be returned
            count--;
        }
        else
            value = null;
        
        return value;
    }

    public Department peek() {
        return queue.peekFirst();
    }

    public int getCount() {
        return count;
    }

    public Boolean isEmpty() {
//        System.out.println("count: " + count);
        if(count == 0) return true;
        else return false;
    }

    @Override
    public String toString() {
        if(queue == null) return "";
        String builder = new String();
        Integer i = 0;
        for( Department d : queue) {
            builder += i++;
            builder += ": ";
            builder += d;
            builder += ", ";
        }
        builder += ";\n";
        return builder;
    }
}
