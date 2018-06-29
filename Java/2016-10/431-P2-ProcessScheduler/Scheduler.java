/**
 * Rachel Chiang
 * 
 * This class is at the top of the hierarchy containing the process scheduler
 * algorithms. It contains commonly used methods by all of them.
 */
public abstract class Scheduler
{
	/**
	 * The {@link #processTable} is essentially the queue containing the
	 * processes. It is instantiated in the constructor as null and can be set
	 * by {@link #setProcessTable(ProcessQueue)}.
	 */
	private ProcessQueue processTable;
	
	/**
	 * The {@link #cycles} field keeps track of how many cycles have elapsed.
	 * It is instantiated at 0 in the constructor {@link #Scheduler()} and can
	 * only be changed by adding a quantum to it using the method 
	 * {@link #increaseCycles(int)}. 
	 */
	private int cycles;
	
	/**
	 * {@link #turnaroundNumerator} keeps track of the total number of cycles
	 * needed for each process to complete execution. It can be modified by
	 * the {@link #adjustTurnaround()}. It is to be used later for calculating 
	 * the average turnaround time for all the processes to complete.
	 */
	private double turnaroundNumerator;
	
	/**
	 * {@link #processesCompleted} simply keeps track of how many processes have
	 * finished executing and it is incremented by one in 
	 * {@link #adjustTurnaround()}. It is used after the process table is empty
	 * to find the average turnaround time.
	 */
	private int processesCompleted;
	
	/**
	 * This string is just used for introducing the scheduling algorithm in the
	 * {@link #run()}.
	 */
	private String schedulerName;
	
	private String schedulerIntroduction;
	
	/**
	 * The constructor that instantiates the fields.
	 */
	public Scheduler(String intro)
	{
		processTable = null;
		cycles = 0;
		turnaroundNumerator = 0;
		processesCompleted = 0;
		schedulerIntroduction = intro;
	}
	
	/**
	 * Shared abstract method. I will explain them when they are implemented.
	 */
	public abstract void runProcess();
	
	/**
	 * This method introduces the scheduler and holds the execution loop.
	 */
	public void run()
	{
		System.out.println("Running " + schedulerIntroduction + ".");
		while (!(isDone()))
		{
			runProcess();
		}
		System.out.println();
	}
	
	/**
	 * This method sets the process table.
	 */
	public void setProcessTable(ProcessQueue pQ)
	{
		processTable = pQ;
	}
	
	/**
	 * This method adds the quantum to {@link #cycles}. It should be called when
	 * a process is being executed regardless of whether it finishes or not.
	 */
	public void increaseCycles(int quantum)
	{
		cycles += quantum;
	}
	
	/**
	 * This method is meant to be called after a process has finished executing.
	 * If it has finished, it will add the new turnaround time for the finished
	 * cycle to {@link #turnaroundNumerator} and increment
	 * {@link #processesCompleted} by one.
	 */
	public void adjustTurnaround()
	{
		turnaroundNumerator += cycles;
		++processesCompleted;
	}
	
	/**
	 * This method just checks to see if all of the processes have finished
	 * executing. It should only return true if the {@link #processTable} is 
	 * empty (since when all the processes finish executing, they are dequeued).
	 * If it is not done, it will return false. Additionally, the average 
	 * turnaround time is calculated here by dividing 
	 * {@link #turnaroundNumerator} by {@link #processesCompleted}. The result
	 * is printed out and indicates that {@link #run()} has completed.
	 */
	public boolean isDone()
	{
		if (processTable.getSize() == 0)
		{
			System.out.println("Average turnaround time: "
					+ turnaroundNumerator/processesCompleted);
			return true;
		}
		return false;
	}
	
	/**
	 * This method simply returns {@link #processTable}.
	 */
	public ProcessQueue getProcessTable()
	{
		return processTable;
	}
	/**
	 * This method simply returns {@link #cycles}.
	 */
	public int getCycles()
	{
		return cycles;
	}
	/**
	 * This method simply returns {@link #schedulerName}.
	 */
	public String getName()
	{
		return schedulerName;
	}
}
