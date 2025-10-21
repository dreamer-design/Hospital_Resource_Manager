package dsa.structs;

//import java.util.Iterator;

public class Stack<E> {

    private int size;
    private int top;
    private LinkedList<Object> stack;


    //constructor
    public Stack() {
        stack = new LinkedList();
        size = 0;
        top = 0;
    }

    public void push(Object obj) {
        
        if( top <= size-1) {
            stack.insertLast(obj);
            top++;
//            stack[top++] = obj;
//            System.out.print("push: " + obj + " ");

        }
        else System.out.println("full: " + obj);
        
    }

    public Object pop() {
        if( top >= 0 ) {
//            return stack[--top];
            top--;
            return stack.removeLast();
        }
        else
            return null;
    }
    
//    @Override
//    public Iterator<Object> iterator() {
//        return new Iterator<>() {
//            private int i = 0;
//            public boolean hasNext() { return i <= top; }
//            public Object next() { return stack[i++]; }
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

    public Object peek() {
        return stack.peekLast();
//        return stack[top-1];
    }

    public int getCount() {
        return top-1;
    }

    public Boolean isEmpty() {
        return stack.isEmpty();        
//        if(top == 0) return true;
//        else return false;
    }

    @Override
    public String toString() {
        if(stack == null) return "";
        String builder = new String();
        while(stack.peekFirst() != null ) builder += stack.peekLast() + ", ";
//        String temp = new String();
//        for(int i = 0; i < top; i++) temp += stack[i] + " ";
//        return temp;
      return builder;
    }
}
