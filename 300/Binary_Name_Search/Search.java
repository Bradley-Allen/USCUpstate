/**
 * @author Bradley Allen
 * Lab 6: Searching -- Search.java
 * Prompts user to enter a prefix to search,
 * returns a list of names with the prefix.
 */

import java.util.*;
import java.io.*;

public class Search {
	public static void main(String[] args) throws IOException {
		/** Initializes and scans in file */
		Scanner file = new Scanner(new File("us.txt"));
		ArrayList<String> list = new ArrayList<>();
		while (file.hasNext()) {
			list.add(file.next());
		}
		file.close();
		
		/** Prompts user input and searches */
		System.out.print("Enter start of name: ");
		Scanner scan = new Scanner(System.in);
		String target = scan.next();
		
		ArrayList<String> results = binarySearch(list, target.toLowerCase());
		
		/** Prints results */
		if (results.size() != 0) {
			for (int i=0; i<results.size(); ++i) {
				System.out.println(results.get(i));
			}
		}
		scan.close();
	}
	
	/** Binary search, returns a list of names matching prefix */
	public static ArrayList<String> binarySearch
			(ArrayList<String> list, String target) {
		int min = 0, max = list.size() - 1, mid = 0, counter = 0, prev = 0;
		boolean found = false, flag = true;
		ArrayList<String> answers = new ArrayList<>();

		while (!found && min <= max) {
			mid = (min + max) / 2;
			prev = mid-1;
			if (list.get(mid).toLowerCase().startsWith(target) &&
					(prev < 0 || !list.get(prev).toLowerCase().startsWith(target))) {
				while(flag) {
					if (list.get(mid).toLowerCase().startsWith(target)) {
						answers.add(list.get(mid));
						++mid;
					}
					else break;
				}
				found = true;
			}
			else if (target.compareTo(list.get(mid).toLowerCase()) < 0) {
				++counter;
				max = mid - 1;
			}
	        else {
	        	++counter;
	            min = mid + 1;
	        }
		}
	    if (found) {
	    	System.out.println("Counted " + counter + 
	    			" times and found " + answers.size() + " results:\n");
	    	return answers;
	    }
	    else {
	    	System.out.println("Counted " + counter + 
	    			" and didn't find any match...");
	    	return answers;
	    }
	}
}
