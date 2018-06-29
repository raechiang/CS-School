/**
 * Rachel Chiang
 * CS 431-02
 * Exercise 3: Semaphore-Based Blocking Queue
 * 
 * In this project, we use semaphores to solve a race condition in the producer-
 * consumer problem.
 */
import java.util.concurrent.Semaphore;

/**
 * This class implements a generic Blocking Queue with the very basic methods,
 * {@link #dequeue()} and {@link #enqueue(Object)} and their two supporting 
 * methods, {@link #addLast(Object)} and {@link #removeFirst()}. The queue 
 * itself is comprised of simple Nodes linked together with only the 
 * {@link #head} giving access to the queue.
 */
public final class BlockingQueue<T>
{
	/**
	 * This gives access to the queue, which are simply Nodes linked together.
	 */
	private Node<T> head;
	
	/**
	 * These are the three semaphores.
	 * {@link #mutex} will be initialized with 1 and controls access to the 
	 * shared resource. 1 means it is unlocked and 0 is locked. If a thread 
	 * needs to access its critical region, mutex will lock and will unlock if 
	 * the thread completes its critical region. If mutex is locked, whatever 
	 * that is trying to access the critical region will be blocked.
	 * {@link #empty} will be initialized with the maximum size of the queue. It
	 * indicates how many spots in the buffer are empty.
	 * {@link #full} will be initialized with 0, which indicates how many spots
	 * are currently being used.
	 */
	private Semaphore mutex;
	private Semaphore empty;
	private Semaphore full;
	
	/**
	 * Constructor initializing the fields.
	 */
	public BlockingQueue(int size)
	{
		head = null;
		mutex = new Semaphore(1);
		empty = new Semaphore(size);
		full = new Semaphore(0);
	}

	/**
	 * One of the two required methods for the project. This uses semaphores to
	 * control access to the queue. It uses {@link #addLast(Object)} to help add
	 * some new T to the queue.
	 */
	public void enqueue(T t) throws InterruptedException
	{
		
		empty.acquire();
		mutex.acquire();
		// add item
		addLast(t);
		mutex.release();
		full.release();
	}
	
	/**
	 * One of the two required methods for the project. This uses semaphores to
	 * control access to the queue. It uses {@link #removeFirst()} to help 
	 * remove the first element/node from the queue.
	 */
	public T dequeue() throws InterruptedException
	{
		full.acquire();
		mutex.acquire();
		// remove item
		T first = removeFirst();
		mutex.release();
		empty.release();
		return first;
	}
	
	/**
	 * This method simply supports the {@link #enqueue(Object)} method, adding t
	 * to the end of the queue as a new Node.
	 */
	private synchronized void addLast(T t)
	{
		if (head == null)
		{
			head = new Node<T>(t);
		}
		else
		{
			Node<T> cur = head;
			while (cur.next != null)
			{
				cur = cur.next;
			}
			cur.next = new Node<T>(t);
		}
	}
	
	/**
	 * This method simply supports the {@link #dequeue()} method, removing the
	 * {@link #head} of the queue and returning its T element, and lastly, 
	 * replacing the head as the next Node in the queue. 
	 */
	private synchronized T removeFirst()
	{
		if (head == null)
		{
			System.out.println("Empty queue.");
			return null;
		}
		
		T first = head.element;
		head = head.next;
		return first;
	}
	
	/**
	 * This class makes up the queue.
	 */
	private static class Node<T>
	{
		T element;
		Node<T> next;
		Node(T t)
		{
			element = t;
			next = null;
		}
	}
	
	/**
	 * This is the main method which contains the test provided for the exercise.
	 */
	public static void main(String[] args) throws Exception
	{
		BlockingQueue<Integer> queue = new BlockingQueue<>(100);
		Runnable r = () ->
		{
			for (int i = 0; i < 200; i++)
			{
				try {
					int n = queue.dequeue();
					System.out.println(n + " Removed");
					Thread.sleep(500);
				} catch (Exception e) {}
			}
		};
		Thread t = new Thread(r);
		t.start();
		for (int i = 0; i < 200; i++)
		{
			System.out.println("Adding " + i);
			queue.enqueue(i);
		}
	}
}
