package cs331.sorting.rchiang;

public class Node<E extends Comparable <?>>
{
    private E element;
    private Node<E> next;
    private Node<E> prev;
    
    public Node()
    {
        element = null;
        next = null;
        prev = null;
    }
    
    public Node(E e, Node<E> n, Node<E> p)
    {
        element = e;
        next = n;
        prev = p;
    }
    
    public boolean setElement(E e)
    {
        element = e;
        return true;
    }
    
    public boolean hasNext()
    {
        if (next != null)
        {
            return true;
        }
        return false;
    }
    
    public boolean hasPrevious()
    {
        if (prev != null)
        {
            return true;
        }
        return false;
    }
    
    public boolean setNext(Node<E> n)
    {
        next = n;
        return true;
    }
    
    public boolean setPrevious(Node<E> p)
    {
        prev = p;
        return true;
    }
    
    public Node<E> getNext()
    {
        return next;
    }
    
    public Node<E> getPrev()
    {
        return prev;
    }
    
    public E getElement()
    {
        return element;
    }
}
