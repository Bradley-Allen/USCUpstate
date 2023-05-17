import java.util.Random;

public class PhoneNumberGenerator 
{
	public static void main(String[] args) 
	{
		// Initialization
		Random generator = new Random();
		int firstnum, secondnum, thirdnum, firstthree, lastfour;
		String areacode;
		
		// Handles number generation
		firstnum = generator.nextInt(8);
		secondnum = generator.nextInt(8);
		thirdnum = generator.nextInt(8);
		areacode = "" + firstnum + secondnum + thirdnum;
		firstthree = generator.nextInt(556) + 100;
		lastfour = generator.nextInt(9000) + 1000;
		
		// Prints phone number.
		System.out.print("Random Phone Number: " + areacode + "-" + firstthree + "-" + lastfour);
	}
}