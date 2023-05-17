/**
 * LR Parser
 * By Bradley Allen
 * Parses input ending with $ using given grammar rules and
 * parsing table.
 */

import java.util.Scanner;
import java.util.Stack;

public class LRParse {
	public static void main(String[] args) {
		String input = takeInput();
		
		// The sentence always has a $ symbol at the end
		if (!input.endsWith("$")) {
			System.err.println("Error invalid input: \"" + input + "\" does not end with $");
		}
		
		String[][] actionTable = buildTable("action");
		String[][] gotoTable = buildTable("goto");
		String[][] ruleTable = buildTable("rules");
		
		String curSentence = input;
		
		// Stack initiated and starts at state 0
		Stack<String> stk = new Stack<String>();
		stk.push("0");
		
		while (!curSentence.isEmpty()) {
			String state = stk.peek();
			int nextToken = takeNextToken(curSentence);
			String action = null;
			try {
				action = actionTable[Integer.parseInt(state)][nextToken];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("Error unrecognized token: " + curSentence.charAt(0));
				break;
			}
			
			// Print after each action
			System.out.println(stackString(stk) + " \t" + curSentence + " \t" + action);
			
			// If sentence accepted, end loop
			if (action.compareTo("acc") == 0) {
				break;
			}
			
			// Shift
			if (action.startsWith("s")) {
				if (nextToken == 0) {
					// Sentence starts with id
					stk.push(curSentence.substring(0, 2));
					stk.push(action.substring(1));
					curSentence = curSentence.substring(2);
				} else {
					// Sentence starts with +, *, (, or )
					stk.push(curSentence.substring(0, 1));
					stk.push(action.substring(1));
					curSentence = curSentence.substring(1);
				}
			}
			// Reduce
			else {
				int rule = Integer.parseInt(action.substring(1));
				String rightSide = ruleTable[rule-1][1]; // Side containing reduce rule
				String leftSide = ruleTable[rule-1][0]; // Side containing reduced term
				int gotoState = -1;
				while (!rightSide.isEmpty()) {
					if (rule == 6) {
						// reducing id
						if (stk.pop().compareTo(rightSide) == 0) {
							gotoState = Integer.parseInt(stk.peek());
							rightSide = "";
						}
					} else {
						if (stk.pop().compareTo(String.valueOf(rightSide.charAt(rightSide.length()-1))) == 0) {
							rightSide = rightSide.substring(0, rightSide.length()-1);
						}
					}
				}
				
				gotoState = Integer.parseInt(stk.peek());
				stk.push(leftSide); // Popped entire matching grammar rule, replace with left side of rule
				
				// Reduced, use goto table
				int newState = -1;
				if (stk.peek() == "E") {
					newState = Integer.parseInt(gotoTable[gotoState][0]);
				} else if (stk.peek() == "T") {
					newState = Integer.parseInt(gotoTable[gotoState][1]);
				} else {
					newState = Integer.parseInt(gotoTable[gotoState][2]);
				}
				stk.push(String.valueOf(newState));
			}
			
		}
	}
	
	private static String stackString(Stack<String> stk) {
		String str = stk.toString().replace("[", "").replace("]", "").replace(", ", "");
		return str;
	}
	
	private static int takeNextToken(String sentence) {
		int nextToken = -1;
		if (sentence.startsWith("id")) { nextToken = 0; }
		else if (sentence.startsWith("+")) { nextToken = 1; }
		else if (sentence.startsWith("*")) { nextToken = 2; }
		else if (sentence.startsWith("(")) { nextToken = 3; }
		else if (sentence.startsWith(")")) { nextToken = 4; }
		else if (sentence.startsWith("$")) { nextToken = 5; }
		return nextToken;
	}
	
	private static String[][] buildTable(String tbl) {
		String[][] table = null;
		
		if (tbl.equals("action")) {
			// Action table
			table = new String[12][6];
			// State 0
			table[0][0] = "s5";		// id
			table[0][3] = "s4";		// (
			// State 1
			table[1][1] = "s6";		// +
			table[1][5] = "acc";		// $
			// State 2
			table[2][1] = "r2";		// +
			table[2][2] = "s7";		// *
			table[2][4] = "r2";		// )
			table[2][5] = "r2";		// $
			// State 3
			table[3][1] = "r4";		// +
			table[3][2] = "r4";		// *
			table[3][4] = "r4";		// )
			table[3][5] = "r4";		// $
			// State 4
			table[4][0] = "s5";		// id
			table[4][3] = "s4";		// (
			// State 5
			table[5][1] = "r6";		// +
			table[5][2] = "r6";		// *
			table[5][4] = "r6";		// )
			table[5][5] = "r6";		// $
			// State 6
			table[6][0] = "s5";		// id
			table[6][3] = "s4";		// (
			// State 7
			table[7][0] = "s5";		// id
			table[7][3] = "s4";		// (
			// State 8
			table[8][1] = "s6";		// +
			table[8][4] = "s11";		// )
			// State 9
			table[9][1] = "r1";		// +
			table[9][2] = "s7";		// *
			table[9][4] = "r1";		// )
			table[9][5] = "r1";		// $
			// State 10
			table[10][1] = "r3";		// +
			table[10][2] = "r3";		// *
			table[10][4] = "r3";		// )
			table[10][5] = "r3";		// $
			// State 11
			table[11][1] = "r5";		// +
			table[11][2] = "r5";		// *
			table[11][4] = "r5";		// )
			table[11][5] = "r5";		// $
		}
		else if (tbl.equals("goto")) {
			// Goto table
			table = new String[12][3];
			// State 0
			table[0][0] = "1";		// E
			table[0][1] = "2";		// T
			table[0][2] = "3";		// F
			// State 4
			table[4][0] = "8";		// E
			table[4][1] = "2";		// T
			table[4][2] = "3";		// F
			// State 6
			table[6][1] = "9";		// T
			table[6][2] = "3";		// F
			// State 7
			table[7][2] = "10";		// F
		}
		else if (tbl.equals("rules")) {
			// Grammar rules
			table = new String[6][2];
			// Rule 1: E -> E + T
			table[0][0] = "E";
			table[0][1] = "E+T";
			// Rule 2: E -> T
			table[1][0] = "E";
			table[1][1] = "T";
			// Rule 3: T -> T * F
			table[2][0] = "T";
			table[2][1] = "T*F";
			// Rule 4: T -> F
			table[3][0] = "T";
			table[3][1] = "F";
			// Rule 5: F -> ( E )
			table[4][0] = "F";
			table[4][1] = "(E)";
			// Rule 6: F -> id
			table[5][0] = "F";
			table[5][1] = "id";
		}
		else {
			// Developer error handling
			System.err.println("buildTable given invalid build - \"" + tbl + "\"");
		}
		
		return table;
	}
	
	private static String takeInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();
		return input;
	}
}