package dsa.structs;

//import java.util.Iterator;

import dsa.data.Department;


public class Stack<E> {
    private int top;
    private LinkedList<Department> stack;


    //constructor
    public Stack() {
        stack = new LinkedList();
        top = 0;
    }

    public void push(Department obj) {
        stack.insertLast(obj);
        top++;        
    }

    public Department pop() {
        if( top >= 0 ) {
//            return stack[--top];
            top--;
            return stack.removeLast();
        }
        else
            return null;
    }
    
//    @Override
//    public Iterator<Department> iterator() {
//        return new Iterator<>() {
//            private int i = 0;
//            public boolean hasNext() { return i <= top; }
//            public Department next() { return stack[i++]; }
//        };
//    }

    
//    public DSAQueue convertToQueue() {
//        DSAQueue postfixQueue = new DSAQueue( ); // top = 13 size = 19
//        
////        System.out.println("conve%rting to Queue");
//        for( int i = 0; i < top; i++) {
//            postfixQueue.enqueue( stack.removeFirst() );
//        }
//        
//        return postfixQueue;
//    }

    public Department peek() {
        return stack.peekLast();
    }

    public int getCount() {
        return top-1;
    }

    public Boolean isEmpty() {
        return stack.isEmpty();        
    }

    @Override
    public String toString() {
        if(stack == null) return "";
        String builder = new String();
        while(stack.peekFirst() != null ) builder += stack.peekLast() + ", ";
      return builder;
    }
}
