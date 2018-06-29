/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 1: iVote Simulator
 * This console-based iVote simulator supports two question types: single choice
 * and multiple choices. The question, its type, and the answers all can be
 * configured by the user. I have also provided a little demo.
 * 
 * In this project, I attempt to follow the OOP principles: (1) Keep things as
 * private as possible; (2) Use appropriate Accessors, which corresponds with 
 * principle 1 anyway; (3) Prefer interfaces though it is case-dependent; (4)
 * User Interfaces over Switches; and (5) Consider Composition over Inheritance.
 * 
 * Note: I assumed that "multiple choice" meant that you could select more than
 * one answer, like you could in the ivote.io activities we did in class.
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This driver allows the user to select one of two modes: a fully-automated 
 * pre-hard-coded demo or user input mode. User Input mode is contained in this 
 * class.
 * In the user input mode, the user will set the question type (single answer 
 * or multiple answers), the question, and the possible choices. Once a question
 * type has been selected, the user cannot exit the program until the simulation
 * is complete. Furthermore, edits to the question or the answers cannot be made
 * once they have been submitted. After the question is all squared away, a list
 * of students is generated. Finally, the iVote accepts the submissions and 
 * displays the students' answer selections.
 */
public class SimulationDriver implements Constants
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		// Prompt for auto demo mode or user input mode
		System.out.println("Welcome!\n Please select a testing mode:"
				+ "\n  (1) Automated Demo Mode"
				+ "\n  (2) User Input Mode");
		int testingMode = sc.nextInt();
		System.out.println("\n"); // for the sake of clean-looking output
		
		if (testingMode == 1)
		{
			// demo mode
			Demo demo = new Demo();
			demo.run();
		}
		else if (testingMode == 2)
		{
			// user input mode
			String typePrompt = 
					"Please select a question type or \"exit\" to quit:"
					+ "\n A. Single Answer Multiple Choice"
					+ "\n B. Multiple Answers Multiple Choice";
			ArrayList<String> responses = new ArrayList<String>();
			sc.nextLine();
			
			// prompt for question type selection
			System.out.println(typePrompt);
			String input = sc.nextLine();
			while (!(input.equalsIgnoreCase("exit")))
			{
				if (input.equalsIgnoreCase("a") || input.equalsIgnoreCase("b"))
				{
					// user successfully selected a question type
					String questionType = input;
					System.out.println("Enter a question to ask:");
					String question = sc.nextLine();
					// Sets the letter choice. I wasn't entirely sure if this was 
					// supposed to be configurable as well, but I'd rather not 
					// leave it up to the user anyway
					int counter = 0; 
					// controls the loop
					boolean isDone = false;
					
					System.out.println("Please input possible responses, "
							+ "separated by new lines, or input only "
							+ "\"done\" or \"d\" when finished");
					while (!(isDone))
					{
						// creates the letter choice
						char letterChoice = (char)(ASCII_ALPHA_ADDER + counter);
						System.out.print(" " + letterChoice + "? ");
						input = sc.nextLine();
						if ((input.equalsIgnoreCase("done")) 
								|| (input.equalsIgnoreCase("d")))
						{
							isDone = true;
						}
						else
						{
							// adds the new possible response to an array
							responses.add(letterChoice + ". " + input);
						}
						++counter;
					}
					// a list of students is generated
					StudentGenerator studentsList = new StudentGenerator(--counter);
					ConsoleIVoteService iVote = new ConsoleIVoteService(questionType, 
							question, responses);
					// save and display the submissions
					iVote.receiveSubmissions(studentsList.getStudents());
					iVote.displayStatistics();
				}
				else
				{
					System.out.println("Bad input. Please try again.");
				}
				System.out.println(typePrompt);
				input = sc.nextLine();
			}
		}
		System.out.println("Thank you for using the I Vote Service. Goodbye.");
	}
}
