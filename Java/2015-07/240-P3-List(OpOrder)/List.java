/*
 * Rachel Chiang, CS 240-E01
 * Project 3: Stack Application
 * 
 * This class simply holds all of the data which will be displayed later to the user.
 * I used a singly linked list so that the user could input as multiple expressions.
 * Nested, of course, is the Expression (Node) class.
 */
class List {
	// This field saves the head
	private Expression head;
	// This field saves the size.
	private int size;
	
	/*
	 * This initializes the list's size to zero.
	 */
	public List() {
		size = 0;
	}
	
	/*
	 * This method accepts three Strings as parameters. The arguments are, in order,
	 * the user's infix expression, the postfix expression, and the prefix expression of it.
	 */
	public void addLast(String userIn, String post, String pre) {
		// if it is empty, the input is saved as the head
		if (size == 0) {
			head = new Expression(userIn, post, pre, null);
		}
		else {
			// otherwise, it traverses the list until the current node's next is null. It
			// attaches the new expressions to the next of the current node.
			Expression cur = head;
			Expression newExpr = new Expression(userIn, post, pre, null);
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = newExpr;
		}
		size++; // increase the size
	}
	
	/*
	 * This method prints a neat little table.
	 */
	public void printTable() {
		Expression cur = head;
		if (cur == null) {
			// This is for when the user did not input anything and simply entered "quit."
			System.out.println("You have quit without input.");
			System.exit(0);
		}
		
		String border = "+=====================+=====================+=====================+";
		
		// This is the top of the table, with appropriate column labels.
		System.out.println(border);
		System.out.printf("|%-21s|%-21s|%-21s|\n", "Infix", "Postfix", "Prefix");
		System.out.println(border);
		
		// Goes through the list, printing out the three expressions in the appropriate boxes.
		while (cur != null) {
			System.out.printf( "|%-21s|%-21s|%-21s|\n", cur.userInfix, cur.postfix, cur.prefix);
			System.out.println(border); // closes each row
			cur = cur.next;
		}
		
	}
	
	/*
	 * This is a nested class, Expression, which is essentially a node containing the three Strings
	 * (userInfix, postfix, prefix) and the next node.
	 */
	private static class Expression {
		// User's inputed expression
		String userInfix;
		// the userInfix converted to postfix
		String postfix;
		// the userInfix converted to prefix
		String prefix;
		// the next node
		Expression next;
		
		// Initialized when creating a new node
		Expression(String userIn, String post, String pre, Expression n) {
			userInfix = userIn;
			postfix = post;
			prefix = pre;
			next = n;
		}
	}
	
}
