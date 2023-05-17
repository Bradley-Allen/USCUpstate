import java.util.Scanner;

public class Age
{
	public static void main(String[] args)
	{
		int age;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter your age: ");
		age = scan.nextInt();
		
		if (age < 22)
		{
			System.out.println("Stay in school.");
		}
		else if (age >= 22 && age < 65)
		{
			System.out.println("Get a job.");
		}
		else
		{
			System.out.println("Time to retire.");
		}
	}
}