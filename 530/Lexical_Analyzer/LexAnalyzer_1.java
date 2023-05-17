/**
 * Simple Lexical Analyzer
 * Written by Bradley Allen
 * Uses input file name to print all
 * recognized tokens.
 * 
 * Keywords (case sensitive): int, double, String
 * Operators: =, (, ), +, -, *, /, ,, ;
 * Identifier: letter(letter|digit)*
 * int constant
 * double constant
 * string constant (enclosed by pair of "")
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LexAnalyzer {
	
	static Scanner input = new Scanner(System.in);
	static ArrayList<String> keywords = new ArrayList<>(Arrays.asList("int", "double", "String"));
	static ArrayList<String> operators = new ArrayList<>(Arrays.asList("=", "(", ")", "+", "-", "*", "/", ",", ";"));
	
	/**
	 * Reads character-by-character and groups for token categorization
	 * @param line
	 * @param line count
	 */
	private static void analyzeLine(String line, int linecount) {
		// Holds token being built
		ArrayList<String> tokenConstruct = new ArrayList<>();
		// List of tokens built by line
		ArrayList<String> analyzedLine = new ArrayList<>();
		
		for (int i=0; i<line.length(); ++i) {
			Character character = line.charAt(i);
			if(operators.contains(character.toString())) {
				// The character is an operator
				if (!tokenConstruct.isEmpty()) {
					analyzedLine.add(tokenConstruct.toString());
					tokenConstruct.clear();
				}
				tokenConstruct.add(character.toString());
				analyzedLine.add(tokenConstruct.toString());
				tokenConstruct.clear();
			} else if(Character.isDigit(character)) {
				// The character is a number
				tokenConstruct.add(character.toString());
			} else if(Character.isLetter(character)) {
				// The character is a letter
				tokenConstruct.add(character.toString());
			} else if(character.toString().equals(" ")) {
				// The character is a space
				if (!tokenConstruct.isEmpty()) {
					analyzedLine.add(tokenConstruct.toString());
					tokenConstruct.clear();
				}
			} else if(character.toString().equals(".")) {
				// The character is a decimal/period
				tokenConstruct.add(character.toString());
			} else if(character.toString().equals("\"")) {
				// The character is a quotation mark
				tokenConstruct.add(character.toString());
			} else {
				// The character is an unrecognized symbol
				if (!tokenConstruct.isEmpty()) {
					analyzedLine.add(tokenConstruct.toString());
					tokenConstruct.clear();
				}
				tokenConstruct.add(character.toString());
				analyzedLine.add(tokenConstruct.toString());
				tokenConstruct.clear();
			}
		}
		
		// Finish current token if not already finished
		if (!tokenConstruct.isEmpty()) {
			analyzedLine.add(tokenConstruct.toString());
			tokenConstruct.clear();
		}
		
		// Find and print category for each token
		categorize(analyzedLine, linecount);
	}
	
	/**
	 * Checks which category the token is in
	 * @param category to be checked
	 * @param token
	 * @return true/false from category
	 */
	private static boolean categoryChecker(String category, String token) {
		if (category == "identifier") {
			Character first = token.charAt(0);
			if (Character.isLetter(first) && !token.contains(".")) {
				return true;
			}
			return false;
		} else if (category == "int") {
			try {
				Integer.parseInt(token);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else if (category == "double") {
			try {
				Double.parseDouble(token);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else if (category == "string") {
			if (((Character)token.charAt(0)).toString().equals("\"") && ((Character)token.charAt(token.length()-1)).toString().equals("\"")) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Prints line number, token category, and token
	 * @param line
	 * @param line count
	 */
	private static void categorize(ArrayList<String> line, int linecount) {
		for (int i=0; i<line.size(); ++i) {
			String token = combineCharacters(line.get(i));
			if (!token.equals(" ")) {
				if (keywords.contains(token)) {
					System.out.println("Line " + linecount + " keyword: " + token);
				} else if (operators.contains(token)) {
					System.out.println("Line " + linecount + " operator: " + token);
				} else if (categoryChecker("identifier", token)) {
					System.out.println("Line " + linecount + " identifier: " + token);
				} else if (categoryChecker("int", token)) {
					System.out.println("Line " + linecount + " int constant: " + token);
				} else if (categoryChecker("double", token)) {
					System.out.println("Line " + linecount + " double constant: " + token);
				} else if (categoryChecker("string", token)) {
					System.out.println("Line " + linecount + " string constant: " + token);
				} else {
					System.out.println("Line " + linecount + " error: " + token + " not recognized");
				}
			}
		}
	}
	
	/**
	 * Removes brackets and commas from character list to make token
	 * @param character
	 * @return combined token
	 */
	private static String combineCharacters(String character) {
		String combined = character.substring(1, character.length()-1);
		if (Character.isLetter((Character)combined.charAt(0)) || Character.isDigit((Character)combined.charAt(0)) || ((Character)combined.charAt(0)).toString().equals("\"")) {
			combined = combined.replace(", ", "");
		}
		return combined;
	}
	
	/**
	 * Reads in file for line-by-line analysis
	 * @param filename
	 */
	private static void readFile(String filename) {
		try {
			File file = new File(filename);
			Scanner filescan = new Scanner(file);
			int linecount = 1;
			while (filescan.hasNextLine()) {
				analyzeLine(filescan.nextLine(), linecount++);
			}
			filescan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompts user for input file name
	 * @return filename
	 */
	private static String takeFileInput() {
		System.out.print("Input file name: ");
		String filename = input.next();
		return filename;
	}
	
	/**
	 * Driver method
	 */
	public static void main(String[] args) {
		String filename = takeFileInput();
		System.out.println("File: " + filename);
		readFile(filename);
	}
}
