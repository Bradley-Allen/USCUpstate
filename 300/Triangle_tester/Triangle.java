/**
 * Lab 6: Triangle.java
 * @author Bradley Allen
 * Takes command-line arguments as angles of a triangle. Outputs
 * whether the triangle is valid or not.
 */

public class Triangle {
	
	public static void main(String[] args) {
		int[] anglearr = new int[args.length];
		
		/** Reads command-line arguments */
		for (int i=0; i<args.length; ++i) {
			anglearr[i] = Integer.parseInt(args[i]);
			
			System.out.print(args[i] + "°");
			if (i != args.length-1) System.out.print(", ");
			else System.out.print(" ");
		}
		
		/** Prints if triangle is valid or not	*/
		if (isValid(args)) {
			System.out.println("is a valid triangle.");
		}
		else {
			System.out.println("is NOT a valid triangle.");
		}
	}
	
	/** Checks if triangle is valid based on criteria	*/
	private static boolean isValid(String ... nums) {
		/** Triangles must have 3 angles	*/
		if (nums.length != 3) {
			throw new NotEnoughAngles();
		}
		
		/** Calculates the sum of the angles	*/
		int nsum = 0;
		for (String n : nums) {
			if (Integer.parseInt(n) <= 0) {
				return false;
			}
			nsum += Integer.parseInt(n);
		}
		
		if (nsum == 180) {
			return true;
		}
		
		return false;
	}
}
