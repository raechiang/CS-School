/**
 * Rachel Chiang
 * CS 431-02
 * Project 3 Swapping
 * 
 * This class is a simple list of current existing jobs on the system. It is
 * only possible to add to this list, not remove.
 */
public class JobList
{
    /**
     * This field provides access to the first job of the list.
     */
    private Job start;
    
    /**
     * {@link #start} is initialized as null.
     */
    public JobList()
    {
        start = null;
    }
    
    /**
     * This method simply adds a job given some integer pid and size. It asks
     * {@link #doesJobExist(int)} if a job with the same pid is already in the
     * list. If it does, then it will not add the new process, but if it does
     * not, it will add a new job using {@link #addLast}.
     */
    public boolean addJob(int pid, int size)
    {
        if (start == null)
        {
            start = new Job(pid, size);
            return true;
        }
        
        if (doesJobExist(pid))
        {
            System.out.println(pid + " already exists in the job list.");
            return false;
        }
        else
        {
            addLast(new Job(pid, size));
            return true;
        }
    }
    
    /**
     * This method simply checks to see if a job with an identical pid (passed
     * as the argument) exists in the system.
     */
    public boolean doesJobExist(int pid)
    {
        Job current = start;
        while (current != null)
        {
            if (current.getPid() == pid)
            {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    
    /**
     * This method adds a new job to the list.
     */
    private void addLast(Job newJob)
    {
        Job current = start;
        while (current.getNext() != null)
        {
            current = current.getNext();
        }
        
        current.setNext(newJob);
    }
    
    public Job getStart()
    {
        return start;
    }
    
    public Job getJob(int pid)
    {
        // Searches list for the job that has the same process ID.
        Job desired = start;
        while (desired != null)
        {
            if (desired.getPid() == pid)
            {
                return desired;
            }
            desired = desired.getNext();
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        Job current = start;
        String jobs = "";
        
        while (current != null)
        {
            jobs += current.toString() + " ";
            current = current.getNext();
        }
        
        return jobs;
    }
}
