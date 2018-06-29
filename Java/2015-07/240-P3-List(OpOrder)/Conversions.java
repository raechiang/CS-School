import java.util.Stack;

/*
 * Rachel Chiang, CS 240-E01
 * Project 3: Stack Application
 * 
 * This class uses stacks to convert an inputed Infix expression into a postfix
 * expression and a prefix expression. It contains the following methods:
 * Conversions(String), inToPost(), inToPre(), makeItPrettier(char[]),
 * isParenMatch(), and validOperands().
 */
class Conversions {
	/*
	 * This field saves the user's inputed expression as a character array.
	 * In retrospect, I could've kept it as a String and used charAt(int).
	 */
	private char[] userInfix;
	
	/*
	 * This method initializes the userInfix field with the user's given
	 * input (which is provided as an argument) after converting it to a
	 * char array.
	 */
	public Conversions(String userInput) {
		userInfix = userInput.toCharArray();
	}
	
	/*
	 * This method converts the infix expression into a postfix expression.
	 * It utilizes one stack for operators, pushing, popping, or recording
	 * depending on the type of operator at a given index in the array, and
	 * it always records the operands into the postfix String, which is the
	 * String that is returned.
	 */
	public String inToPost() {
		// Necessary fields created and initialized
		Stack<Character> operators = new Stack<Character>();
		String postfix = "";
		
		// For loop to traverse the characters of the infix expression
		for (int i = 0; i < userInfix.length; i++) {
			if (userInfix[i] == '(' || userInfix[i] == '[' || userInfix[i] == '{') {
				// If it is a left (opening) parenthesis, bracket, or brace, the
				// character is pushed onto the operators stack
				operators.push(userInfix[i]);
			}
			else if (userInfix[i] == '+' || userInfix[i] == '-') {
				// If it is a plus or minus sign, the precedence will be equal to
				// or less than the previous operator.
				if (operators.empty()) {
					// If the stack is empty, the operator is simply pushed onto it.
					operators.push(userInfix[i]);
				}
				else {
					// used to terminate the loop. Makes the conditions in the while-loop look a lot
					// neater
					boolean term = false;
					// Otherwise it must check the precedence
					while (!(operators.empty()) && !(term)) {
						// Loops until the operators stack is no longer empty and the next one is not an
						// opening parenthesis, bracket, brace
						if (operators.peek() == '/' || operators.peek() == '*' || operators.peek() == '%'
								|| operators.peek() == '-' || operators.peek() == '+') {
							// if the precedence is the same or lesser, the top of the operators stack
							// is popped off and placed into the postfix String
							postfix += operators.pop();
						}
						else {
							term = true;
						}
					}
					// After all that checking, the char being checked is pushed onto the stack
					operators.push(userInfix[i]);
				}
			}
			else if (userInfix[i] == '/' || userInfix[i] == '*' || userInfix[i] == '%') {
				// If the char being pointed to is /, *, or %, the precedence will be
				// higher or the same
				if (operators.empty()) {
					// if the operators stack is empty, simply push the char onto the stack
					operators.push(userInfix[i]);
				}
				else {
					// if the stack is not empty, proceed with checking the precedence.
					if (operators.peek() == '-' || operators.peek() == '+') {
						// higher precedence, so simply pushed onto the stack
						operators.push(userInfix[i]);
					}
					else {
						// If the operator at the top of the stack is not - or +:
						
						// Boolean used to terminate the loop
						boolean term = false;
						
						while ( !(operators.empty()) && !(term)) {
							// Loops while the operators stack is not empty and term is false
							if (operators.peek() == '*' || operators.peek() == '/' || operators.peek() == '%') {
								// if the operator at the top of the stack is of the same precedence,
								// it is popped off and added to the postfix String.
								postfix += operators.pop();
							}
							else {
								// If any of the other operators are found, it terminates the loop.
								term = true;
							}
						}
						// the char is pushed onto the stack
						operators.push(userInfix[i]);
					}
				}
			}
			else if (userInfix[i] == ')' || userInfix[i] == ']' || userInfix[i] == '}') {
				// If a closing parenthesis, bracket, or brace is scanned
				while (operators.peek() != '(' && operators.peek() != '[' && operators.peek() != '{') {
					// The operators stack is popped into the postfix String until the opening bracket
					// is found
					postfix += operators.pop();
				}
				// And the opening operator is popped off
				operators.pop();
				
			}
			else if (userInfix[i] == ' ') {
				// If the scanned char is a space, do nothing
			}
			else {
				// If the scanned char is a variable, add it to the postfix String
				postfix += userInfix[i];
			}
			
		}
		
		// Makes sure the operators stack is cleared.
		while ( !(operators.empty()) ) {
			postfix += operators.pop();
		}
		
		// QoL thing for spaced-out output.
		postfix = makeItPrettier(postfix.toCharArray());
		
		// returns the postfix String
		return postfix;
	}
	
