/**
 * Linked-list-based stack for integers.
 * 
 * @author Bradley Allen
 */
class Stack {
    private Node top;

    /**
     * Constructor. Initializes top as a reference to null.
     */
    public Stack() {
        top = null;
    }

    /**
     * Indicates whether the stack is full.
     * Since this is a linked-list, the stack will never be full.
     * 
     * @return false - Linked-list-based stacks are never full.
     */
    public boolean isFull() {
        return false;
    }

    /**
     * Adds an integer to the top of the stack and increments top.
     * 
     * @param toAdd new integer to add.
     * @throws ArrayIndexOutOfBoundsException if the stack is full.
     */
    public void push(int toAdd) {
        // isFull does not need to be checked, so this can be removed
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        Node node = new Node(toAdd);
        node.next = top;
        top = node;
    }

    /**
     * Removes and returns a number from the top of the stack.
     * 
     * @return the integer at the top of the stack.
     * @throws EmptyStackException if the stack is empty
     */
    public int pop() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        
        int temp = top.data;
        top = top.next;
        return temp;
    }

    /**
     * Indicates whether the stack is empty.
     * 
     * @return true if it is empty.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns a string representation of the contents of the stack.
     * 
     * @return String representation of the stack.
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Node current = top; current != null; current = current.next) {
        	output.insert(0, " ");
        	output.insert(0, current);
        }
        return output.toString();
    }
    
    /**
     * Nested class node that the stack is based on.
     */
    private class Node {
        public Node next;
        public int data;

        public Node(int d) {
            data = d;
            next = null;
        }

        public String toString() {
            return "[" + data + "]";
        }
    }
}

class StackTest {
    public static void main(String[] args) {
        int numToAdd = (int) (Math.random() * 30) + 3;
        Stack stack = new Stack();

        for (int i = 0; i < numToAdd; ++i) {
            stack.push((int) (Math.random() * 90) + 10);
        }
        System.out.println("Stack: " + stack);

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}