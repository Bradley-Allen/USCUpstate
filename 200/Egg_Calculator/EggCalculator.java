public class EggCalculator 
{
	public static void main(String[] args) 
	{
		int TotalEggs = 27; 				// The number of eggs in the purchase.
		int DozenEggs = TotalEggs / 12;		// Amount of eggs grouped in dozens.
		int LooseEggs = TotalEggs % 12;		// Amount of eggs left over from grouping.
		
		double DozenPrice = 3.25;			// Price of a grouped dozen.
		double LoosePrice = 0.45;			// Price of a single egg.
		
		double TotalPrice = (DozenPrice * DozenEggs) + (LoosePrice * LooseEggs);
		
		System.out.println("You ordered " + TotalEggs + " eggs. That's " + DozenEggs + " dozen at $" + DozenPrice + " per dozen and " + LooseEggs + " loose eggs at $" + LoosePrice + " each for a total of $" + TotalPrice + ".");
	}
}
