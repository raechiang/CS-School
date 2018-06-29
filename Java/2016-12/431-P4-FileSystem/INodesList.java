/**
 * Rachel Chiang
 * CS 431-02
 * Project 4: File Allocation Table Simulation
 */

/**
 * This class represents the inodes list, which is just a singly-linked list.
 * You can check if a file exists, add a new inode to the list, remove an inode
 * from the list, and retrieve the head.
 */
public class INodesList
{
    /**
     * This is just the head, which gives access to the list.
     */
    private INode head;
    
    /**
     * The constructor.
     */
    public INodesList()
    {
        head = null;
    }
    
    /**
     * This method checks to see if an inode with the given file name exists in
     * the list already.
     * @param name - The name of the file to search for.
     * @return It returns true if it exists, and false if it does not.
     */
    public boolean contains(String name)
    {
        INode current = head;
        if (current == null)
        {
            return false;
        }
        
        while (current != null)
        {
            if (current.getFileName().equals(name))
            {
                return true;
            }
            current = current.getNext();
        }
        
        return false;
    }
    
    /**
     * This method adds a new inode to the list.
     * @param name - the file name
     * @param startIndex - the index in the FAT that its pointers begin at
     * @param size - the number of blocks that the new file should take up
     * @return - It actually always returns true.
     */
    public boolean add(String name, int startIndex, int size)
    {
        INode current = head;
        if (current == null)
        {
            // if the head is null, it will make a new inode and point to it
            head = new INode(name, startIndex, size);
            return true;
        }
        
        // otherwise we go to the end of the list
        while (current.getNext() != null)
        {
            current = current.getNext();
        }
        current.setNext(new INode(name, startIndex, size));
        
        return true;
    }
    
    /**
     * This method removes an inode from the list. It calls
     * {@link #remove(INode, INode)} to actually remove the inode. 
     * @param name - The name of the file that you wish to remove.
     * @return - It returns -1 if it a file of that name does not exist, or it
     *          returns the starting index of the file that you wish to remove
     *          so that the bitmap may be adjusted accordingly.
     */
    public int remove(String name)
    {
        // if the file does not exist
        if (!(contains(name)))
        {
            System.out.println(" File of name " + name + " does not exist.");
            return -1;
        }
        
        INode current = head;
        
        if (current.getFileName().equals(name))
        {
            // name is the head, remove the inode
            remove(null, current);
            return current.getStartIndex();
        }
        
        if (current.getNext() != null)
        {
            // more than one inode exists so we must search
            INode prev = current;
            current = current.getNext();
            while (current != null)
            {
                if (current.getFileName().equals(name))
                {
                    // remove the inode
                    remove(prev, current);
                    return current.getStartIndex();
                }
                prev = prev.getNext();
                current = current.getNext();
            }
            System.out.println(" File of name " + name + " does not exist.");
            return -1;
        }
        else
        {
            System.out.println(" File of name " + name + " does not exist.");
            return -1;
        }
    }
    
    /**
     * This method is the one that actually removes the node. It's called only
     * by {@link #remove(String)}.
     * @param prev - the node that came before the one that is to be removed
     * @param current - the node that is to be removed
     */
    private void remove(INode prev, INode current)
    {
        if (prev == null)
        {
            // only one INode exists, removing it makes an empty list
            head = current.getNext();
        }
        else
        {
            // remove the current node by removing the pointer to it
            prev.setNext(current.getNext());
            current.setNext(null);
        }
    }
    
    /**
     * Get the {@link #head}. This is used for printing the inodes.
     * @return the {@link #head}
     */
    public INode getHead()
    {
        return head;
    }
}