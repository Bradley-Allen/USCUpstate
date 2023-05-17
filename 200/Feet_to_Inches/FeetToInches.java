public class FeetToInches
{
	public static void main(String[] args)
	{
		int TotalInches = 86;
		
		int Feet = TotalInches / 12;
		int Inches = TotalInches % 12;
		
		System.out.println(TotalInches + " inches is equivalent to " + Feet + " feet and " + Inches + " inches.");
	}
}
