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
 * This abstract class represents the Question, including the question itself,
 * the possible choices, and the students' choices. It is to be the superclass
 * of SingleAnswerQuestion and MultipleAnswersQuestion.
 */
public abstract class Question implements Constants
{
	private String question; // the question itself
	private ArrayList<String> possibleResponses; // possible choices
	private int[] studentSubmissions; // students' choices
	
	/**
	 * The constructor instantiates the {@link #question} and 
	 * {@link #possibleResponses} according to information passed by the
	 * IVoteService (and previously, the SimulationDriver).
	 * {@link #studentSubmissions} is filled with 0's, as no student's 
	 * submission has been accounted for yet.
	 */
	public Question(String question, ArrayList<String> possibleResponses)
	{
		this.question = question;
		this.possibleResponses = possibleResponses;
		this.studentSubmissions = new int[possibleResponses.size()];
		for (int i = 0; i < this.studentSubmissions.length; ++i)
		{
			studentSubmissions[i] = 0;
		}
	}
	
	/**
	 * Accounts for the student's answer.
	 */
	public abstract void receiveStudentAnswer(String answer);
	
	/**
	 * Increments the counter for a given letter answer by 1.
	 */
	public void incrementResponseCount(int index)
	{
		++studentSubmissions[index];
	}
	
	/**
	 * Getters
	 */
	public String getQuestion()
	{
		return question;
	}
	public ArrayList<String> getPossibleResponses()
	{
		return possibleResponses;
	}
	public int[] getStudentSubmissions()
	{
		return studentSubmissions;
	}
}
