/**
 * An array-based queue for integers.
 * 
 * @author Bradley Allen
 */
class Queue {
    private int[] queue;
    private static final int CAP = 20;
    private int front, back, size;

    /**
     * Default constructor: no parameters
     */
    public Queue() {
    	queue = new int[CAP];
    	front = back = -1;
        size = 0;
    }

    /**
     * enqueue: add element to the rear
     * 
     * @throws ArrayIndexOutOfBounds
     */
    public void enqueue(int data) {
        if (isEmpty()) {
        	queue[++front] = data;
        	queue[++back] = data;
        	size++;
        } else if (isFull()) {
        	throw new ArrayIndexOutOfBoundsException("The queue is full.");
        } else {
        	back = (back + 1) % CAP;
        	queue[back] = data;
        	size++;
        }

    }

    /**
     * dequeue: remove from the front
     * 
     * @return the integer from the front
     * @throws NoSuchElement
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The queue is empty.");
        } else {
        	int value = queue[front];
        	queue[front] = 0;
        	front = (front + 1) % CAP;
        	size--;
            return value;
        }

    }

    /**
     * Indicates whether the queue is empty.
     * 
     * @return true if there are no items in the queue.
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * Indicates whether the queue is full.
     * 
     * @return true if full.
     */
    public boolean isFull() {
        return (size == CAP);
    }

    /**
     * toString: print contents
     * 
     * @return a string representation of the queue
     */
    public String toString() {
        StringBuilder output = new StringBuilder("");
        for (int i = front; i != back+1; i = (i+1) % CAP) {
        	output.append(queue[i]);
        	output.append(" ");
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        // Add random numbers to a queue.
        int numToAdd = (int) (Math.random() * 8) + 3;
        for (int i = 0; i < numToAdd; ++i) {
            int num = (int) (Math.random() * 50) + 1;
            queue.enqueue(num);
        }
        System.out.println("Queue: " + queue);
        // remove some numbers
        for (int i = 0; i < 3 && !queue.isEmpty(); ++i) {
            System.out.println("Removed: " + queue.dequeue());
        }

        System.out.println("\nQueue: " + queue);

        // Add more
        numToAdd = (int) (Math.random() * 8);
        for (int i = 0; i < numToAdd; ++i) {
            int num = (int) (Math.random() * 50) + 1;
            queue.enqueue(num);
        }

        System.out.println("\nQueue: " + queue);

        // Remove until empty.
        while (!queue.isEmpty()) {
            System.out.println("Removed: " + queue.dequeue());
        }
    }
}