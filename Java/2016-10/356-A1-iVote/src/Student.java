/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 1: iVote Simulator
 * This console-based iVote simulator supports two question types: single choice
 * and multiple choices. The question, its type, and the answers all can be
 * configured by the user.
 */

/**
 * Holds information about each participant.
 */
public class Student
{
	private final String studentID;
	private String response;
	
	public Student(String id)
	{
		studentID = id;
	}
	
	public void setResponse(String response)
	{
		this.response = response;
	}
	
	public String getResponse()
	{
		return response;
	}
	public String getStudentID()
	{
		return studentID;
	}
}
