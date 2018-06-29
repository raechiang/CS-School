package cs331.sorting.rchiang;

import java.util.List;
import java.util.ListIterator;

/**
 * This Quick Sort uses the first element as a pivot.
 * @author Rachel
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> extends QuickSortGeneral<T>
{
    @Override
    public int partition(List<T> list, int low, int high)
    {
        // Runtime O(1) since it's an ArrayList
        // Auxiliary Space O(1)
        // Selects the first element of the partition
        T pivot = list.get(low);
        
        // Runtime O(1); Auxiliary Space O(1)
        int i = high;
        for (int j = high; j > low; --j)
        {
            // Runtime O(n); Auxiliary Space O(1)
            // iterates from the last to the first (the pivot)
            if (list.get(j).compareTo(pivot) >= 0)
            {
                // if the current >= pivot, swap the
                // current with element i
                // Auxiliary Space O(1), simple swap
                T swap = list.get(i);
                list.set(i, list.get(j));
                list.set(j, swap);
                --i;
            }
        }
        // Runtime O(1), simple swap
        list.set(low, list.get(i));
        list.set(i, pivot);
        
        return i;
    }
}
