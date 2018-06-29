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
 * This is a simple automated demo for those who don't want to spend the time
 * inputting their own question type, questions, and answers.
 */
public class Demo
{
	public void run()
	{
		singleAnswerDemo1();
		singleAnswerDemo2();
		multipleAnswersDemo1();
		multipleAnswersDemo2();
	}
	
	private void singleAnswerDemo1()
	{
		System.out.println("Single Answer Demo 1");
		ArrayList<String> responses = new ArrayList<String>();
		responses.add("A. True");
		responses.add("B. False");
		StudentGenerator studentsList = new StudentGenerator(2);
		ConsoleIVoteService iVote = new ConsoleIVoteService("a", "6/2(1+2)=9", responses);
		iVote.receiveSubmissions(studentsList.getStudents());
		iVote.displayStatistics();
	}
	
	private void singleAnswerDemo2()
	{
		System.out.println("Single Answer Demo 2");
		ArrayList<String>responses = new ArrayList<String>();
		responses.add("A. Red");
		responses.add("B. Orange");
		responses.add("C. Yellow");
		responses.add("D. Green");
		responses.add("E. Blue");
		responses.add("F. Indigo");
		responses.add("G. Violet");
		StudentGenerator studentsList = new StudentGenerator(7);
		ConsoleIVoteService iVote = new ConsoleIVoteService("a", "What is your favorite color?", 
				responses);
		iVote.receiveSubmissions(studentsList.getStudents());
		iVote.displayStatistics();
	}
	
	public void multipleAnswersDemo1()
	{
		System.out.println("Multiple Answers Demo 1");
		ArrayList<String>responses = new ArrayList<String>();
		responses.add("A. Bulbasaur");
		responses.add("B. Charmander");
		responses.add("C. Squirtle");
		responses.add("D. Pikachu");
		StudentGenerator studentsList = new StudentGenerator(4);
		ConsoleIVoteService iVote = new ConsoleIVoteService("b",
				"Which of the following starter Pokemon do you like?", responses);
		iVote.receiveSubmissions(studentsList.getStudents());
		iVote.displayStatistics();
	}
	
	public void multipleAnswersDemo2()
	{
		System.out.println("Multiple Answers Demo 2");
		ArrayList<String>responses = new ArrayList<String>();
		responses.add("A. Facebook");
		responses.add("B. Whatsapp");
		responses.add("C. QQ");
		responses.add("D. Tumblr");
		responses.add("E. Instagram");
		responses.add("F. Twitter");
		responses.add("G. Snapchat");
		StudentGenerator studentsList = new StudentGenerator(7);
		ConsoleIVoteService iVote = new ConsoleIVoteService("b", 
				"Select all social networks that you use daily.", responses);
		iVote.receiveSubmissions(studentsList.getStudents());
		iVote.displayStatistics();
	}
}