	/*
	 * This method returns a the prefix expression of the infix version as a String. It 
	 * converts the infix expression by utilizing two stacks--one for operands and one
	 * for operators. How the push and pop is handled depends on the type of character
	 * and its precedence.
	 */
	public String inToPre() {
		// Fields initializing the Strings prefix, op, leftOperand, and rightOperand.
		String prefix = "";
		String op = "";
		String leftOperand = "";
		String rightOperand = "";
		
		// Creation of the operators and operands stacks
		Stack<String> operators = new Stack<String>();
		Stack<String> operands = new Stack<String>();
		
		// For loop that travels through the array
		for (int i = 0; i<userInfix.length; i++) {
			// characters are converted to strings so they can be added to the String
			// fields and stacks listed above
			String s = "" + userInfix[i];
			if (userInfix[i] == '(' || userInfix[i] == '[' || userInfix[i] == '{') {
				// If the scanned character is an opening parenthesis, bracket, or brace,
				// push it onto the Stack as a String
				operators.push(s);
			}
			else if (userInfix[i] == ')' || userInfix[i] == ']' || userInfix[i] == '}') {
				// If it is a closing parenthesis, bracket, or brace,
				while ( !(operators.peek().equals("(")) && !(operators.peek().equals("["))
						&& !(operators.peek().equals("{")) ) {
					// loop through the stack until the opening piece is found,
					// and pop operators into op and two operands into rightOperand and leftOperand
					op = operators.pop();
					rightOperand = operands.pop();
					leftOperand = operands.pop();
					// Then, put the three together
					op += leftOperand + rightOperand;
					// and push them onto the operands stack
					operands.push(op);
				}
				// Finally, pop off the opening parenthesis, bracket, or brace
				operators.pop();
			}
			else if (userInfix[i] == ' ') {
				// If a space is encountered, do nothing
			}
			else if (userInfix[i] == '*' || userInfix[i] == '/' || userInfix[i] == '%') {
				// If a *, /, or % is scanned
				if (operators.empty()) {
					// if it is empty, simply push it.
					operators.push(s);
				}
				else {
					// Otherwise, we must check precedence.
					if (operators.peek().equals("-") || operators.peek().equals("+")) {
						// If the next operator ont he stack is - or +, simply push onto the stack.
						operators.push(s);
					}
					else {
						// used to terminate the loop
						boolean term = false;
						
						while ( !(operators.empty()) && !(term) ) {
							// while the operators stack is not empty and terminate is false
							if (operators.peek().equals("*") || operators.peek().equals("/") 
									|| operators.peek().equals("%")) {
								// if the top of the operators stack is the same precedence,
								// pop off the operator into op and two operands into rightOperand
								// and leftOperand.
								op = operators.pop();
								rightOperand = operands.pop();
								leftOperand = operands.pop();
								// Join them altogether and then push it onto the operands stack
								op += leftOperand + rightOperand;
								operands.push(op);
							}
							else {
								// If a different operator than the above listed is encountered,
								// terminate the loop
								term = true;
							}
						}
						// push the currently scanned operator onto the operators stack.
						operators.push(s);
					}
				}
			}
			else if (userInfix[i] == '+' || userInfix[i] == '-') {
				// If a + or - is being scanned
				if (operators.empty()) {
					// if it is empty, push it onto the operators stack.
					operators.push(s);
				}
				else {
					// otherwise,
					boolean term = false;
					while ( !(operators.empty()) && !(term)) {
						// Loops while the operators stack is not empty and term is false
						if (operators.peek().equals("/") || operators.peek().equals("*")
								|| operators.peek().equals("%") || operators.peek().equals("-") 
								|| operators.peek().equals("+") ) {
							// if the operator on the top is of higher or equal precedence,
							// the top operator is popped into op, and two operands are popped
							// into rightOperand and leftOperand
							op = operators.pop();
							rightOperand = operands.pop();
							leftOperand = operands.pop();
							// the three strings are put together and pushed onto the operands stack
							op += leftOperand + rightOperand;
							operands.push(op);
						}
						else {
							term = true;
						}
					}
					// pushes the operator being scanned onto the operators stack.
					operators.push(s);
				}
			}
			else {
				// If it was none of the above, it must have been an operand, and it will be pushed
				// onto the operands stack
				operands.push(s);
			}
			
		}
		
		// Ensures that the operators stack is empty, and makes sure to place
		// everything into the operands stack.
		while ( !(operators.empty()) && !(operands.empty()) ) {
			op = "";
			rightOperand = "";
			leftOperand = "";
			op = operators.pop();
			rightOperand = operands.pop();
			leftOperand = operands.pop();
			op += leftOperand + rightOperand;
			operands.push(op);
		}
		
		// prefix is created and then the operands and operators are
		// spaced out with makeItPrettier(char[])
		prefix = operands.pop();
		prefix = makeItPrettier(prefix.toCharArray());
		
		// The prefix String is returned.
		return prefix;
	}
	
