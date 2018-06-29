package cs331.sorting.rchiang;

import java.util.List;

public class InsertionSort<T extends Comparable<T>> implements Sort<T>
{
    private LList<T> list;
    
    public InsertionSort()
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
        insertionSort();
        return list;
    }
    
    private void insertionSort()
    {
        // Input Space, Output Space O(n)
        // Auxiliary Space O(1) - all actions are performed on original list
        
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
    }
}