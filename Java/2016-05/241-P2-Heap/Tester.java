/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 2: Heap
 */
import java.util.Random;

/**
 * This class simply organizes the two testing options and their two methods.
 */
public class Tester {

	public Tester() {
	}
	
	/**
	 * This method is for option one, the 100 randomly generated elements.
	 * It uses {@link #randNums()} for support in creating the random
	 * integers [1, 100]. 20 sets of these 100 integers are created and placed
	 * into a heap by a series of insertions and by the optimal method.
	 * The swap counts are kept and at the end, the averages of the two methods
	 * are calculated.
	 * I split this one into {@link #optionOneSequential(int[])} and 
	 * {@link #optionOneOptimal(int[])} so it would be neater.
	 */
	public void optionOne()
	{
		int seqTotalSwaps = 0;
		int optTotalSwaps = 0;
		for (int i = 0; i < 20; ++i)
		{
			// 20 sets of option one
			int[] elements = randNums();
			int seqSwaps = optionOneSequential(elements);
			int optSwaps = optionOneOptimal(elements);
			seqTotalSwaps += seqSwaps;
			optTotalSwaps += optSwaps;
		}
		System.out.println();
		
		System.out.println("Total Sequential Method Swaps: " + seqTotalSwaps
						+  "\nTotal Optimal Method Swaps:    " + optTotalSwaps);
		System.out.println();
		System.out.printf("Average swaps for series of insertions: %4d", 
				(seqTotalSwaps/20));
		System.out.printf("\nAverage swaps for optimal method:       %4d", 
				(optTotalSwaps/20));
	}
	
	private int optionOneSequential(int[] elements)
	{
		Heap rngSeqHeap = new Heap(elements.length);
		
		int seqSwaps = 0;
		// build with sequential add
		for (int i = 0; i < elements.length; ++i)
		{
			seqSwaps += rngSeqHeap.addElement(elements[i]);
		}
		
		return seqSwaps;
	}
	
	private int optionOneOptimal(int[] elements)
	{
		// optimal build
		Heap rngOptHeap = new Heap(elements.length);
		int optSwaps = rngOptHeap.build(elements);
		
		return optSwaps;
	}
	
	/**
	 * This is for option two, the fixed positive integers 1-100. Again,
	 * they are added to a heap with the series of insertions method and
	 * the optimal method. The first ten elements are printed as well as
	 * their swap counts. Then, the roots are removed 10 times from each
	 * and the first ten are printed again.  
	 */
	public void optionTwo()
	{
		Heap fixedSeqHeap = new Heap(100);
		int seqSwaps = 0;
		for (int i = 1; i < 101; ++i)
		{
			seqSwaps += fixedSeqHeap.addElement(i);
		}
		
		System.out.print("Heap built using series of insertions: ");
		fixedSeqHeap.printFirstTen();
		System.out.println("...");
		System.out.println(" Number of Swaps: " + seqSwaps);
		for (int i = 0; i < 10; ++i)
		{
			fixedSeqHeap.removeRoot();
		}
		System.out.print(" Heap after 10 removals: ");
		fixedSeqHeap.printFirstTen();
		System.out.println("...");
		
		int[] elements = new int[100];
		for (int i = 0; i < elements.length; ++i)
		{
			elements[i] = i + 1;
		}
		Heap fixedOptHeap = new Heap(elements.length);
		int optSwaps = fixedOptHeap.build(elements);
		System.out.print("Heap built using optimal method: ");
		fixedOptHeap.printFirstTen();
		System.out.println("...");
		System.out.println(" Number of Swaps: " + optSwaps);
		for (int i = 0; i < 10; ++i)
		{
			fixedOptHeap.removeRoot();
		}
		System.out.print(" Heap after 10 removals: ");
		fixedOptHeap.printFirstTen();
		System.out.println("...");
	}
	
	private int[] randNums()
	{
		Random rng = new Random();
		int[] elements = new int[100];
		for (int i = 0; i < elements.length; ++i)
		{
			boolean isDup = false;
			int newElement = rng.nextInt(101); // what is "proper range"?
			
			for (int j = 0; j < i; ++j)
			{
				if (elements[j] == newElement)
				{
					isDup = true;
				}
			}
			
			if (isDup || newElement == 0) // 0 is technically not pos and not neg
			{
				--i;
			}
			else
			{
				elements[i] = newElement;
			}
		}
		return elements;
	}

}
