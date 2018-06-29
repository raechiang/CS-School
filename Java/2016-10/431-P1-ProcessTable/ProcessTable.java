import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Rachel Chiang
 * CS 431-02
 * Project 1: Process Table
 * 
 * This project simulates a process table containing standard table entries 
 * (process ID, program counter, and status) and some information for the sake
 * of the simulation (total cycles a program executes and information for a
 * process' blocks).
 * 
 * My output did not turn out to be exactly the same as the example output given
 * in the project description. Hopefully that is not a big issue because it
 * seems to work in the same way though the order of the outputs are in a 
 * different order. It also works with cycle values not evenly divisible by 100,
 * such as a block of 150 duration or a pc that finishes at 250 (which was an 
 * issue I had in a previous version I had written) and in the case that the PC 
 * of a process starts at a different value other than 0 (as it should).
 */
class ProcessTable
{
	public static void main(String[] args) throws IOException
	{
		// Input file read and process table entries initialized
		String fileName = args[0];
		Scanner inputFile = new Scanner(new File(fileName));
		ArrayList<ProcessInfo> processTable = new ArrayList<ProcessInfo>();
		
		while (inputFile.hasNext())
		{
			processTable.add(new ProcessInfo(inputFile.nextLine()));
		}
		inputFile.close();
		
		// Works through each process and removes them when they're finished
		while (!(processTable.isEmpty()))
		{
			for (int idx = 0; idx < processTable.size(); ++idx)
			{
				// swap in: print new status (pid running)
				processTable.get(idx).updateStatus();
				// swap out: print new status (pid ready)
				processTable.get(idx).updateStatus();
				// update block
				processTable.get(idx).updateBlock();
				
				if (processTable.get(idx).isFinished())
				{
					processTable.remove(idx);
					--idx;
				}
				
			}
			
		}
		
	}

}
