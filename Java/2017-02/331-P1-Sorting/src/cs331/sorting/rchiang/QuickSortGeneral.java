package cs331.sorting.rchiang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class QuickSortGeneral<T extends Comparable<T>> implements Sort<T>
{
    private ArrayList<T> list;
    
    public QuickSortGeneral()
    {
        list = new ArrayList<T>();
    }
    
    @Override
    public void init(List<T> list)
    {
        while (!(list.isEmpty()))
        {
            T item = list.remove(0);
            this.list.add(item);
        }
    }
    
    @Override
    public List<T> getSortedList()
    {
        return quickSort(this.list, 0, this.list.size() - 1);
    }
    
    public List<T> quickSort(List<T> list, int low, int high)
    {
        int pivot;
        if (high > low)
        {
            // Overall Runtime 2O(n/2) + O(n) + cO(1) => O(nlogn)
            
            // Auxiliary Space O(n/2) + O(1) => O(log n)
            // Recursive calls end
            // Runtime O(n)
            // Auxiliary Space O(n/2)
            pivot = partition(list, low, high);
            // Runtime O(n/2)
            quickSort(list, low, pivot - 1);
            // Runtime O(n/2)
            quickSort(list, pivot + 1, high);
        }
        return list;
    }
    
    public abstract int partition(List<T> list, int low, int high);
}
