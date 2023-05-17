/**
 * @author Bradley Allen
 * Lab 7 -- Recursion.java
 * Reads user input, prints input
 * forwards and backwards.
 */

import java.util.Scanner;

public class Recursion {
	public static void main(String[] args) {
		/** Initialization */
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a line of text: ");
		String line = scan.nextLine();
		scan.close();
		
		/** Recursion to print regularly */
		System.out.print("\nThis is what you entered:\n\t");
		printForwards(line.toCharArray(), line.length());
		System.out.println();
		
		/** Recursion to print backwards */
		System.out.print("This is what you entered, but backwards:\n\t");
		printBackwards(line.toCharArray(), 0);
	}
	
	/** Handles regular printing with recursion */
	private static void printForwards(char line[], int i) {
		/** Base case */
		if (i == 0) return;
		
		printForwards(line, i-1);
		System.out.print(line[i-1]);
	}
	
	/** Handles backwards printing with recursion */
	private static void printBackwards(char line[], int i) {
		/** Base case */
		if (i == line.length) return;
		
		printBackwards(line, i + 1);
		System.out.print(line[i]);
	}
}