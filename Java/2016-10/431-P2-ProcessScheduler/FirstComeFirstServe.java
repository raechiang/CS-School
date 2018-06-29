/**
 * This scheduler is basically the SimpleScheduler. It's just a FIFO scheduling
 * algorithm, and no changes need to be made to the queue for priorities, so
 * this class can remain pleasantly tiny. All it does is send to the top class
 * that it is to be introduced as a first-come, first-served scheduler.
 */
public class FirstComeFirstServe extends SimpleScheduler
{
	public FirstComeFirstServe(ProcessQueue pQ)
	{
		super("first-come, first-served scheduler");
		super.setProcessTable(pQ);
	}
}