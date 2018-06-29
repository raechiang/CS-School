/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 1: iVote Simulator
 * This console-based iVote simulator supports two question types: single choice
 * and multiple choices. The question, its type, and the answers all can be
 * configured by the user.
 */
import java.util.ArrayList;

/**
 * This class communicates between the SimulationDriver and the Question classes.
 */
public class ConsoleIVoteService implements IVoteService, Constants
{
	private Question question;
	private Student[] students;
	
	// Constructor
	public ConsoleIVoteService(String questionType, String question, ArrayList<String> responses)
	{
		if (questionType.equalsIgnoreCase("a"))
		{
			this.question = new SingleAnswerQuestion(question, responses);
		}
		else if (questionType.equalsIgnoreCase("b"))
		{
			this.question = new MultipleAnswersQuestion(question, responses);
		}
	}
	
	/**
	 * This method simply receives a list of students and passes the students'
	 * responses to {@link #question}.
	 */
	@Override
	public void receiveSubmissions(Student[] students)
	{
		this.students = students;
		for (int i = 0; i < this.students.length; ++i)
		{
			question.receiveStudentAnswer(this.students[i].getResponse());
		}
	}
	
	/**
	 * This method prints out how many selections of each response were made. 
	 */
	@Override
	public void displayStatistics()
	{
		System.out.println("QUESTION: " + question.getQuestion());
		for (int i = 0; i < question.getPossibleResponses().size(); ++i)
		{
			System.out.println("  " + question.getPossibleResponses().get(i));
		}
		System.out.println(" Student answers are as follows: ");
		for (int i = 0; i < question.getStudentSubmissions().length; ++i)
		{
			System.out.println("  " + (char)(i + ASCII_ALPHA_ADDER) + " : " + question.getStudentSubmissions()[i]);
		}
	}

}
