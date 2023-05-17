/**
 * Step_3_2.java
 * @author Bradley Allen
 * Inserts incrementing integers into an AVL tree,
 * then removes a given amount at random.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Step_3_2 {
	
	final static int INPUTSIZE = 2000, NODESTOREMOVE = 1000; // CHANGE
	final static String OUTPUT = "output4.txt";

	public static void main(String[] args) {
		int[] arr = buildArray();
		
		long startBuildTime = System.nanoTime();
		AvlTree<Integer> avl = buildAVL(arr);
		long endBuildTime = System.nanoTime();
		long elapsedBuildTime = endBuildTime - startBuildTime;
		
		int[] removedArray = buildRemovedArray(arr);
		
		long startRemoveTime = System.nanoTime();
		avl = removeNodes(avl, removedArray);
		long endRemoveTime = System.nanoTime();
		long elapsedRemoveTime = endRemoveTime - startRemoveTime;
		
		printResults(elapsedBuildTime, elapsedRemoveTime);		
	}
	
	/**
	 * Builds the array of incrementing integers.
	 * @return Array of incrementing integers
	 */
	private static int[] buildArray() {
		int[] arr = new int[INPUTSIZE];
		for (int i=0; i<arr.length; ++i) {
			arr[i] = i+1;
		}
		return arr;
	}
	
	/**
	 * Builds an AVL Tree using an array of integers.
	 * @param Array of integers to be used
	 * @return AVL Tree
	 */
	private static AvlTree<Integer> buildAVL(int[] arr) {
		AvlTree<Integer> avl = new AvlTree<>();
		for (int i=0; i<arr.length; ++i) {
			avl.insert(arr[i]);
		}
		return avl;
	}
	
	/**
	 * Builds the array of integers to be removed from the AVL.
	 * @param Array of integers to randomly chose from
	 * @return Array of integers that will be removed from the AVL
	 */
	private static int[] buildRemovedArray(int[] arr) {
		int[] removedArr = new int[NODESTOREMOVE];
		int indexToRemove;
		for (int i=0; i<removedArr.length; ++i) {
			indexToRemove = (int) (Math.random()*INPUTSIZE);
			indexToRemove = checkDuplicate(removedArr, indexToRemove);
			removedArr[i] = arr[indexToRemove];
		}
		return removedArr;
	}
	
	/**
	 * Checks for duplicate numbers before inserted into array.
	 * If duplicate, generates another number and checks again.
	 * RECURSIVE
	 * @param Array to be checked for duplicates
	 * @param Integer to check
	 * @return Integer checked
	 */
	private static int checkDuplicate(int[] arr, int num) {
		for (int i=0; i<arr.length; ++i) {
			if (arr[i] == num) {
				int randomNumber = (int) (Math.random()*INPUTSIZE);
				int newRandomNumber = checkDuplicate(arr, randomNumber);
				num = newRandomNumber;
				break;
			}
		}
		return num;
	}

	/**
	 * Removes nodes from an AVL tree, given an array of which nodes to remove.
	 * @param AVL Tree
	 * @param Array of integers to be removed from the tree
	 * @return Modified AVL Tree
	 */
	private static AvlTree<Integer> removeNodes(AvlTree<Integer> avl, int[] arr) {
		for (int i=0; i<arr.length; ++i) {
			avl.remove(arr[i]);
		}
		return avl;
	}
	
	/**
	 * Creates a file and prints the results.
	 * @param elapsedBuildTime
	 * @param elapsedRemoveTime
	 */
	private static void printResults(long elapsedBuildTime, long elapsedRemoveTime) {
		System.out.println("Elapsed time to build AVL: " + elapsedBuildTime + " nanoseconds.");
		System.out.println("Elapsed time to remove " + NODESTOREMOVE + " nodes" + " from AVL: " + elapsedRemoveTime + " nanoseconds.");
		
		System.out.println("\nCreating file to save results...");
		createFile();
		writeFile(elapsedBuildTime, elapsedRemoveTime);
		System.out.println("\nResults successfully saved.");
	}

	/**
	 * Creates the output file, notifies if it is already created.
	 */
	private static void createFile() {
		try {
			File file = new File(OUTPUT);
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			}
			else {
				System.out.println("!! Failed to create file: File already exists.");
			}
		} catch (IOException e) {
			System.out.println("!!! Failed to create file: Stack trace printed.");
			e.printStackTrace();
		}
	}

	/**
	 * Writes to the output file.
	 * @param elapsedBuildTime
	 * @param elapsedRemoveTime
	 */
	private static void writeFile(long elapsedBuildTime, long elapsedRemoveTime) {
		try {
			FileWriter myWriter = new FileWriter(OUTPUT, true);
			myWriter.append("RESULTS FOR " + INPUTSIZE + " INCREMENTING INTEGERS\n");
			myWriter.append("\tElapsed time to build AVL: " + elapsedBuildTime + " nanoseconds.\n");
			myWriter.append("\tElapsed time to remove " + NODESTOREMOVE + " nodes" + " from AVL: " + elapsedRemoveTime + " nanoseconds.\n\n");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("!!! Failed to write to file: Stack trace printed.");
			e.printStackTrace();
		}
	}
}
