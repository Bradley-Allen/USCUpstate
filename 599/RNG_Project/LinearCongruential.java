/**
 * 
 * @author Bradley Allen
 * Generates a random number using a linear
 * congruential algorithm.
 */

public class LinearCongruential {
	
	/**
	 * Linear congruential algorithm
	 * @param seed
	 * @param modulus
	 * @param multiplier
	 * @param increment
	 * @param numOfRandomNums
	 * @param randomnumbers
	 */
	private static void generate(int seed, int modulus, int multiplier, int increment, int numOfRandomNums, int[] randomnumbers) {
		randomnumbers[0] = seed;
		
		for (int i=1; i<numOfRandomNums; ++i) {
			randomnumbers[i] = ((randomnumbers[i-1] * multiplier) + increment) % modulus;
		}
	}
	
	/**
	 * Driver method
	 */
	public static void main(String[] args) {
		int seed = (Math.abs((int) System.currentTimeMillis())) % 73;
		//int seed = 1;
		int modulus = 73;
		int multiplier = 2;
		int increment = 3;
		int numOfRandomNums = 1000;
		int[] randomnumbers = new int[numOfRandomNums];
		int[] occurrences = new int[modulus];
		
		long timeStart = System.currentTimeMillis();
		
		generate(seed, modulus, multiplier, increment, numOfRandomNums, randomnumbers);
		
		// Read over all randomly generated numbers
		for (int i=0; i<randomnumbers.length; ++i) {
			// Increment counter for that number
			occurrences[randomnumbers[i]] += 1;
			
			// Print number generated
			System.out.print(randomnumbers[i] + " ");
		}
		
		long timeStop = System.currentTimeMillis();
		
		System.out.println("");
		
		// Print number of occurrences
		for (int i=0; i<occurrences.length; ++i) {
			System.out.println(i + ": " + occurrences[i]);
		}
		
		System.out.println("Time elapsed: " + (timeStop - timeStart) + "ms");
		
	}
}
