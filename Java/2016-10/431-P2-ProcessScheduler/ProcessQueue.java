/**
 * Rachel Chiang
 * 
 * This class represents a queue of ProcessNodes. You can add or remove from the
 * queue, and you can also create a copy of the queue.
 */
public class ProcessQueue
{
	/**
	 * This fields points to the first ProcessNode of the queue. It is set to
	 * null in the constructor and can only be changed the first time a process
	 * is added or when a process is dequeued/removed. There are two methods
	 * for adding, {@link #add(ProcessNode)} and {@link #addFromString(String)}.
	 */
	private ProcessNode first;
	
	/**
	 * {@link #first} is the only field to instantiate and it is set to null.
	 */
	public ProcessQueue()
	{
		first = null;
	}
	
	/**
	 * This method adds a new process (which is passed as the argument) to the
	 * ProcessQueue. It uses {@link #getLast()} to find the last node in the
	 * queue.
	 * I considered making last itself a field, but it made using the
	 * queue in certain scheduling algorithms slightly more complicated
	 * because I ended up having to re-locate the last anyway if I ended up 
	 * not using the usual FIFO order of a queue. I decided to just keep the
	 * last access consistent and made a getter instead.
	 * I suppose this turned out to be more of a List than a Queue, but it
	 * still functions as a Queue for most of the scheduling algorithms and it
	 * would be a pain to rename it.
	 */
	public void add(ProcessNode newProcess)
	{
		if (first == null)
		{
			// queue is empty, so first is a new process
			first = newProcess;
		}
		else
		{
			// non-empty queue, add to the end
			getLast().setNext(newProcess);
		}
	}
	
	/**
	 * This method takes a string argument containing information about the new
	 * process. This method is only used at one point, which is when the file is
	 * loaded and lines of information are sent individually to this class so a
	 * new node can be created after parsing the information from the String. It
	 * calls {@link #add(ProcessNode)} to finish the job.
	 */
	public void addFromString(String processInfo)
	{
		int commaIndex = processInfo.indexOf(',');
		int startIndex = 0;
		String piece = processInfo.substring(startIndex, commaIndex);
		
		int pid = Integer.parseInt(piece);
		
		piece = processInfo.substring(commaIndex + 1);
		
		int cycles = Integer.parseInt(piece);
		
		ProcessNode newProcess = new ProcessNode(pid, cycles);
		
		add(newProcess);
	}
	
	/**
	 * This method simply removes the first ProcessNode from the queue.
	 */
	public void removeFirst()
	{
		if (first != null)
		{
			ProcessNode current = first.getNext();
			first.setNext(null);
			first = current;
		}
	}
	
	/**
	 * This method makes a copy of the queue that called it and returns the 
	 * copy. It was used to recreate the queues from the file content for the
	 * different scheduling algorithm tests.
	 */
	public ProcessQueue getCopyOf()
	{
		ProcessQueue copy = new ProcessQueue();
		ProcessNode current = first;
		
		while (current != null)
		{
			ProcessNode newNode = new ProcessNode(current.getProcessID(), current.getRemainingCycles());
			copy.add(newNode);
			current = current.getNext();
		}
		return copy;
	}
	
	/**
	 * A getter for the {@link #first} field.
	 */
	public ProcessNode getFirst()
	{
		return first;
	}
	
	/**
	 * A getter that sifts through the queue and returns the last process.
	 */
	public ProcessNode getLast()
	{
		ProcessNode current = first;
		while (current.getNext() != null)
		{
			current = current.getNext();
		}
		
		return current;
	}
	
	/**
	 * A getter that counts the number of processes in the queue.
	 */
	public int getSize()
	{
		int size = 0;
		ProcessNode current = first;
		while (current != null)
		{
			++size;
			current = current.getNext();
		}
		return size;
	}
}
