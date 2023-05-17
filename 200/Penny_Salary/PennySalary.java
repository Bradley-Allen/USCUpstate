import java.text.NumberFormat;
import java.util.Scanner;

public class PennySalary
{
	public static void main(String[] args)
	{
		int daysworked;
		double salary = 0, totalsalary = 0;
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		
		System.out.print("Enter the days worked during the month: ");
		Scanner scan = new Scanner(System.in);
		daysworked = scan.nextInt();
		scan.close();
		
		if(daysworked > 31) {
			System.out.println("Error: 'daysworked' greater than 31 -- Please enter the days worked during one month.");
		}
		else {
		for(int i=1; i<=daysworked; ++i)
		{
			if(salary == 0)
				salary += 0.01;
			else
			salary = salary * 2;
			totalsalary += salary;
			System.out.println("On day " + i + ", you earned: " + fmt.format(salary));
		}
		
		System.out.println("Total pay earned during the month: " + fmt.format(totalsalary));
		}
	}
}