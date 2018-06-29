import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * This class tests all the methods implemented and displays the appropriate results. There are
 * 5 cases that are already set up, or the user can choose to input their own elements.
 */
class Testing {
	
	private Scanner sc = new Scanner(System.in);
	/*
	 * These are the two sets that will be used for testing subsetOf, isEqual, union, intersection, and
	 * complement.
	 */
	private Set A = new Set();
	private Set B = new Set();
	
	/*
	 * This method merely gives a bit of background for each of the options. Options 1 through 5 test
	 * preset sets A and B. Option 6 allows for the user to input their own elements.
	 */
	public void displayCases() {
		System.out.println("Option 1: A and B are equal but distinct sets.");
		System.out.println("Option 2: A and B have different sizes, but one is a subset of the other.");
		System.out.println("Option 3: A and B are non-empty and different in size but have common elements.");
		System.out.println("Option 4: A and B are non-empty with nothing in common.");
		System.out.println("Option 5: One is non-empty, and the other empty.");
		System.out.println("Option 6: Input elements for sets A and B.");
	}
	
	/*
	 * This method asks the user to select the desired case, which are represented by their corresponding
	 * integer values.
	 */
	public void selectCase() {
		displayCases();
		boolean invalidInput = true;
		while (invalidInput) {
			try {
				invalidInput = false;
				System.out.println("\nInput the corresponding integer [1, 6]: ");
				int caseNumber = sc.nextInt();
				
				if (caseNumber >= 1 && caseNumber <= 6) {
					switch (caseNumber) {
						case 1:
							System.out.println("CASE 1:");
							option1();
							break;
						case 2:
							System.out.println("CASE 2:");
							option2();
							break;
						case 3:
							System.out.println("CASE 3:");
							option3();
							break;
						case 4:
							System.out.println("CASE 4:");
							option4();
							break;
						case 5:
							System.out.println("CASE 5:");
							option5();
							break;
						case 6:
							System.out.println("CASE 6:");
							option6();
							break;
					}
				}
				else {
					System.out.println("Invalid input, please try again.");
					invalidInput = true;
				}
				
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input, please try again.");
				invalidInput = true;
				sc.next();
			}
		}
	}
	
	/*
	 * This method adds the elements to the sets. A and B are equal but distinct sets.
	 */
	public void option1() {
		// A = {1, 2, 3}, B = {2, 1, 3}
		A.addElement(1);
		A.addElement(2);
		A.addElement(3);
		B.addElement(2);
		B.addElement(1);
		B.addElement(3);
		theProcess();
	}
	
	/*
	 * This method adds the elements to the sets. A and B have different sizes, and A is the subset
	 * of B.
	 */
	public void option2() {
		// A = {1}, B = {1, 2}
		A.addElement(1);
		B.addElement(1);
		B.addElement(2);
		theProcess();
	}
	
	/*
	 * This method adds the elements to the sets. A and B are different in size and have some common and
	 * uncommon elements.
	 */
	public void option3() {
		// A = {1, 2, 3}, B = {2, 3, 4, 5}
		A.addElement(1);
		A.addElement(2);
		A.addElement(3);
		B.addElement(2);
		B.addElement(3);
		B.addElement(4);
		B.addElement(5);
		theProcess();
	}
	
	/*
	 * This method adds the elements to the sets. A and B are different sizes and contain completely
	 * different elements.
	 */
	public void option4() {
		// A = {1}, B = {2, 3}
		A.addElement(1);
		B.addElement(2);
		B.addElement(3);
		theProcess();
	}
	
	/*
	 * This method adds elements to the sets. A has some elements, whereas B is an empty set.
	 */
	public void option5() {
		// A = {2, 3, 5, 7}, B = {}
		A.addElement(2);
		A.addElement(3);
		A.addElement(5);
		A.addElement(7);
		theProcess();
	}
	
	/*
	 * This method allows the user to add elements to the sets. The user also has the option to remove
	 * elements from the current set they are adding to.
	 */
	public void option6() {
		// wantsQuit is used to keep the loop running until the user inputs "quit"
		boolean wantsQuit = false;
		System.out.println("Instructions: Please input elements, separated by spaces or enters (new line).\n" +
							"              Note, elements are case sensitive. While inputting into set A, \n" +
							"              enter 'quit' to finish; while inputting into set B, enter 'quit'\n" +
						    "              to finish. To remove a previously entered element in the same set,\n" +
						    "              enter 'remove'.");
		System.out.println("Input elements for set A: ");
		while ( !(wantsQuit) ) {
			Object input = sc.next();
			if (input.toString().toLowerCase().equals("quit")) {
				wantsQuit = true;
			}
			else if (input.toString().toLowerCase().equals("remove")) {
				// The user can remove an unwanted element.
				System.out.println("A = " + A.toString());
				System.out.println("What object would you like to remove? ");
				Object removeThis = sc.next();
				if (A.remove(removeThis)) {
					System.out.println("Removal successful. Continuing set A input: ");
				}
				else {
					System.out.println("Removal unsuccessful. Continuing set A input: ");
				}
			}
			else {
				A.addElement(input);
			}
		}
		
		wantsQuit = false;
		System.out.println("Input elements for set B: ");
		while ( !(wantsQuit) ) {
			Object input = sc.next();
			if (input.toString().toLowerCase().equals("quit")) {
				wantsQuit = true;
			}
			else if (input.toString().toLowerCase().equals("remove")) {
				System.out.println("B = " + B.toString());
				System.out.println("What object would you like to remove? ");
				Object removeThis = sc.next();
				if (B.remove(removeThis)) {
					System.out.println("Removal successful. Continuing set B input: ");
				}
				else {
					System.out.println("Removal unsuccessful. Continuing set B input: ");
				}
			}
			else {
				B.addElement(input);
			}
		}
		
		theProcess();
	}
	
	/*
	 * This method tests the sets for subsetOf, isEqual, union, intersection, and complement, and
	 * displays the results. This method is called at the end of every Option.
	 */
	public void theProcess() {
		Set cUnion = A.union(B);
		Set cIntersect = A.intersection(B);
		Set cComplement = A.complement(B);
		Set cComplement2 = B.complement(A);
		String spacing = "    ";
		
		System.out.println("\tA = " + A.toString());
		System.out.println("\tB = " + B.toString());
		
		System.out.println(spacing + "B is a subset of A:   " + A.subsetOf(B));
		System.out.println(spacing + "A is a subset of B:   " + B.subsetOf(A));
		System.out.println(spacing + "A is equal to B:      " + A.isEqual(B));
		System.out.println(spacing + "A in union with B:    C = " + cUnion.toString());
		System.out.println(spacing + "A intersects B:       C = " + cIntersect.toString());
		System.out.println(spacing + "A complements B:      C = " + cComplement.toString());
		System.out.println(spacing + "B complements A:      C = " + cComplement2.toString());
		System.out.println();
	}
}
