import java.util.Scanner;

/*
 * Rachel Chiang, CS 240-E01
 * Project 3: Stack Application
 * 
 * This class holds the main method and communicates between Conversions and List,
 * and it accepts user input.
 */
public class Project3 {

	/*
	 * This method is the main method. It requests user input for an expression and
	 * calls on Conversions and List. After the user inputs an infix expression, 
	 * it is converted using Conversions, and the process repeats from the beginning
	 * (asking the user to input another expression). When the user is done with
	 * input, they may enter "quit". A table of each of the valid expressions is then
	 * printed in its original Infix form and its postfix and prefix forms.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// A counter because I thought it'd look nicer than repeating the instructions
		int numOfExp = 1;
		
		Conversions convertWhat;
		List listOfExp = new List();
		
		// A boolean to terminate the while loop
		boolean wantContinue = true;
		
		System.out.println("INSTRUCTIONS: Please input one or more infix arithmetic expressions,\n"
				+ "separated by enter, or 'quit' to stop inputting.");
		while (wantContinue) {
			if (numOfExp > 3) { // for the counter, QoL kind of thing.
				System.out.print(numOfExp + "th: ");
			}
			else {
				switch (numOfExp) {
					case 1:
						System.out.print(numOfExp + "st: ");
						break;
					case 2:
						System.out.print(numOfExp + "nd: ");
						break;
					case 3:
						System.out.print(numOfExp + "rd: ");
				}
			}
			
			String input = sc.nextLine();
			
			if (input.equalsIgnoreCase("quit")) {
				wantContinue = false;
			}
			else {
				convertWhat = new Conversions(input);
				// Checks to see if the inputed expression is a valid expression.
				if (convertWhat.isParenMatch() && convertWhat.validOps()) {
					// If it passes both error tests, the expression is converted into postfix
					// and prefix, and then the three are saved into a linked list.
					listOfExp.addLast(input, convertWhat.inToPost(), convertWhat.inToPre());
					numOfExp++;
				}
				else {
					// Does nothing and loops back around for the user to try inputting again.
					System.out.println("Please try again.");
				}
			}
		}
		
		// Simply prints the table.
		listOfExp.printTable();
	}

}
