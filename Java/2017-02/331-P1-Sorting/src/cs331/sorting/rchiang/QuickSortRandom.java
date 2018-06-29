package cs331.sorting.rchiang;

import java.util.List;
import java.util.Random;

public class QuickSortRandom<T extends Comparable<T>> extends QuickSortGeneral<T>
{
    @Override
    public int partition(List<T> list, int low, int high)
    {
        // pick random index
        Random rng = new Random();
        int pivotIndex = low + rng.nextInt(high - low + 1);
        
        T pivot = list.get(pivotIndex);
        
        // swap randomly picked pivot with the first
        list.set(pivotIndex, list.get(low));
        list.set(low, pivot);
        pivotIndex = low;
        
        // continue
        int i = high;
        for (int j = high; j > low; --j)
        {
            // iterates from the last to the first (the pivot)
            if (list.get(j).compareTo(pivot) >= 0)
            {
                // if the current >= pivot, swap the
                // current with element i
                T swap = list.get(i);
                list.set(i, list.get(j));
                list.set(j, swap);
                --i;
            }
        }
        list.set(low, list.get(i));
        list.set(i, pivot);
        
        return i;
    }
}
