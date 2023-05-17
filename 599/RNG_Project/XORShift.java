public class XORShift {
	
	static long seed;
	
	/**
	 * Generates a random long number
	 * using XOR shift
	 * @return Random Number (seed)
	 */
	public static long randomLong() {
	  seed ^= (seed << 21);
	  seed ^= (seed >>> 35);
	  seed ^= (seed << 4);
	  return Math.abs(seed % 73);
	}
	
	public static void main(String[] args) {
		seed = System.nanoTime();
		int numToGenerate = 1000;
		int modulus = 73;
		int[] randomnumbers = new int[numToGenerate];
		int[] occurrences = new int[modulus];
		
		long timeStart = System.currentTimeMillis();
		
		for (int i=0; i<randomnumbers.length; ++i) {
			randomnumbers[i] = (int) randomLong();
			
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
