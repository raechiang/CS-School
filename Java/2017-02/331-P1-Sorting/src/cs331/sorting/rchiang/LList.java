package cs331.sorting.rchiang;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LList<E extends Comparable<?>> implements List<E>
{
    private Node<E> head;
    private int size;
    
    public LList()
    {
        head = null;
        size = 0;
    }
    
    public LList(LList<E> oldList)
    {
        addAll(oldList);
    }
    
    @Override
    public boolean add(E e)
    {
        if (head == null)
        {
            head = new Node<E>(e, null, null);
            ++size;
   //         System.out.println("Added " + e + " to head.");
            return true;
        }
        
        Node<E> curr = head;
        
        while (curr.hasNext())
        {
            curr = curr.getNext();
        }
        
   //     System.out.println("Added " + e + " after " + curr.getElement());
        curr.setNext(new Node<E>(e, null, curr));
        ++size;
        return true;
    }

    @Override
    public void add(int index, E element)
    {
        if (index < 0)
        {
            System.err.println("Index out of bounds.");
            return;
        }
        if (size == 0)
        {
            add(element);
            return;
        }
        
        if (index < size)
        {
            Node<E> curr = head;
            Node<E> prev = null;
            int i = 0;
            while (i < index)
            {
                prev = curr;
                curr = curr.getNext();
                ++i;
            }
            Node<E> n = curr.getNext();
            if (prev != null)
            {
                curr.setNext(new Node<E>(element, n, prev));
            }
            else
            {
                curr.setNext(new Node<E>(element, n, null));
            }
            ++size;
        }
        else
        {
            System.err.println("Index out of bounds.");
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        Object[] cArr = c.toArray();
        for (int i = 0; i < c.size(); ++i)
        {
            add((E) cArr[i]);
        }
        return true;
    }
    
    public Node<E> getHead()
    {
        return head;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        if (index < 0)
        {
            System.err.println("Index out of bounds");
            return false;
        }
        
        Object[] cArr = c.toArray();
        for (int i = c.size() - 1; i >= 0; --i)
        {
            add(index, (E) cArr[i]);
        }
        return true;
    }

    @Override
    public void clear()
    {
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(Object o)
    {
        if (size == 0)
        {
            return false;
        }
        
        Node<E> curr = head;
        for (int i = 0; i < size; ++i)
        {
            if (curr.getElement().equals(o))
            {
                return true;
            }
            if (curr.hasNext())
            {
                curr = curr.getNext();
            }
        }
        return false;
    }
    
    @Override
    public boolean containsAll(Collection<?> c)
    {
        if (size < c.size())
        {
            return false;
        }
        
        Object[] cArr = c.toArray();
        int sameCounter = 0;
        for (int i = 0; i < c.size(); ++i)
        {
            if (!(contains(cArr[i])))
            {
                return false;
            }
            ++sameCounter;
        }
        if (sameCounter == c.size())
        {
            return true;
        }
        return false;
    }

    @Override
    public E get(int index)
    {
        if (index >= size || index < 0)
        {
            return null;
        }
        
        Node<E> current = head;
        for (int i = 0; i < index; ++i)
        {
            current = current.getNext();
        }
        
        return current.getElement();
    }

    @Override
    public int indexOf(Object o)
    {
        if (size == 0)
        {
            return -1;
        }
        
        Node<E> curr = head;
        for (int i = 0; i < size; ++i)
        {
            if (curr.getElement().equals(o))
            {
                return i;
            }
            curr = curr.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator()
    {
        LListIterator<E> llistit = new LListIterator<E>(head, 0);
        return llistit;
    }

    @Override
    public int lastIndexOf(Object o)
    {
        if (size == 0)
        {
            return -1;
        }
        
        int index = -1;
        int i = 0;
        Node<E> current = head;
        while (current.getNext() != null)
        {
            if (current.getElement().equals(o))
            {
                index = i;
            }
            ++i;
            current = current.getNext();
        }
        
        return index;
    }

    @Override
    public ListIterator<E> listIterator()
    {
        LListIterator<E> llistit = new LListIterator<E>(head, 0);
        return llistit;
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        if (index > size || index < 0)
        {
            return null;
        }
        
        Node<E> current = head;
        
        for (int i = 0; i < index; ++i)
        {
            current = current.getNext();
        }
        
        LListIterator<E> listit = new LListIterator<E>(current, index);
        
        return listit;
    }

    @Override
    public boolean remove(Object o)
    {
        if (contains(o))
        {
            if (size == 1)
            {
                if (head.getElement().equals(o))
                {
                    --size;
                    head = null;
                    return true;
                }
                return false;
            }
            
            Node<E> current = head;
            Node<E> prev = null;
            while (current.hasNext())
            {
                if (current.getElement().equals(o))
                {
                    if (prev != null)
                    {
                        prev.setNext(current.getNext());
                        current.getNext().setPrevious(prev);
                    }
                    current.setNext(null);
                    --size;
                    return true;
                }
                prev = current;
                current = current.getNext();
            }
        }
        return false;
    }
    
    public Node<E> getNode(int index)
    {
        if (index >= size || index < 0)
        {
            return null;
        }
        
        Node<E> current = head;
        for (int i = 0; i < index; ++i)
        {
            current = current.getNext();
        }
        
        return current;
    }

    @Override
    public E remove(int index)
    {
        if (size == 1)
        {
            if (index == 0)
            {
                E e = head.getElement();
                head = null;
                --size;
                return e;
            }
            return null;
        }
        
        if (index == 0)
        {
        //    System.out.print("size " + size + " ");
            E e = head.getElement();
            Node<E> newHead = getHead().getNext();
            newHead.setPrevious(null);
            head.setNext(null);
            head = newHead;
            --size;
            return e;
        }
        
        if (index > 0 && index < size)
        {
            Node<E> current = getNode(index);
            Node<E> prev = getNode(index - 1);
            if (current.hasNext())
            {
                prev.setNext(current.getNext());
                current.getNext().setPrevious(prev);
            }
            E e = current.getElement();
            current.setNext(null);
            --size;
            return e;
        }
        return null;
    }

    /**
     * Removes from this list all of its elements that are 
     * contained in the specified collection (optional operation).
     */
    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("removeAll(Collection<?>) is not supported by this list.");
    }

    /**
     * Retains only the elements in this list that are contained in
     * the specified collection (optional operation).
     */
    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("retainAll(Collection<?>) is not supported by this list.");
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     */
    @Override
    public E set(int index, E element)
    {
        if (index < size && index >= 0)
        {
            getNode(index).setElement(element);
        }
        
        return null;
    }

    @Override
    public int size()
    {
        return size;
    }

    /**
     * Returns a view of the portion of this list between the
     * specified fromIndex, inclusive, and toIndex, exclusive.
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        if (toIndex > size)
        {
            return null;
        }
        if (fromIndex < 0)
        {
            return null;
        }
        
        List<E> sublist = new LList<E>();
        Node<E> current = getNode(fromIndex);
        
        for (int i = fromIndex; i < toIndex; ++i)
        {
            sublist.add(current.getElement());
            current = current.getNext();
        }
        // TODO Auto-generated method stub
    //    System.err.println("Please do not use this.");
        return sublist;
    }
    
    public LList<E> chopLList(int toIndex)
    {
        if (toIndex > size || toIndex < 1)
        {
            return null;
        }
        
        LList<E> sublist = new LList<E>();
        for (int i = 0; i < toIndex; ++i)
        {
       //     System.out.print(i + ": " + get(0) + " ");
            E e = remove(0);
       //     System.out.println("Added " + e);
            sublist.add(e);
        }
        
        return sublist;
    }

    @Override
    public Object[] toArray()
    {
        Object[] arr = new Object[size];
        Node<E> current = head;
        for (int i = 0; i < size; ++i)
        {
            arr[i] = current;
            if (current.hasNext())
            {
                current = current.getNext();
            }
        }
        
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        Node<E> current = head;
        for (int i = 0; i < a.length; ++i)
        {
            if (a.length <= size)
            {
                a[i] = (T) current.getElement();
                current = current.getNext();
            }
        }
        return a;
    }
    
    public void write()
    {
        Node<E> current = head;
        String s = "";
        while (current.hasNext())
        {
            s += current.getElement() + "  ";
            current = current.getNext();
        }
        System.out.println(s);
    }

}
