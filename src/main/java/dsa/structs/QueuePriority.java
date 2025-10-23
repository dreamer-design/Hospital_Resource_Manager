package dsa.structs;

// Generic Priority Queue implementation
public class QueuePriority<T extends Comparable<T>> {

    protected int count;
    protected LinkedList<T> queue;

    //constructor
    public QueuePriority() {
        queue = new LinkedList<>();
        count = 0;
    }

    //constructor
    public QueuePriority(T d) {
        queue = new LinkedList();
        queue.insertFirst(d);
        count = 1;
    }

    // Priority enqueue - insert based on natural ordering (highest priority first)
    public void enqueue(T item) {
        if (queue.isEmpty()) {
            queue.insertFirst(item);
        } else {
            // Find the correct position based on priority
            LinkedList.Node<T> current = queue.getHeadNode();
            LinkedList.Node<T> previous = null;
            
            // Traverse to find the correct insertion point
            while (current != null && item.compareTo(current.data) > 0) {
                previous = current;
                current = queue.getNext(current);
            }
            
            if (previous == null) {
                // Insert at head (highest priority)
                queue.insertFirst(item);
            } else {
                // Insert after previous node
                queue.insertBefore(current != null ? current.data : queue.peekLast(), item);
            }
        }
        count++;
    }

    // Dequeue - remove highest priority item (first in queue)
    public T dequeue() {
        T value;
        
        if (count > 0) {
            value = queue.removeFirst();
            count--;
        } else {
            value = null;
        }
        
        return value;
    }

    public T peek() {
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
        for( T item : queue) {
            builder += i++;
            builder += ": ";
            builder += item;
            builder += ", ";
        }
        builder += ";\n";
        return builder;
    }
}