	/*
	 * This simple method is sort of optional, I assume, but I didn't like how the
	 * operators and operands printed mashed together, thereby degrading readability.
	 * It accepts a character array as an argument and returns a String with one space
	 * between each character.
	 */
	public String makeItPrettier(char[] e) {
		// new string to be returned is initialized
		String newStr = "";
		// for loop runs through the character array
		for (int i=0; i<e.length; i++) {
			// adds the character to the String
			newStr+=e[i];
			if (i+1 != e.length) {
				// adds a space unless it's the last character in the array
				newStr+=" ";
			}
		}
		return newStr;
	}
	
	/*
	 * This method verifies if the parentheses, brackets, and braces have correctly placed
	 * corresponding pairs by using a stack. If it doesn't, false is returned.
	 * Specifically, it checks to see if an expression was given at all, if every opening
	 * piece has a closing pair, and if the pairs correspond (in order).
	 */
	public boolean isParenMatch() {
		// Checks to make sure the user actually inputed something
		if (userInfix.length == 0) {
			System.out.println("     No expression given.");
			return false;
		}
		
		Stack<Character> theStack = new Stack<Character>();
		
		for (int i = 0; i < userInfix.length; i++) {
			// for loop to go through the array
			if (userInfix[i] == '(' || userInfix[i] == '[' || userInfix[i] == '{') {
				// if it finds an opening operator, pushes opening parenthesis, bracket, or brace
				theStack.push(userInfix[i]);
			}
			else if (userInfix[i] == ')' || userInfix[i] == ']' || userInfix[i] == '}') {
				// if it finds a closing piece
				if (theStack.empty()) {
					// if it's empty then it is wrong and returns false
					System.out.println("     Invalid input." +
										"\n     Cannot start with closing parenthesis.");
					return false;
				}
				switch (userInfix[i]) {
				// checks each of the three cases, and if it doesn't correspond, it returns false
				// otherwise, pop the stack
					case ')':
						if (theStack.peek() != '(') {
							System.out.println("     Invalid input.\n     " +
									userInfix[i] + " did not match previous.");
							return false;
						}
						else {
							theStack.pop();
						}
						break;
					case ']':
						if (theStack.peek() != '[') {
							System.out.println("     Invalid input.\n     " +
									userInfix[i] + " did not match previous.");
							return false;
						}
						else {
							theStack.pop();
						}
						break;
					case '}':
						if (theStack.peek() != '{') {
							System.out.println("     Invalid input.\n     " +
									userInfix[i] + " did not match previous.");
							return false;
						}
						else {
							theStack.pop();
						}
						break;
				}
			}
			
		}
		
		// At the end, all parentheses should have cancelled itself out
		if (theStack.empty()) {
			return true;
		}
		else {
			System.out.println("     Invalid input.");
			System.out.println("     Check for pairs of corresponding parentheses, brackets, or braces.");
			return false;
		}
	}
	
