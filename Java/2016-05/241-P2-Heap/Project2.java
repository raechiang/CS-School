/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 2: Heap
 * 
 * This project requires the student to implement a binary max-heap using an 
 * array. 100 randomly generated positive integers (without duplicates) must
 * be used for testing. Otherwise, the user can select to test with 100 
 * fixed values [1, 100]. The implementation must be able to build a heap 
 * with sequential insertions and with the optimal method. For both builds, 
 * a counter for the number of swaps done is required.
 * 
 * Testing:
 *  For the first tests (100 randomly generated integers), 20 sets (2000 
 * individual numbers total) must be computed. The program MUST output
 * the average number of swaps for sequential and optimal methods.
 *  For the second test, the first 10 integers of the array must be
 * printed as well as the number of swaps for sequential/optimal method. 
 * The heap needs to undergo 10 removals and output the first 10 integers
 * again.
 */
import java.util.Scanner;

/**
 * This class simply holds the main() method and has user input validation.
 */
public class Project2 {

	public Project2() {
	}

	/**
	 * This is the main method. It simply creates a Tester called test,
	 * accepts the user's input for which type of test to do, and starts
	 * the test.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tester test = new Tester();
		Scanner sc = new Scanner(System.in);
		
		boolean validEntry = false;
		int userIn = 0;
		
		while (!(validEntry))
		{
			System.out.println("Please select an option for testing.\n"
					+ " (1) 100 randomly generated integers\n"
					+ " (2) 100 fixed values [1, 100]\n"
					+ "Enter the corresponding integer [1, 2]: ");
			
			if (sc.hasNextInt())
			{
				userIn = sc.nextInt();
				if (userIn == 1 || userIn == 2)
				{
					validEntry = true;
				}
				else
				{
					System.out.println("Please try again. Input must be 1 or 2.");
				}
			}
			else
			{
				System.out.println("Please try again. Input must be 1 or 2.");
				sc.next();
			}
		}
		
		if (userIn == 1)
		{
			test.optionOne();
		}
		else
		{
			test.optionTwo();
		}
		
		System.out.println("Thank you for using the program.");

	}

}
