package cs331.sorting.rchiang;

import java.util.LinkedList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements Sort<T>
{
    private LinkedList<T> list;
    
    public MergeSort()
    {
        list = new LinkedList<T>();
    }
    
    @Override
    public List<T> getSortedList()
    {
         // Input Space O(n), Output Space O(n)
         return mergeSort(this.list);
    }
    
    private List<T> mergeSort(List<T> list)
    {
        if (list.size() == 1)
        {
            // Runtime O(1)
            return list;
        }
        // Runtime f(n/2), Auxiliary Space O(n/2)
        List<T> left = mergeSort(new LinkedList<T>(list.subList(0, list.size()/2)));
        // Runtime f(n/2), Auxiliary Space sublist actually returns a view of the same list
        List<T> right = mergeSort(list.subList(list.size()/2, list.size()));
        // Runtime O(n) -- see below
        return merge(left, right);
    }
    
    private List<T> merge(List<T> left, List<T> right)
    {
        // Runtime O(1)
        // Auxiliary Space The list is divided in half, O(n/2) + O(1)
        //   => O(logn)
        List<T> both = new LinkedList<T>();
        
        while (!(left.isEmpty()) && !(right.isEmpty()))
        {
            // Runtime One of the two will empty first
            if (left.get(0).compareTo(right.get(0)) <= 0)
            {
                // By removing as we go and adding to the empty list,
                // the total auxiliary space is not actually affected
                // left item <= right item
                both.add(left.remove(0));
            }
            else
            {
                // left item > right item
                both.add(right.remove(0));
            }
        }
        
        while(!(left.isEmpty()))
        {
            both.add(left.remove(0));
        }
        while (!(right.isEmpty()))
        {
            both.add(right.remove(0));
        }
        
        // Overall runtime O(n) because it will iterate only for the size
        // of the left and right lists, which were divided into 2.
        // Thus, (n/2) + (n/2) = n
        return both;
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
}
