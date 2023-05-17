import java.util.ArrayList;
import java.util.Collections;

/* Author: Bradley Allen
 * 1/21/2022 CSCIU300-03 SPRING */

public class IntSort
{
    public static void main(String []args)
    {
        // Checks if there are arguments
        if (args.length > 0) 
        {
            // Initializes ArrayList
            System.out.println("Before sort:");
            ArrayList<Integer> numbers = new ArrayList<>();
            
            // Fills ArrayList "numbers" with arguments
            for (int i = 0; i < args.length; ++i) {
                numbers.add(Integer.parseInt(args[i]));
                System.out.println(numbers.get(i));
            }
            
            // Formatting
            System.out.println("");
            System.out.println("After sort:");
            
            // Sorts the ArrayList "numbers"
            Collections.sort(numbers);
            
            // Prints out sorted ArrayList "numbers"
            for (int numinorder: numbers)
            {
                System.out.println(numinorder);
            }
        }
        // Gives error if there are no arguments
        else {
            System.err.println("Usage: java IntSort arg1 arg2 arg3");
        }
    }
}