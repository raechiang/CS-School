/**
 * Rachel Chiang
 * CS 431-02
 * Project 4: File Allocation Table Simulation
 */

/**
 * This class represents the inodes. They are just nodes. They hold the name of
 * the file, the starting index (for the FAT), and the size, and they point to
 * the next node in the list, or null if there is none. Its methods are all
 * simple getters and setters.
 */
public class INode
{
    private String fileName;
    private int startIndex;
    private int size;
    private INode next;
    
    public INode(String name, int start, int size)
    {
        this.fileName = name;
        this.startIndex = start;
        this.size = size;
        this.next = null;
    }
    
    public void setNext(INode newNode)
    {
        next = newNode;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public int getStartIndex()
    {
        return startIndex;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public INode getNext()
    {
        return next;
    }
}
