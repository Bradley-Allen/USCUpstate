import java.util.Scanner;

public class DigitReader
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter an integer: ");
		String integerstring = scan.nextLine();
		scan.close();
		
		int digits = integerstring.length(), zeros = 0, evens = 0, odds = 0;
		
		// Loop to determine polarity
		for(int i=0; i<=digits-1; ++i)
		{
			String check = integerstring.substring(i, i+1);
			int checkinteger = Integer.valueOf(check);
		
				if(checkinteger == 0) {
					System.out.println(checkinteger + " is zero");
					zeros += 1;
				}
				else if(checkinteger % 2 == 0) {
					System.out.println(checkinteger + " is even");
					evens += 1;
				}
				else {
					System.out.println(checkinteger + " is odd");
					odds += 1;
				}
		}
		
		System.out.println("");
		System.out.println("Total even digits: " + evens);
		System.out.println("Total odd digits: " + odds);
		System.out.println("Total zero digits: " + zeros);
	}
}