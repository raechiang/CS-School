/**
 * Rachel Chiang
 * 
 * This class is for the RoundRobin and RandomScheduler. It just has a
 * quantum to iterate the cycles.
 */
public abstract class QuantumScheduler extends Scheduler
{
	private int quantum;
	
	public QuantumScheduler(String type, int quantum)
	{
		super(type);
		this.quantum = quantum;
	}
	
	public int getQuantum()
	{
		return quantum;
	}
}