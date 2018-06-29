/**
 * Rachel Chiang
 * CS 356-01
 * Assignment 1: iVote Simulator
 * This console-based iVote simulator supports two question types: single choice
 * and multiple choices. The question, its type, and the answers all can be
 * configured by the user.
 */

/**
 * Simple interface for the IVoteService.
 */
public interface IVoteService
{
	public void receiveSubmissions(Student[] students);
	public void displayStatistics();
}
