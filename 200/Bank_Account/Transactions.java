public class Transactions
{
	public static void main(String[] args)
	{
		Account acct1 = new Account("Ted Murphy", 72354, 102.56);
		Account acct2 = new Account("Jane Smith", 69713, 40.00);
		Account acct3 = new Account("Edward Demsey", 93757, 759.32);
		
		acct1.deposit(25.85);
		
		double oldSmithBalance = acct2.getBalance();
		double smithBalance = acct2.deposit(500.00);
		
		if (oldSmithBalance > smithBalance) {
			System.out.println("Error: Attempted to deposit a negative value into account #69713.");
		}
		else {
			System.out.println("Smith balance after deposit: " + smithBalance);
			System.out.println("Smith balance after withdrawal: " + acct2.withdraw (430.75, 1.50));
			double balanceValidCheck = acct2.getBalance();
			if (balanceValidCheck < 0) {
				System.out.println("Error: Balance after withdrawl is negative for account #69713.");
			}
			else {
				acct1.addInterest();
				acct2.addInterest();
				acct3.addInterest();
				
				System.out.println();
				System.out.println(acct1);
				System.out.println(acct2);
				System.out.println(acct3);
			}
		}
	}
}