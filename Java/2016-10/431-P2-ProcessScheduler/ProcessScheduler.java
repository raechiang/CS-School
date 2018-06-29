/**
 * Rachel Chiang
 * CS 431-02
 * Project 2: Process Scheduler
 * 
 * This project involves simulating some process scheduling algorithms on lists 
 * of processes which are provided in a file through the command line. There is
 * the first-come, first-serve algorithm, which (as its name implies) simply 
 * runs each process in the order that they are found in the file. The shortest
 * first scheduler executes the processes in order of those taking the least
 * cycles to those taking the most amount of cycles. If processes are the same
 * length, they are executed first-come, first-serve. The round robin scheduler
 * runs through the processes in order of submission, giving each a fair amount
 * of time (called the quantum). If a process is not finished, it gets re-added
 * to the queue and the cycle repeats. As the name suggests, the random
 * scheduler randomly selects a process to run with a specified quantum. Similar
 * to the round robin, the process will be thrown back into the drawing hat to 
 * be randomly selected again. However, it is not entirely random, as certain
 * processes have higher chances of winning than others.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProcessScheduler
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String fileName = args[0];
		Scanner inputFile = new Scanner(new File(fileName));
		ProcessQueue processTable = new ProcessQueue();
		
		while (inputFile.hasNext())
		{
			processTable.addFromString(inputFile.nextLine());
		}
		
		// creation of the schedulers
		Scheduler fcfs = new FirstComeFirstServe(processTable.getCopyOf());
		Scheduler sf = new ShortestFirst(processTable.getCopyOf());
		Scheduler rr50 = new RoundRobin(processTable.getCopyOf(), 50);
		Scheduler rr100 = new RoundRobin(processTable.getCopyOf(), 100);
		Scheduler r50 = new RandomScheduler(processTable.getCopyOf(), 50);
		
		// they do their thing
		fcfs.run();
		sf.run();
		rr50.run();
		rr100.run();
		r50.run();
		
		inputFile.close();
	}

}
