import java.text.NumberFormat;
import java.util.Scanner;

public class BankCheckCharges
{
	public static void main(String[] args)
	{
		int checks;
		double monthcharge, checkcharge, totalcharge;
		monthcharge = 10;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the number of checks written during the past month: ");
		checks = scan.nextInt();
		
		if (checks < 20)
		{
			checkcharge = 0.1;
		}
		else if (checks >= 20 && checks < 40)
		{
			checkcharge = 0.08;
		}
		else if (checks >= 40 && checks < 60)
		{
			checkcharge = 0.06;
		}
		else
		{
			checkcharge = 0.04;
		}
		
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		
		totalcharge = monthcharge + checks * checkcharge;
		System.out.println("Total fees for the month: " + fmt.format(totalcharge));
	}
}