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
 * This Question type only counts one answer per student.
 */
public class SingleAnswerQuestion extends Question
{
	public SingleAnswerQuestion(String question, ArrayList<String> possibleResponses)
	{
		super(question, possibleResponses);
	}
	
	/**
	 * Picks up only the last submission from a student.
	 */
	@Override
	public void receiveStudentAnswer(String answer)
	{
		char last = answer.charAt(answer.length() - 1);
		int index = ((int) last) - ASCII_ALPHA_ADDER;
		incrementResponseCount(index);
	}
}