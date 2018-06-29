/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 1: Binary Search Tree Implementation
 * 
 * This project implements a binary search tree (BST) using recursion.
 * Adding nodes, deleting nodes, finding predecessors of nodes, and finding
 * successors of nodes are the necessary functions of this project.
 */

import java.util.Scanner;

/**
 * This class contains the {@link #main(String[])} method and user interaction.
 */
public class Project1 {

	/**
	 * This method first requests for the user to input a string of integers
	 * separated by spaces. It will then pass each integer to the BST to be
	 * inserted. After, the BST's initial values are printed out in three
	 * different orders depending on the traversal type (pre-, in-, and post-
	 * order traversal).
	 * 
	 * After, the loop for interacting with the tree begins. The human is 
	 * given the following options:
	 *  I (int) - to add an integer to the tree
	 *  D (int) - to remove an integer from the tree
	 *  P (int) - to find the predecessor of the given integer
	 *  S (int) - to find the successor of the given integer
	 *  H       - to display the "help" menu
	 *  E       - to exit the program
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Local variables
		boolean wantContinue = true;
		Tree binSearchTree = new Tree();
		String invalidInput = " Input invalid. Please ";
		Scanner sc = new Scanner(System.in);
		
		// Tree's initial values
		System.out.println("Please enter the initial sequence of integer values, "
				+ "each separated by a space: ");		
		String input = sc.nextLine();
		Scanner s = new Scanner(input);
		
		while (s.hasNext())
		{
			if (s.hasNextInt())
			{
				binSearchTree.add(s.nextInt(), binSearchTree.getRoot());
			}
			else
			{
				System.out.println(s.next() + " was ignored.");
			}
		}
		
		// Traversal print outs
		System.out.print(" Pre-Order Traversal: ");
		binSearchTree.preOrderTrav(binSearchTree.getRoot());
		System.out.println();
		System.out.print(" In-Order Traversal: ");
		binSearchTree.inOrderTrav(binSearchTree.getRoot());
		System.out.println();
		System.out.print(" Post-Order Traversal: ");
		binSearchTree.postOrderTrav(binSearchTree.getRoot());
		System.out.println();
		
		// Command loop
		while (wantContinue)
		{
			System.out.print("Command: ");
			input = sc.next();
			
			// Command executions
			switch (input.charAt(0))
			{
				case 'I': case 'i':
					// Insert new element
					if (sc.hasNextInt())
					{
						binSearchTree.add(sc.nextInt(), binSearchTree.getRoot());
						System.out.print(" In-Order Traversal: ");
						binSearchTree.inOrderTrav(binSearchTree.getRoot());
						System.out.println();
					}
					else
					{
						// Problem with the inputed command
						System.out.println(invalidInput + "specify integer to insert.");
						sc.next();
					}
					break;
				case 'D': case 'd':
					// Delete an element
					if (sc.hasNextInt())
					{
						int wantToDel = sc.nextInt();
						boolean isRemoved = binSearchTree.remove(wantToDel, binSearchTree.getRoot());
						if (isRemoved)
						{
							// Successful removal of an element
							System.out.println(" " + wantToDel + " was removed.");
							System.out.print(" In-Order Traversal: ");
							binSearchTree.inOrderTrav(binSearchTree.getRoot());
							System.out.println();
						}
						else
						{
							// The element does not exist!
							System.out.println(" " + wantToDel + " does not exist.");
						}
					}
					else
					{
						// Problem with the inputed command
						System.out.println(invalidInput + "specify integer to delete.");
						sc.next();
					}
					break;
				case 'P': case 'p':
					// Find predecessor
					if (sc.hasNextInt())
					{
						int target = sc.nextInt();
						if (binSearchTree.doesExist(target, binSearchTree.getRoot()))
						{
							BTNode pred = binSearchTree.getPredecessor(target, binSearchTree.getRoot());
							if (pred == null)
							{
								System.out.println(" " + target + " does not have a predecessor.");
							}
							else
							{
								System.out.println(" " + pred.getData() 
										+ " is the predecessor of " + target + ".");
							}
						}
						else
						{
							// Given target does not exist
							System.out.println(" " + target + " does not exist in the tree.");
						}
					}
					else
					{
						// Problem with the inputed command
						System.out.println(invalidInput + "specify for which integer to"
								+ " find the predecessor of.");
						sc.next();
					}
					break;
				case 'S': case 's':
					// Find successor
					if (sc.hasNextInt())
					{
						int target = sc.nextInt();
						if (binSearchTree.doesExist(target, binSearchTree.getRoot()))
						{
							BTNode suc = binSearchTree.getSuccessor(target,  binSearchTree.getRoot());
							if (suc == null)
							{
								System.out.println(" " + target + " has no successor.");
							}
							else
							{
								System.out.println(" " + suc.getData() 
										+ " is the successor of " + target + ".");
							}
						}
						else
						{
							// Bad target
							System.out.println(" " + target + " does not exist in the tree.");
						}
					}
					else
					{
						// Problem with input
						System.out.println(invalidInput + "specify for which integer to"
								+ " find the successor of.");
						sc.next();
					}
					break;
				case 'H': case 'h':
					// Print help in a fancy table
					System.out.println(
					" +---------+-----------------------+\n" +
					" | Command | Function              |\n" +
					" +---------+-----------------------+\n" +
					" | I <int> | Insert a value        |\n" +
					" | D <int> | Delete a value        |\n" +
					" | P <int> | Find predecessor      |\n" +
					" | S <int> | Find successor        |\n" + 
					" |    H    | Display this message  |\n" +
					" |    E    | Exit the program      |\n" +
					" +---------+-----------------------+\n" +
					" (Note: Lower-case commands are allowed!)");
					break;
				case 'E': case 'e':
					// Goodbye
					wantContinue = false;
					break;
				default:
					// Character didn't match any of the commands!
					System.out.println(invalidInput + "use a proper command.\n"
							+ " Input \'H\' for help.");
					break;
			}
		}
		System.out.println(" Thank you for using this program.");
		
		s.close();
		sc.close();
	}
}
