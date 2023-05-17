/**
 * Lab 5 (P2: Sort Comparison)
 * @author Bradley Allen
 * Compares the selection sort, insertion sort, and merge sort
 * algorithms on varying amounts of rectangle objects.
 */

import java.util.*;
import java.io.*;
import java.text.*;

public class Comparison {
	
	public static void main(String[] args) throws IOException {
		ArrayList<Rectangle> rectList = new ArrayList<>();
		Scanner scan = new Scanner(new File("rectangles.txt"));
		DecimalFormat fmt = new DecimalFormat("###,###");
		
		/** Scans file, adds data to rectList */
		while (scan.hasNext()) {
			int width = scan.nextInt();
			int height = scan.nextInt();
			rectList.add(new Rectangle(width, height));
		}
		
		System.out.println("Sorting " + fmt.format(rectList.size())
			+ " rectangles.");
		
		/** Creates rectArray1 for the Selection Sort, tracks
		 *  time elapsed, and prints status message	*/
		Rectangle[] rectArray1 = new Rectangle[rectList.size()];
		rectList.toArray(rectArray1);
		double selectionSortStartTime = System.nanoTime();
		selectionSort(rectArray1);
		double selectionSortEndTime = System.nanoTime();
		double selectionSortElapsedTime = (selectionSortEndTime
				- selectionSortStartTime)/1000;
		System.out.println("Selection sort complete.");
		
		/** Creates rectArray2 for the Insertion Sort, tracks 
		 *  time elapsed, and prints status message	*/
		Rectangle[] rectArray2 = new Rectangle[rectList.size()];
		rectList.toArray(rectArray2);
		double insertionSortStartTime = System.nanoTime();
		selectionSort(rectArray2);
		double insertionSortEndTime = System.nanoTime();
		double insertionSortElapsedTime = (insertionSortEndTime
				- insertionSortStartTime)/1000;
		System.out.println("Insertion sort complete.");
		
		/** Sorts rectList using the Merge Sort, tracks
		 *  time elapsed, and prints status message	*/
		double mergeSortStartTime = System.nanoTime();
		Collections.sort(rectList);
		double mergeSortEndTime = System.nanoTime();
		double mergeSortElapsedTime = (mergeSortEndTime 
				- mergeSortStartTime)/1000;
		System.out.println("Merge sort complete.");
		
		/** Prints the results to be used for comparison of the 
		 *  different sorting algorithms; times in microseconds	*/
		System.out.printf("Selection sort: ");
		System.out.printf("%11s", fmt.format(selectionSortElapsedTime));
		System.out.printf(" microseconds.\n");
		
		System.out.printf("Insertion sort: ");
		System.out.printf("%11s", fmt.format(insertionSortElapsedTime));
		System.out.printf(" microseconds.\n");
		
		System.out.printf("    Merge sort: ");
		System.out.printf("%11s", fmt.format(mergeSortElapsedTime));
		System.out.printf(" microseconds.\n");
	}
	
	/** Selection Sort	*/
	public static <T extends Comparable<? super T>> void selectionSort(T[] list) {
        int min;
        T temp;

        for (int index = 0; index < list.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < list.length; scan++)
                if (list[scan].compareTo((T) list[min]) < 0)
                    min = scan;

            // Swap the values
            temp = list[min];
            list[min] = list[index];
            list[index] = temp;
        }
    }
	
	/** Insertion Sort	*/
	public static <T extends Comparable<? super T>> void insertionSort(T[] list) {
        for (int index = 1; index < list.length; index++) {
            T key = list[index];
            int position = index;

            // Shift larger values to the right
            while (position > 0 && key.compareTo((T) list[position - 1]) < 0) {
                list[position] = list[position - 1];
                position--;
            }

            list[position] = key;
        }
    }
	
}


