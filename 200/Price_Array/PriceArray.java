import java.util.Scanner;
import java.text.NumberFormat;

public class PriceArray
{
	public static void main(String[] args)
	{
		// Initialization
		double[] prices = new double[5];
		double highestprice = 0, lowestprice = 999999*Math.exp(999), averageprice=0;
		Scanner scan = new Scanner(System.in);
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		
		// Fills out array with requested prices
		for (int i=0;i<5;i++)
		{
			System.out.print("Enter the prices of the items from store " + (i+1) + ": ");
			prices[i] = scan.nextDouble();
		}
		scan.close();
		
		// Checks for the highest, lowest and average prices
		for (int i=0;i<5;i++)
		{
			if (highestprice <= prices[i])
			{
				highestprice = prices[i];
			}
			if (lowestprice >= prices[i])
			{
				lowestprice = prices[i];
			}
			averageprice += prices[i];
		}
		
		// Calculates average price
		averageprice /= 5;
		
		// Prints the highest, lowest and average prices
		System.out.println("");
		System.out.println("The highest price is: " + fmt.format(highestprice));
		System.out.println("The lowest price is: " + fmt.format(lowestprice));
		System.out.println("The average price is: " + fmt.format(averageprice));
	}
}