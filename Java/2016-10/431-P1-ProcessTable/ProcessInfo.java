import java.util.ArrayList;

/**
 * This class contains all the information for a process, including its blocks.
 */
public class ProcessInfo
{
	private final int processID;
	private int programCounter;
	private int status; // 0 running, 1 blocked, 2 ready
	private final int totalCycles;
	private ArrayList<BlockStatus> blocks = new ArrayList<BlockStatus>();
	
	/**
	 * These are sort of for the sake of making the rest of the methods more
	 * readable.
	 */
	private static final int RUNNING = 0;
	private static final int BLOCKED = 1;
	private static final int READY = 2;
	private static final int DEFAULT_ITERATOR = 100;
	
	public ProcessInfo(String pInfo)
	{
		status = READY;
		
		char[] info = pInfo.toCharArray();
		int startIndex = 0;
		String piece = "";
		
		int endIndex = indexFinder(info, startIndex);
		piece = pInfo.substring(startIndex, endIndex);
		processID = Integer.parseInt(piece);
		startIndex = endIndex + 1;
		
		endIndex = indexFinder(info, startIndex);
		String remaining = pInfo.substring(startIndex);
		piece = pInfo.substring(startIndex, endIndex);
		programCounter = Integer.parseInt(piece);
		startIndex = endIndex + 1;
		
		endIndex = indexFinder(info, startIndex);
		piece = remaining.substring(endIndex);
		piece = pInfo.substring(startIndex, endIndex);
		totalCycles = Integer.parseInt(piece);
		
		
		String blockString = pInfo.substring(endIndex + 1);
		
		if (blockString.contains(";"))
		{
			char[] blockInfo = blockString.toCharArray();
			int blockCounter = 0;
			for (int i = 0; i < blockInfo.length; ++i)
			{
				if (blockInfo[i] == ';')
				{
					++blockCounter;
				}
			}
			startIndex = 0;
			int blockStart = 0;
			int blockDuration = 0;
			endIndex = blockString.indexOf(';');
			
			while (blockCounter > 0)
			{
				piece = blockString.substring(startIndex, endIndex);
				blockString = blockString.substring(endIndex + 1);
				blockStart = Integer.parseInt(piece);
				
				if (blockString.contains(":"))
				{
					endIndex = blockString.indexOf(':');
				}
				else
				{
					endIndex = blockString.length();
				}
				piece = blockString.substring(startIndex, endIndex);
				
				if (endIndex < blockString.length())
				{
					blockString = blockString.substring(endIndex + 1);
				}
				blockDuration = Integer.parseInt(piece);
				
				blocks.add(new BlockStatus(blockStart, blockDuration));
				
				--blockCounter;
			}
		}
	}

	/**
	 * I didn't realize there was an "indexOf" until later.
	 */
	private int indexFinder(char[] info, int index)
	{
		if (index < info.length)
		{
			while (info[index] != ',')
			{
				++index;
			}
			return index;
		}
		else
		{
			return -1;
		}
	}
	
	/**
	 * The status of a process can only change in certain ways depending on its
	 * current status. A running process can only be changed to blocked or 
	 * ready; a blocked class can only move to ready; and a ready only moves
	 * to running.
	 */
	public void updateStatus()
	{
		if (status == RUNNING)
		{
			updatePC(DEFAULT_ITERATOR);
			if (checkBlock() == -1)
			{
				status = READY;
				System.out.println(processID + " ready");
			}
			else
			{
				status = BLOCKED;
				System.out.println(processID + " blocked");
			}
		}
		else if (status == BLOCKED)
		{
			int blockIndex = checkBlock();
			if (blocks.get(blockIndex).getRemaining() == 0)
			{
				status = READY;
				System.out.println(processID + " ready");
				blocks.remove(blockIndex);
			}
		}
		else if (status == READY)
		{
			status = RUNNING;
			System.out.println(processID + " running");
		}
	}
	
	/**
	 * Reduces the program counter.
	 */
	public void updatePC(int iterator)
	{
		System.out.print("pc " + programCounter + " -> ");
		
		if (checkBlock() == -1)
		{
			// no block, pc updates normally
			int newPC = programCounter + iterator;
			if (newPC >= totalCycles)
			{
				programCounter = totalCycles;
			}
			else
			{
				programCounter = newPC;
			}
		}
		
		System.out.println(programCounter);
	}
	
	/**
	 * This checks to see if the program has a block at the current pc.
	 */
	public int checkBlock()
	{
		for (int i = 0; i < blocks.size(); ++i)
		{
			if (blocks.get(i).getStart() == programCounter)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * This updates the block if there is a block.
	 */
	public void updateBlock()
	{
		int blockIndex = checkBlock();
		if (blockIndex != -1)
		{
			System.out.print(processID + " block ");
			blocks.get(blockIndex).reduceRemaining(DEFAULT_ITERATOR);
			updateStatus();
		}
	}
	
	/**
	 * This method returns if a process is finished so it can be removed from the
	 * process table.
	 */
	public boolean isFinished()
	{
		if (programCounter == totalCycles)
		{
			System.out.println(processID + " done");
			return true;
		}
		else
		{
			return false;
		}
	}
}
