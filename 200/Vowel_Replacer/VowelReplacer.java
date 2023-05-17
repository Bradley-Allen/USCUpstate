import java.util.Scanner;

public class VowelReplacer 
{
	public static void main(String[] args) 
	{
		// Initialization
		String phrase, phrasenoa, phrasenoae, phrasenoaei, phrasenoaeio, phrasenoaeiou;
		
		System.out.print("Enter a phrase: ");
		
		Scanner scan = new Scanner(System.in);
		phrase = scan.nextLine();
		
		// Handles replacing of vowels.
		phrasenoa = phrase.replace("a", "$");
		phrasenoae = phrasenoa.replace("e", "$");
		phrasenoaei = phrasenoae.replace("i", "$");
		phrasenoaeio = phrasenoaei.replace("o", "$");
		phrasenoaeiou = phrasenoaeio.replace("u", "$");
		
		// Prints phrase entered, but $ instead of vowels.
		System.out.println(phrasenoaeiou);
	}
}