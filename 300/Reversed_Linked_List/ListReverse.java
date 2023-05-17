import java.util.Scanner;

/**
 * Lab 8 -- ListReverse.java && IntList.java
 * Reads user input and adds to a list until a
 * noninteger is entered, then prints the list reversed.
 * 
 * @author Bradley Allen
 *
 */
class ListReverse {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		IntList list = new IntList();
		System.out.print("Enter numbers: ");
		while (scan.hasNextInt()) {
			list.addToHead(scan.nextInt());
		}
		scan.close();
		System.out.println("List reversed: " + list);
	}	
}