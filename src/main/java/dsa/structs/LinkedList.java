package dsa.structs;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedList<E> implements Iterable<E> {
    Node<E> head;
    Node<E> tail;
    
    // internal Node
    protected static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
        
        // Constructor for the Node
        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    // constructor
    public LinkedList() {
        head = null;
        tail = null;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() { return current != null; }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E val = current.data;
                current = current.next;
                return val;
            }
        };
    }
    
    public int getLength() {
        int i = 0;
        for(var x: this) {
            i++;
        }
        return i;
    }
    
    public Boolean isEmpty() {
        return head == null;
        
    }
    
    public void insertFirst(E data) {
        Node<E> n = new Node<>(data);
        
        // empty list
        if( head == null ) {
            head = n;
            tail = n;
        }
        else {
        head.prev = n; // update head previous to new node
        n.next = head; // new nodes next to prev head
        head = n;
        }
    }
    
    public void insertLast(E data) {
        Node<E> n = new Node<>(data);
        
        // empty list
        if( head == null ) {
            head = n;
            tail = n;
            return;
        }
        else {            
//            // single: traverse to last node
//            Node current = head;
//            while(current.next != null) current = current.next;
//            current.next = n;
            
            // doubly upgraded
            n.prev = tail; // new nodes previous is tail
            tail.next = n; // add after tail node
            tail = n;  // set new node to last
        }
    }
    
    public void insertBefore(E target, E value) {
        Node<E> newNode = new Node<>(value);
        Node<E> cur = traverseTo(target); // find the target node
        
//        System.out.println(cur.data);

        if (cur != null) {
            newNode.next = cur;
            
//            System.out.println(cur.prev.data); // should be 1?
            newNode.prev = cur.prev;

            if (cur.prev != null) {
                cur.prev.next = newNode;  // link previous node to newNode
            } 
            else {
                head = newNode;           // inserting before head → update head
            }

            cur.prev = newNode;
        } else {
            System.out.println("Target not found: " + target);
        }
    }
    
    public E removeFirst() {
        // check empty
        if(head == null) return null; //throw Exception("Empty List");

        E dat = head.data; // hold the data
        
        // last node in the list
        if (head.next == null || head == tail) {
            head = null;
            tail = null;
        } 
        // more than one
        else {
            head = head.next; // remove head
            head.prev = null;
        } 
        
        return dat;
    }
    
    public Boolean find(E valueToFind) {
        Node<E> current = head;

        // This single loop handles all cases:
        //   Empty List: `current` is null, loop never runs.
        //   One-Node List: Loop runs once.
        //   Multi-Node List: Loop runs until the end.
        while (current != null) {
            if (current.data.equals(valueToFind)) return true; // found
            current = current.next;
        }
        
        return false; // not found
    }
    
    // if i return a reference to the found item instead?
    private Node<E> traverseTo(E valueToFind) {
        Node<E> current = head;

        while (current != null) {
            if (current.data.equals(valueToFind)) return current; // found
            current = current.next;
        }
        
        return null; // not found
    }
    
    public E removeLast() {
        // empty
        if (head == null) {
            return null; // Nothing to remove.
        }

        //  one node.
        if (head.next == null) {
            E data = head.data;
            head = null;
            tail = null; // Update tail as well
            return data;
        }

        // multiple nodes.
        Node<E> current = head;
        // Traverse  to  2nd last node.
        while (current.next.next != null) {
            current = current.next;
        }

        // The second-to-last node is now 'current'.
        E data = current.next.data; // Get the data from the last node.
        current.next = null; // Detach the last node.
        tail = current; // Update the tail pointer to the new last node.

        return data;
    }
    
    public void remove(E value) {
        Node<E> nodeToRemove;
        
        // also checks null
        if( find(value)) {
            nodeToRemove = traverseTo(value);
            
            if(nodeToRemove == null) return; // not found
            
            // fix links
            // Case 1: only one node
            if (nodeToRemove == head && head.next == null) {
                head = null;
                tail = null;
            }
            // Case 2: removing head
            else if (nodeToRemove == head) {
                head = nodeToRemove.next;
                head.prev = null;
            }
            // Case 3: removing tail
            else if (nodeToRemove == tail) {
                tail = nodeToRemove.prev;
                tail.next = null;
            }
            // Case 4: middle node
            else {
                Node<E> previousNode = nodeToRemove.prev;
                Node<E> nextNode = nodeToRemove.next;
                previousNode.next = nextNode;
                nextNode.prev = previousNode;
            }
            
        } // end if find
//        else ;//do nothing not found
    }

    public E peekFirst() {
        if(head == null) return null;
        return head.data;
    }

    public E peekLast() {
        // singly
//        while(ref.next != null) ref = ref.next;
        // doubly
        if (tail != null) return tail.data;
        else return null;
    }
    
    public Node<E> getHeadNode() {
        return head;
    }

    public Node<E> getNext(Node<E> node) {
        return node.next;
    }

    public E getData(Node<E> node) {
        return node.data;
    }
    
    @Override
    public String toString() {
        if (head == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        Node<E> curNode = head;

        while (curNode != null) {
            builder.append(curNode.data);
            if (curNode.next != null) {
                builder.append(", ");
            }
            curNode = curNode.next;
        }

        return builder.toString();
    }

} // end class
