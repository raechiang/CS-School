import java.io.*;
import java.util.*;

/*
 * Project 2: Hashing, Rachel Chiang, CS 240-E01
 * 
 * This class contains the main method. It receives a directory name given by the user when
 * the program is run and takes reads the files in the directory, recording the names and 
 * scores as the keys and values, which are saved as Entries in a hash table. After, it will
 * print out the following quantities: collisions, table size, and names (individual entries).
 * Then, it will print the minimum and maximum averages and the entries that possess those
 * corresponding averages. Lastly, it requests user to input a name (the key) and responds
 * with the name, average, and number of scores.
 */
public class Project2 {

	/*
	 * This is the main method, with parameter as an array of Strings. The first String in
	 * the array, args[0], is used as the address for the directory, in which files containing
	 * information for the key and key's values are used as Entries in a hash table. Lastly, it
	 * calls a method to print all of the required information, including the interactive
	 * portion.
	 */
	public static void main(String[] args) throws IOException {
		Hashing hashTable = new Hashing();
				
		try {
			File directory = new File(args[0]);

			File[] files = directory.listFiles();

			File file;
			Scanner input;
			
			// Checks if the directory provided contains files
			if (files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					input = new Scanner(files[i]);

					String key;
					double value = 0;
					while (input.hasNext()) {
						key = "";
						while (!(input.hasNextDouble())) {
							key += input.next();
							// Only inserts the space between the names
							if (!(input.hasNextDouble())) {
								key += " ";
							}
						}
						if (input.hasNextDouble()) {
							value = input.nextDouble();
						}
						// Saves the key and the value in the hash table
						hashTable.put(key, value);
					}
				}
				// Calls the method printInformation(Hashing)
				printInformation(hashTable);
			}
			else { // when the directory has no files
				System.out.println("Error: The given directory is empty.");
				System.exit(0);
			}
			System.out.println();
		} catch (NullPointerException e) {
			System.out.println("Error: Directory name invalid.");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException aie) {
			System.out.println("Error: Directory name is missing.");
			System.out.println("Usage: java Project2 directory_name");
			System.exit(0);
		}

	}

	/*
	 * This method prints the information regarding the hash table. It gives the number of
	 * collisions, the size of the table, the number of names, and then prints the minimum
	 * and maximum averages with their corresponding names. Lastly, it asks the user to
	 * input a name for the program to find in the hash table and prints the name's
	 * information.
	 */
	public static void printInformation(Hashing hashTable) {
		hashTable.printTableInfo(); // prints quantity of collisions and table size
		hashTable.findMinAvg(); // prints minimum average
		hashTable.findMaxAvg(); // prints maximum average
		System.out.println();

		Scanner sc = new Scanner(System.in);
		

		boolean wantContinue = true;
		while (wantContinue) {
			System.out.print("Enter name or 'q' to quit: ");
			String s = sc.nextLine();
			if (s.equalsIgnoreCase("q")) {
				wantContinue = false;
				System.out.println("You have quit.");
				System.exit(0);
			} else {
				// searches hash table and prints the given key's corresponding information
				hashTable.printKeyInfo(s);
			}
		}

	}

}
