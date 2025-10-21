package dsa.structs;
// FIFO, default shuffling queue

import dsa.data.Department;

public class Queue {

    protected int size;
    protected int count;
    protected LinkedList<Department> queue;

    //constructor
    public Queue() {
        queue = new LinkedList();
        size = 0;
        count = 0; // top
    }

    // equiv. to enqueue, add to end
    public void enqueue(Department obj) {
        
        
        if( count <= size ) {
//            System.out.print("enqueue: " + obj + " ");
            queue.insertLast(obj);;
            count++;
//            queue[count++] = obj;  // add to end
            }
        else
            System.out.println("full: " + obj);
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

    public Boolean isFull() {
        System.out.println("isFull, count: " + count + " " + "size: " + (size+1) );
        if(count == size+1) return true;
        else return false;
    }

    @Override
    public String toString() {
        if(queue == null) return "";
        String builder = new String();
        while(queue.peekFirst() != null ) builder += queue.peekLast() + ", ";
        return builder;
    }
}
