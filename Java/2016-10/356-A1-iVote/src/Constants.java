/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 1: iVote Simulator
 * This console-based iVote simulator supports two question types: single choice
 * and multiple choices. The question, its type, and the answers all can be
 * configured by the user.
 */

/**
 * I do not like hardcoded constants that, at first glance, may look arbitrarily
 * or randomly scattered throughout the code, so I made an interface full of
 * frequently used constants. I personally think that they make the code more
 * readable and easily understandable. For instance, if I'd kept 
 * "ASCII_ALPHA_ADDER + counter" as "65 + counter" in the SimulationDriver, it 
 * might take a moment to understand. Or, for instance, if I wanted to go back
 * and change the number of students, it would be more time-consuming to fix 
 * because I would have to search for a little "35". Here, I have quick access 
 * and descriptive variable names.
 */
public interface Constants
{
	// Important numbers, not to be touched
	public static final int ASCII_ALPHA_ADDER = 65;
	public static final int ASCII_CHAR_ADDER = 23;
	
	// Can be modified
	public static final int NUMBER_OF_STUDENTS = 35;
	public static final int MAX_NUMBER_OF_ANSWERS = 10;
}