	/*
	 * This method checks to see if the input of operands and operators are correct. It returns
	 * a boolean value depending on whether it is or isn't.
	 * Specifically, it checks if the number of operands and operators is valid and if they are
	 * in order (for instance, x + y works but y++x and xx+z doesn't).
	 */
	public boolean validOps() {
		
		// used to count if the number of operands and operators are acceptable
		int operandCounter = 0;
		int operatorCounter = 0;
		
		// used to check the order of things
		Stack<Character> theStack = new Stack<Character>();
		int size = 0;
		
		for (int i = 0; i < userInfix.length; i++) {
			// goes through the characters in the user's input
			if (userInfix[i] == '(' || userInfix[i] == ')'
					|| userInfix[i] == '[' || userInfix[i] == ']'
					|| userInfix[i] == '{' || userInfix[i] == '}') {
				// Disregarded because they're checked in isParenMatch()
			}
			else if (userInfix[i] == ' ') {
				// if it's a space, do nothing
			}
			else {
				theStack.push(userInfix[i]);
				size++;
				
				if (userInfix[i] == '+' || userInfix[i] == '-'
						|| userInfix[i] == '*' || userInfix[i] == '/'
						|| userInfix[i] == '%') {
					// this counts how many operators there are
					operatorCounter++;
				}
				else {
					//This counts how many operands there are
					operandCounter++;
				}
			}
		}
		
		// I figured that in a standard expression, the number of operators should be one less
		// than the number of operands, which prevents, for instance, expressions like x + y +
		if (operandCounter-1 != operatorCounter) {
			System.out.println("     Invalid input." +
								"\n     Issue with the amounts of operands and operators.");
			return false;
		}
		
		// Created a new array with only the operators and operands
		// Examples of Bad Input: + y - x, x + + y, -a b
		// I probably could have done it without the array, but this seemed easier to read and understand.
		char[] arr = new char[size];
		for (int i = size - 1; i >= 0; i--) {
			arr[i] = theStack.pop();
		}
		// for loop traverses the array
		for (int i = 0; i < size; i++) {
			if (i%2==0) {
				// if the index is even, it should be an operand
				if (arr[i] == '+' || arr[i] == '-' || arr[i] == '%'
						|| arr[i] == '/' || arr[i] == '*') {
					// if it is not an operand, then it is wrong
					System.out.println("     Invalid input." +
										"\n     Order of operators and operands is incorrect.");
					return false;
				}
			}
			else {
				// if the index is odd, it should be an operator
				if (arr[i] != '+' && arr[i] != '-' && arr[i] != '%'
						&& arr[i] != '/' && arr[i] != '*') {
					System.out.println("     Invalid input." +
										"\n     Order of operators and operands is incorrect.");
					return false;
				}
			}
		}
		
		return true;
	}

}
