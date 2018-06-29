/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 3: Dijkstra's Algorithm
 */
import java.util.Scanner;

/**
 * This class handles communication with the user in both output and input.
 * It gives directions to the user in {@link #welcome()} and 
 * {@link #menuDisplay()}. A command will be requested for through 
 * {@link #requestCmd()} and the response given will prompt a different
 * {@link #response(char)}. Depending on the command, the user will
 * be prompted for a city code {@link #requestCC()} or a distance
 * {@link #requestDub()}. If all inputs are correct, one of the following
 * will be displayed: {@link #printCityInfo(String)} for Q,
 * {@link #distReport(String, String, String, int)} for D,
 * {@link #printInsertion(City, City, double)} for I, or
 * {@link #removalSuccessful(String, String)} for R. However, if something
 * fails, {@link #doesNotExist(String)}, {@link #badDub()}, {@link #badCitiesIn()},
 * or {@link #unsucAdd(String, String)} will be printed.
 */
class UserInterface {
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * This method simply welcomes the user and provides instructions. It calls on
	 * the {@link #menuDisplay()} method to show the possible commands.
	 */
	public void welcome()
	{
		System.out.println("Hello! Welcome!");
		System.out.println("\nInstructions:\n Available Commands:");
		menuDisplay();
		System.out.println(" Depending on the command (not case-sensitive), "
				+ "you will be asked to input one\n or two city codes and you may be "
				+ "asked to include a value for the distance. In\n any case, city codes"
				+ " must be provided as two capitalized letters (ex, \"AB\").");
	}
	
	/**
	 * This method just gives the possible commands and their functions.
	 * More specifically,
	 *  'Q' will allow the user to find out the data associated with one city given 
	 *    its city code. Within this class, assuming all goes well, selecting Q 
	 *    should end with {@link #printCityInfo(String)} before re-entering the 
	 *    command loop.
	 *  'D' will find the distance between two cities given their city codes. Within
	 *    this class, assuming all went well, this should end with 
	 *    {@link #distReport(String, String, String, int)}.
	 *  'I' will allow the user to insert a new road between two cities given two 
	 *    city codes. This should end with {@link #printInsertion(City, City, double)}.
	 *  'R' will allow the user to remove an existing road given two city codes. This
	 *    should end with {@link #removalSuccessful(String, String)}.
	 *  'H' will show the help menu, {@link #menuDisplay()}.
	 *  'E' terminates the command loop, thereby terminating the program by reaching
	 *    the end of main.
	 */
	public void menuDisplay()
	{
		System.out.println("  Q - Query the city information by entering the city code");
		System.out.println("  D - Find the minimum distance between two cities");
		System.out.println("  I - Insert a road by entering two city codes and distance");
		System.out.println("  R - Remove an existing road by entering two city codes");
		System.out.println("  H - Display this message");
		System.out.println("  E - Exit");
	}
	
	/**
	 * This method is used for the individual character commands. See 
	 * {@link #menuDisplay()} for more information about the commands.
	 * @return - The user's input as the first character of the given string.
	 */
	public char requestCmd()
	{
		System.out.print("Please input a command: ");
		String input = sc.next();
		sc.nextLine();
		return input.charAt(0);
	}
	
	/**
	 * This method prints a response corresponding to the command given from
	 * {@link #requestCmd()}. Following this display, the program may request
	 * the user for one or two city codes with {@link #requestCC()} or a
	 * distance {@link #requestDub()}.
	 * @param input The character from {@link #requestCC()}.
	 * @return This is used to terminate the loop!
	 */
	public boolean response(char input)
	{
		switch(input)
		{
			case 'Q': case 'q':
				// Query
				System.out.print("Please input a city code: ");
				break;
			case 'D': case 'd':
			case 'R': case 'r':
				// Distance or Remove (which both require two city codes)
				System.out.print("Please input two city codes: ");
				break;
			case 'I': case 'i':
				// Insert
				System.out.print("Please input two city codes"
						+ " and the distance along this path: ");
				break;
			case 'H': case 'h':
				// Help!
				menuDisplay();
				break;
			case 'E': case 'e':
				// Exit
				System.out.println("Thank you for using this program. Bye.");
				return false;
			default:
				// Bad input ):
				System.out.println("Invalid input. Enter \'H\' for input options.\n"
						+ "Please try again.");
				sc.nextLine();
				break;
		}
		return true;
	}
	
	/**
	 * This method grabs the user's response for a city code request.
	 * @return The city code.
	 */
	public String requestCC()
	{
		String cc = sc.next();
		return cc;
	}
	
	/**
	 * This method grabs the user's response for a distance request.
	 * @return The distance.
	 */
	public double requestDub()
	{
		double reply = -1;
		if (sc.hasNextDouble())
		{
			reply = sc.nextDouble();
		}
		return reply;
	}
	
	/**
	 * This method prints the city info after a successful Q command.
	 * @param c This is just a string of a city's data.
	 */
	public void printCityInfo(String c)
	{
		System.out.println(c);
	}
	
	/**
	 * This method just prints the shortest route from two cities and
	 * the distance of this path.
	 * @param cityName1 - The "from" city
	 * @param cityName2 - The "to" city
	 * @param minDistStr - The string of cities passed through
	 * @param d - the distance
	 */
	public void distReport(String cityName1, String cityName2,
			String minDistStr, double d)
	{
		System.out.println("There exists a minimum distance between " 
				+ cityName1 + " and " + cityName2 + " through the route:\n"
				+ cityName1 + ", " + minDistStr + "with a distance of "
				+ d + ".");
	}
	
	/**
	 * This method is called when an insertion of an edge was successful.
	 * It reports the new edge's information.
	 * @param fromCity - The city that the edge starts from.
	 * @param toCity - The city that the edge goes to.
	 * @param dist - The weight of the edge
	 */
	public void printInsertion(City fromCity, City toCity, double dist)
	{
		if (dist > -1)
		{
			System.out.println("Successfully inserted path between " 
					+ fromCity.getCityName() + " to " + toCity.getCityName() 
					+ " with a distance of " + dist + ".");
		}
		else
		{
			System.out.println("Insertion was unsuccessful.");
		}
	}
	
	/**
	 * The edge between the two cities was removed, so this method reports
	 * this to the user.
	 * @param fromCity - The starting city of the edge.
	 * @param toCity - The ending city of the edge.
	 */
	public void removalSuccessful(String fromCity, String toCity)
	{
		System.out.println("The road between " + fromCity + " to "
				+ toCity + " has been successfully removed.");
	}
	
	/**
	 * A common issue is when something--either the path or city--does not exist.
	 * @param problem - Depending on the problem, the program just reuses this
	 * 			same method with a different string to display.
	 */
	public void doesNotExist(String problem)
	{
		System.out.println(problem + " does not exist. Please try again.");
		sc.nextLine();
	}
	
	/**
	 * This user put in a bad double.
	 */
	public void badDub()
	{
		System.out.println("Please give a distance greater than 0.");
		sc.nextLine();
	}
	
	/**
	 * The user put in two of the same cities.
	 */
	public void badCitiesIn()
	{
		System.out.println("Please do not give the same two cities.");
		sc.nextLine();
	}
	
	/**
	 * An edge could not be added with the 'I' command because it
	 * is already there.
	 * @param fromCity - the city from which the edge originates
	 * @param toCity - the city that the edge goes to
	 */
	public void unsucAdd(String fromCity, String toCity)
	{
		System.out.println("The road from " + fromCity + " to " 
				+ toCity + " already exists.");
	}
}
