import java.util.Scanner;

/**
 * Lab 8 -- ListReverse.java && IntList.java
 * IntList.java is a linked list of integers.
 * 
 * @author Bradley Allen, Dr. Gothard, Lewis/Loftus
 *         3/31/2022
 * 
 */
class IntList {
    private IntNode list;

    /**
     * Sets up an initially empty list of integers.
     */
    public IntList() {
        list = null;
    }

    /**
     * Creates a new IntNode object and adds it to the end of
     * the linked list.
     * 
     * @param num integer to add to the back of the list.
     */
    public void add(int num) {
        IntNode node = new IntNode(num);
        IntNode current;

        if (list == null)
            list = node;
        else {
            current = list;
            while (current.next != null)
                current = current.next;
            current.next = node;
        }
    }
    
    /**
     * Creates a new IntNode object and adds it to the front
     * of the linked list.
     * 
     * @param num integer to add to the front of the list.
     */
    public void addToHead(int num) {
    	IntNode node = new IntNode(num);

        if (list == null)
            list = node;
        else {
            node.next = list;
            list = node;
        }
    }

    /**
     * Returns this list of integers as a string.
     * 
     * @return a string representation of the list.
     */
    public String toString() {
        String result = "";

        IntNode current = list;

        while (current != null) {
            result += current.num + "\t";
            current = current.next;
        }

        return result;
    }

    /**
     * An inner class that represents a node in the int list.
     * The public variables are accessed by the IntList class.
     */
    private class IntNode {
        public int num;
        public IntNode next;

        /**
         * IntNode constructor. Initializes num to n and next to null.
         * 
         * @param n the number to store in the new node.
         */
        public IntNode(int n) {
            num = n;
            next = null;
        }
    }
}