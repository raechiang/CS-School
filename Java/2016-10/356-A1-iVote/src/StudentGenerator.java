/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 1: iVote Simulator
 * This console-based iVote simulator supports two question types: single choice
 * and multiple choices. The question, its type, and the answers all can be
 * configured by the user.
 */
import java.util.Random;

/**
 * This class generates a list of students and their responses to the configured
 * question randomly.
 */
public class StudentGenerator implements Constants
{
	private Student[] students = new Student[NUMBER_OF_STUDENTS];
	private Random rng = new Random();
	
	/**
	 * The constructor instantiates {@link #students} to null, generates
	 * the students with {@link #generateStudents()}, and then randomly
	 * creates their answer submissions. The choices parameter denotes 
	 * how many possible responses there are for the set question.
	 */
	public StudentGenerator(int choices)
	{
		for (int i = 0; i < students.length; ++i)
		{
			students[i] = null;
		}
		generateStudents();
		for (int i = 0; i < students.length; ++i)
		{
			students[i].setResponse(generateResponses(choices));
		}
	}
	
	/**
	 * Creates a new student by calling an ID generator, the {@link #generateID}
	 * method.
	 */
	private void generateStudents()
	{
		for (int i = 0; i < students.length; ++i)
		{
			students[i] = new Student(generateID());
		}
	}
	
	/**
	 * Creates a new ID and checks to make sure it is unique with the
	 * {@link #isIDUnique(String)} method.
	 */
	private String generateID()
	{
		char[] id = new char[9];
		for (int i = 0; i < id.length; ++i)
		{
			id[i] = (char) (rng.nextInt(103) + ASCII_CHAR_ADDER);
		}
		
		String stringID = "";
		for (int i = 0; i < id.length; ++i)
		{
			stringID += id[i];
		}
		
		if (isIDUnique(stringID))
		{
			return stringID;
		}
		else
		{
			return generateID();
		}
	}
	
	/**
	 * Checks if the newly-generated ID exists already with another student.
	 */
	private boolean isIDUnique(String id)
	{
		for (int i = 0; i < students.length; ++i)
		{
			if (students[i] != null)
			{
				if (id.equals(students[i].getStudentID()))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Generates a random response to the question.
	 */
	private String generateResponses(int choices)
	{
		char[] response = new char[rng.nextInt(MAX_NUMBER_OF_ANSWERS) + 1];
		for (int i = 0; i < response.length; ++i)
		{
			response[i] = (char) (rng.nextInt(choices) + ASCII_ALPHA_ADDER);
		}
		String r = "";
		for (int i = 0; i < response.length; ++i)
		{
			r += response[i];
		}
		return r;
	}
	
	/**
	 * Simple getter.
	 */
	public Student[] getStudents()
	{
		return students;
	}
}
