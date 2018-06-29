package cs331.sorting.rchiang;

import java.util.List;

public class MergeInsertionSort<T extends Comparable<T>> implements Sort<T>
{
    private LList<T> list;
    
    public MergeInsertionSort()
    {
        list = new LList<T>();
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
        LList<T> l = mergeInsertionSort(list);
        return l;
    }
    
    private LList<T> mergeInsertionSort(LList<T> list)
    {
        // Runtime and Auxiliary Space would be similar to merge and insertion
        if (list.size() == 1)
        {
            // Runtime O(1)
            return list;
        }
        
        if (list.size() <= 2048)
        {
            return insertionSort(list); 
        }
        
        // Runtime f(n/2)
        // chopList actually literally chops the list; it removes elements
        // from teh original list so that memory can be conserved
        LList<T> left = mergeInsertionSort(list.chopLList(list.size()/2));
        // Runtime f(n/2)
        LList<T> right = mergeInsertionSort(list.chopLList(list.size()));
        // Runtime O(n) -- see below
        return merge(left, right);
    }
    
    private LList<T> merge(LList<T> left, LList<T> right)
    {
        // Runtime O(1)
        LList<T> both = new LList<T>();
        
        while (!(left.isEmpty()) && !(right.isEmpty()))
        {
            // Runtime One of the two will empty first
            if (left.get(0).compareTo(right.get(0)) <= 0)
            {
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
        return both;
    }
    
    private LList<T> insertionSort(LList<T> list)
    {
        // Input Space, Output Space O(n)
        // Runtime O(1); Auxiliary Space O(1)
        Node<T> current = list.getHead().getNext();
        // Runtime O(n)
        while (current != null)
        {
            // Auxiliary Space O(1)
            Node<T> cursor = current;
            // Runtime O(n)
            while (cursor.hasPrevious() && cursor.getPrev().getElement().compareTo(cursor.getElement()) > 0)
            {
                // Runtime O(1); Auxiliary Space none here
                T swapElement = cursor.getPrev().getElement();
                cursor.getPrev().setElement(cursor.getElement());
                cursor.setElement(swapElement);
                cursor = cursor.getPrev();
            }
            current = current.getNext();
        }
        
        return list;
    }
}
