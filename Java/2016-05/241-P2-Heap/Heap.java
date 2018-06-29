/**
 * Rachel Chiang, CS 241-02
 * 
 * Project 2: Heap
 */

/**
 * This class is the Heap class, which contains all the functions needed
 * for testing. There are two fields, {@link #heap} and {@link #last}. The 
 * primary functions are {@link #build(int[])}, {@link #addElement(int)},
 * {@link #removeRoot()}, and {@link #printFirstTen()}. These are supported
 * by the following: {@link #reheapifyUp(int)}, {@link #reheapifyDown(int)},
 * {@link #findParent(int)}, {@link #findLeftChild(int)}, and 
 * {@link #findRightChild(int)}.
 */
public class Heap {
	
	/**
	 * These are the fields, {@link #heap} and {@link #last}. I considered
	 * not including last, and I wonder if perhaps it would have made certain
	 * things simpler, but it was nice not having to re-find the first
	 * available index in the array.
	 */
	private int[] heap;
	private int last;

	/**
	 * This is the constructor for the heap. All values are initialized to
	 * -1 because the heap is supposed to only contain positive integers,
	 * and last is initialized to 0, since of course, it is the first 
	 * available position.
	 * @param size - used to initialize the size of the heap array.
	 */
	public Heap(int size) {
		// TODO Auto-generated constructor stub
		heap = new int[size];
		for (int i = 0; i < size; ++i)
		{
			heap[i] = -1;
		}
		last = 0;
	}
	
	/**
	 * This method builds the heap with the optimal method. The elements
	 * are added arbitrarily and then the heap property is restored with
	 * {@link #reheapifyDown(int)}.
	 * @param elements - an array of elements to add to the heap
	 * @return swapCounter - the number of swaps committed during
	 * 			reheapification
	 */
	public int build(int elements[])
	{
		for (int i = 0; i < elements.length; ++i)
		{
			heap[i] = elements[i];
		}
		last = elements.length - 1;
		
		int swapCounter = 0;
		for (int i = last; i >= 0; --i)
		{
			swapCounter += reheapifyDown(i);
		}
		return swapCounter;
	}
	
	/**
	 * This method adds an individual element, which is meant to be used
	 * for the series of insertions test. It places the new element at the 
	 * first available position, given by {@link #last}. Then it simply calls
	 * {@link #reheapifyUp(int)} to restore the heap property after every
	 * insertion.
	 * @param element - the new element to add
	 * @return It returns the value returned by {@link #reheapifyUp(int)}, which
	 * 			is simply a swap count.
	 */
	public int addElement(int element)
	{
		int i = last;
		
		if (i == heap.length)
		{
			System.out.println("Not enough space in heap.");
			return -1;
		}
		
		heap[i] = element;
		++last;
		return reheapifyUp(i);
	}
	
	/**
	 * This method removes the root, or the value at heap[0]. It calls on the
	 * method {@link #reheapifyDown(int)} for support. It also checks to see if 
	 * the root is actually the last element in the heap because there were
	 * problems with just removing and replacing with the 'last' element (that
	 * is, it would just replace itself with itself).
	 * @return It returns the root's value (in other words, the element that was 
	 * 			just removed). It will return -1 if the heap is empty.
	 */
	public int removeRoot()
	{
		if (last == 0)
		{
			int res = heap[0];
			heap[0] = -1;
			return res;
		}
		
		if (heap[0] != -1)
		{
			int res = heap[0];
			heap[0] = heap[last - 1];
			heap[last - 1] = -1;
			--last;
			
			int current = 0;
			
			reheapifyDown(current);
			
			return res;
		}
		else
		{
			// heap is empty
			return -1;
		}
	}
	
	/**
	 * This method simply prints the first ten elements of the {@link #heap}.
	 */
	public void printFirstTen()
	{
		for (int i = 0; i < 10; ++i)
		{
			if (heap[i] != -1)
			{
				System.out.print(heap[i] + ", ");
			}
		}
		
		if (heap[0] == -1)
		{
			// empty
			System.out.println("The set is empty.");
		}
	}
	
	/**
	 * This method supports the {@link #addElement(int)} method. It simply
	 * swaps a node with its parent if necessary (the one that is the parent
	 * ought to be the greater one).
	 * @param index - the index to begin the reheapification from, which in
	 * 			our case is always essentially the latest addition to the
	 * 			{@link #heap}.
	 * @return swapCounter - as it sounds, it counts the number of swaps
	 */
	private int reheapifyUp(int index)
	{
		int swapCounter = 0;
		while (heap[index] >= heap[findParent(index)] && index > 0)
		{
			int holder = heap[findParent(index)];
			heap[findParent(index)] = heap[index];
			heap[index] = holder;
			
			index = findParent(index);
			++swapCounter;
		}
		return swapCounter;
	}
	
	/**
	 * This method supports both the {@link #build(int[])} method and the 
	 * {@link #removeRoot()} method. This one was somewhat tricky in that
	 * I did not really find the pseudocode provided during the lectures to
	 * be particularly helpful, but maybe I was doing it wrong, so I will
	 * explain this one in more detail in the actual method.
	 * @param current - the index to start at for reheapification down
	 * @return swapCounter - it returns a swap count
	 */
	private int reheapifyDown(int current)
	{
		int swapCounter = 0;
		
		// The bounds had to be checked because you cannot simply
		// access the heap array at some given index. The index must
		// first exist.
		while (current <= last)
		{
			int holder = heap[current];
			int right = findRightChild(current);
			int left = findLeftChild(current);
			
			// Same here, the index of the right child must exist.
			if (right <= last)
			{
				// if right does exist (then of course left also exists since it is
				// a complete binary tree after all)
				if (holder < heap[right] || holder < heap[left])
				{
					// if the holder is less than either of its children, then
					// a swap must occur.
					if (heap[right] > heap[left])
					{
						// right's data is greater than the left's
						// so the right swaps with the node in question
						heap[current] = heap[right];
						heap[right] = holder;
						current = right;
						++swapCounter;
					}
					else
					{
						// left is bigger, so left must swap
						heap[current] = heap[left];
						heap[left] = holder;
						current = left;
						++swapCounter;
					}
				}
				else
				{
					// it would be pointless to continue the loop
					current = last + 1;
				}
			}
			else
			{
				// if right does not exist, that does not mean the left
				// will not!
				if (left < heap.length)
				{
					// so left must be checked
					if (heap[left] > holder)
					{
						// left is bigger than element in question, so swap
						heap[current] = heap[left];
						heap[left] = holder;
						++swapCounter;
					}
				}
				current = left;
			}
		}
		return swapCounter;
	}
	
	/**
	 * These are little helper methods that are simply the rules of an
	 * array implementation of a complete binary tree in compact,
	 * easy-to-access form. :)
	 * @param childIndex - the child whose parent is to be located
	 * @param parentIndex - the parent whose children are to be located
	 * @return - the corresponding integer value of the index
	 */
	private int findParent(int childIndex)
	{
		return ((childIndex - 1) / 2);
	}
	private int findLeftChild(int parentIndex)
	{
		return (2 * parentIndex + 1);
	}
	private int findRightChild(int parentIndex)
	{
		return (2 * parentIndex + 2);
	}

}
