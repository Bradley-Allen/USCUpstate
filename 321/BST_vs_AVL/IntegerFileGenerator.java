/**
 * IntegerFileGenerator.java
 * @author Bradley Allen
 * Creates a file with randomly generated integers.
 */

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class IntegerFileGenerator {
	final static int INPUTSIZE = 2000; // CHANGE
	final static String FILENAME = "A1.txt"; // CHANGE
	
	public static void main(String[] args) {
		createFile();
		int[] input = new int[INPUTSIZE];
		input = randomFill(input);
		writeFile(input);
	}
	
	/**
	 * Creates a file with the given filename.
	 * Notifies if file already exists.
	 * @param filename
	 */
	private static void createFile() {
		try {
			File fileName = new File(FILENAME);
			if (fileName.createNewFile()) {
				System.out.println("File created: " + fileName.getName());
			}
			else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes an array to a file.
	 * @param filename
	 * @param input
	 */
	private static void writeFile(int[] input) {
		try {
			FileWriter myWriter = new FileWriter(FILENAME);
			for (int i=0; i<input.length; ++i) {
				// Prevents extra line at EOF
				if (i == input.length-1) {
					myWriter.write(input[i]+"");
					myWriter.close();
				}
				else {
					myWriter.write(input[i] + "\n");	
				}
				
			}
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Fills an array with random integers, checking
	 * for duplicates.
	 * @param array
	 * @return array
	 */
	private static int[] randomFill(int[] array) {
		int randomNumber;
		for (int i=0; i<array.length; ++i) {
			randomNumber = (int) (Math.random()*100000);
			randomNumber = checkDuplicate(array, randomNumber);
			array[i] = randomNumber;
		}
		return array;
	}
	
	/**
	 * Checks if the num is already in the array
	 * If it is, generates a new num to check.
	 * RECURSIVE
	 * @param array
	 * @param num
	 * @return num
	 */
	private static int checkDuplicate(int[] array, int num) {
		for (int i=0; i<array.length; ++i) {
			if (array[i] == num) {
				int randomNumber = (int) (Math.random()*100000);
				int newRandomNumber = checkDuplicate(array, randomNumber);
				num = newRandomNumber;
				break;
			}
		}
		return num;
	}
}
