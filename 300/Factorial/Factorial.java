import java.util.Scanner;

public class Factorial
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		double factorial = 1;
		System.out.print("Enter a number: ");
		double number = scan.nextDouble();
		scan.close();
		
		for(double i=number;i>1;--i)
		{
			factorial *= i;
		}
		
		System.out.println("The factorial of " + number + " is " + factorial);
	}
}