package cs331.sorting.rchiang;

import java.util.ListIterator;

public class LListIterator<E extends Comparable<?>> implements ListIterator<E>
{
    private Node<E> current;
    private int index;
    
    public LListIterator(Node<E> n, int i)
    {
        current = n;
        index = i;
    }
    
    /**
     * Inserts the specified element into the list (optional operation).
     */
    @Override
    public void add(E arg0)
    {
        throw new UnsupportedOperationException("Invalid operation for this linked list.");
    }
    
    /**
     * Returns true if this list iterator has more elements when
     * traversing the list in the forward direction.
     */
    @Override
    public boolean hasNext()
    {
        return current.hasNext();
    }

    /**
     * Returns true if this list iterator has more elements when
     * traversing the list in the reverse direction.
     */
    @Override
    public boolean hasPrevious()
    {
        return current.hasPrevious();
    }

    /**
     * Returns the next element in the list and advances the cursor position.
     */
    @Override
    public E next()
    {
        ++index;
        current = current.getNext();
        return current.getElement();
    }

    /**
     * Returns the index of the element that would be returned
     * by a subsequent call to next().
     */
    @Override
    public int nextIndex()
    {
        return index + 1;
    }

    /**
     * Returns the previous element in the list and moves the
     * cursor position backwards.
     */
    @Override
    public E previous()
    {
        current = current.getPrev();
        --index;
        return current.getElement();
    }

    /**
     * Returns the index of the element that would be
     * returned by a subsequent call to previous().
     */
    @Override
    public int previousIndex()
    {
        return index - 1;
    }

    /**
     * Removes from the list the last element that was
     * returned by next() or previous() (optional operation).
     */
    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Invalid operation for this linked list.");
    }

    /**
     * Replaces the last element returned by next() or previous()
     * with the specified element (optional operation).
     */
    @Override
    public void set(E arg0)
    {
        throw new UnsupportedOperationException("Invalid operation for this linked list.");
        
    }

}
