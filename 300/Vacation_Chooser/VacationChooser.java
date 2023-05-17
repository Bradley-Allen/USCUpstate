/**
 * VacationChooser.java
 * 
 * Accepts temperatures listed in a text document as an argument (100x5) and outputs
 * the cities with temperatures favorable for a vacation.
 * 
 * @author Bradley Allen
 */

import java.io.*;
import java.util.Scanner;

public class VacationChooser {
	/** Initialization of constants, defining the amount of cities/temperatures and the range of temperatures that are favorable	*/
	public static final int MINTEMP = 65, MAXTEMP = 85, CITYAMOUNT = 100, TEMPERATURESRECORDED = 5;
	
	public static void main(String []args) throws IOException {
		/** Initialization	*/
		Scanner scan = new Scanner(new File(args[0]));
		int[][] citytemplist = new int[CITYAMOUNT][TEMPERATURESRECORDED];
		
		/** Handles all rows, then all columns in the current row	*/
		for(int i=0; i<CITYAMOUNT; ++i) {
			int counter = 0;
			for(int j=0; j<TEMPERATURESRECORDED; ++j) {
				citytemplist[i][j] = scan.nextInt();
				
				/** Counter will increase (up to 5) if the temperature is favorable	*/
				if (printSuggestion(i,citytemplist[i][j])) {
					counter++;
				}
			}
			
			/** Prints if all of the temperatures in a city were favorable	*/
			if (counter == 5) {
				System.out.print("City #" + (i+1) + "\t");
				for (int p=0;p<TEMPERATURESRECORDED;++p) {
					System.out.print("\t" + citytemplist[i][p] + "°");
				}
				System.out.println();
			}
		}
		
		scan.close();
	}
	
	/** Handles comparisons to determine if the temperature is favorable */
	public static boolean printSuggestion(int city, int temperature) {
		if (temperature >= MINTEMP && temperature <= MAXTEMP) {
			return true;
		}
		else {
			return false;
		}
	}
}
