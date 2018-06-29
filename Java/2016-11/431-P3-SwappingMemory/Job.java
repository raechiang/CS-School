/**
 * Rachel Chiang
 * CS 431-02
 * Project 3 Swapping
 */

/**
 * This class was given.
 */
public final class Job
{
    private final int pid;
    private final int size;
    private Job next;
    
    public Job(int pid, int size)
    {
        this.pid = pid;
        this.size = size;
        next = null;
    }
    
    public void setNext(Job next)
    {
        this.next = next;
    }
    
    public int getPid()
    {
        return pid;
    }
    public int getSize()
    {
        return size;
    }
    public Job getNext()
    {
        return next;
    }
    
    @Override
    public String toString()
    {
        return String.format("[%d %d]", pid, size);
    }
}
