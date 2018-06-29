/**
 * Rachel Chiang
 * CS 431-02
 * Project 3 Swapping
 * 
 * This class represents the memory. It contains the bulk of the logic for this
 * project. It contains a reference to the {@link #start} of the list and to
 * a {@link #bookmark} which is only used by {@link #nextFitAllocation(Job)}.
 */
public class Memory
{
    private Segment start;
    /**
     * The bookmark is updated only in {@link #nextFitAllocation(Job)}. The
     * directions weren't clear on how to implement this exactly since it is
     * possible to mix different allocating algorithms.
     */
    private Segment bookmark;
    
    /**
     * The memory starts with a segment identified as a hole (pid = 0).
     */
    public Memory()
    {
        start = new Segment(0, 0, 100, null);
        bookmark = start;
    }
    
    /**
     * Depending on the passed command, this method will call one of the other
     * methods to handle the allocation. It returns true or false based on
     * whether it has successfully allocated or not.
     * ff - {@link #firstFitAllocation(Job)}
     * nf - {@link #nextFitAllocation(Job)}
     * bf - {@link #bestFitAllocation(Job)}
     * wf - {@link #worstFitAllocation(Job)}
     */
    public boolean allocate(String command, Job job)
    {
        if (doesSegmentExist(job.getPid()))
        {
            // already allocated
            System.out.println(job.getPid() + " is already allocated.");
            return false;
        }
        
        if (command.startsWith("ff"))
        {
            return firstFitAllocation(job);
        }
        else if (command.startsWith("nf"))
        {
            return nextFitAllocation(job);
        }
        else if (command.startsWith("bf"))
        {
            return bestFitAllocation(job);
        }
        else if (command.startsWith("wf"))
        {
            return worstFitAllocation(job);
        }
        
        // Shouldn't reach here because this method is called after the 
        // command has already passed the validity checks
        return false;
    }
    
    /**
     * This method deallocates a job given the integer process ID. It first
     * checks to see if the segment actually exists (so it can be removed)
     * using {@link #doesSegmentExist(int)}. If it does, then it will proceed
     * to deallocate it using {@link #unlink(int)}. 
     * @param pid
     * @return
     */
    public boolean deallocate(int pid)
    {
        if (doesSegmentExist(pid))
        {
            unlink(pid);
            return true;
        }
        System.out.println(" The given ID " + pid + " does not exist in the"
                + " memory.");
        return false;
    }
    
    /**
     * This method is called by one of the many allocation methods. It simply
     * adds a link to the appropriate space, which is indicated by "opening".
     */
    private void addLink(Segment opening, Job job)
    {
        Segment newJob = new Segment(job.getPid(), opening.getStart(), 
                job.getSize(), null);
        
        // deals with relinking the previous
        Segment prev = start;
        if (prev.getNext() != null)
        {
            while (prev != null && prev.getNext() != opening)
            {
                prev = prev.getNext();
            }
            if (prev != null)
            {
                prev.setNext(newJob);
            }
        }
        
        // if it's empty, it will allocate at the beginning of the list
        if (opening == start)
        {
            start = newJob;
        }
        
        opening.setStart(opening.getStart() + newJob.getLength());
        opening.setLength(opening.getLength() - newJob.getLength());
        if (opening.getLength() != 0)
        {
            newJob.setNext(opening);
        }
        else
        {
            newJob.setNext(opening.getNext());
        }
    }
    
    /**
     * This method is called by {@link #deallocate(int)}. It removes the
     * segment that has the same pid as the passed argument. There are many
     * different cases to handle. There are four cases below that are redundant
     * but I'm really tired and have had a lot of midterms and rough workdays.
     * It looks neater to have full if-else statements and explanations anyway.
     * Maybe TODO for another day: Reduce redundancy
     */
    private void unlink(int pid)
    {
        // First it finds the corresponding process and keeps references to
        // the process directly previous and directly following it.
        Segment current = start;
        Segment previous = null;
        Segment next = current.getNext();
        while (current.getPid() != pid)
        {
            previous = current;
            current = current.getNext();
            next = current.getNext();
        }
        
        if (previous == null)
        {
            // C -> ?
            // Current is start
            if (next != null)
            {
                if (next.getPid() == 0)
                {
                    // C -> H -> X
                    // The current attaches to a hole, so...
                    next.setStart(0);
                    next.setLength(next.getLength() + current.getLength());
                    start = next;
                    // We simply need to turn the "hole" thing into a hole :) 
                    current.setPid(0);
                    current.setNext(null);
                    //     H -> X
                }
                else
                {
                    // C -> P -> X
                    // Just need to turn the current into a hole.
                    current.setPid(0);
                    // H -> P -> X
                    
                    // Could have probably just taken current.setPid(0) out of
                    // the if and else statement since they share the code
                    // but I think it looks neater to include it and explicitly
                    // describe the cases.
                }
            }
            else
            {
                // Current is lonely...
                current.setPid(0);
            }
        }
        else
        {
            // Current is not start
            if (next != null)
            {
                // ? -> C -> ?
                // Current is between two things
                if (previous.getPid() == 0)
                {
                    // X -> H -> C -> ? -> X
                    // Current is preceded by a hole
                    if (next.getPid() == 0)
                    {
                        // X -> H -> C -> H -> X
                        // Current is preceded and followed by a hole
                        previous.setLength(previous.getLength() 
                                + current.getLength() + next.getLength());
                        previous.setNext(next.getNext());
                        // So we turn the "hole" thing into one big hole. :)
                        // X -> H -----------> X
                    }
                    else
                    {
                        // X -> H -> C -> P -> X
                        // Current is preceded by a hole but followed by a
                        // process
                        previous.setNext(next);
                        previous.setLength(previous.getLength() 
                                + current.getLength());
                        // So we remove the current and adjust the hole's size
                        // and reference
                        // X -> H ------> P -> X
                    }
                }
                else
                {
                    // X -> P -> C -> ? -> X
                    // current is preceded by a process
                    if (next.getPid() == 0)
                    {
                        // X -> P -> C -> H -> X
                        // current is preceded by a process but followed by a
                        // hole
                        previous.setNext(next);
                        next.setStart(current.getStart());
                        next.setLength(next.getLength() + current.getLength());
                        // So we remove the current, change the hole's side
                        // and adjust the hole's length
                        // X -> P -> H ------> X
                    }
                    else
                    {
                        // X -> P -> C -> P -> X
                        // Current is preceded and followed by processes
                        current.setPid(0);
                        // We simply turn it into a hole
                        // X -> P -> H -> P -> X
                    }
                }
            }
            else
            {
                // X -> ? -> C
                // Current is last
                if (previous.getPid() == 0)
                {
                    // X -> H -> C
                    // current is preceded by a hole so we change the hole's
                    // length and remove the reference to the current
                    previous.setNext(null);
                    previous.setLength(previous.getLength() + current.getLength());
                }
                else
                {
                    // X -> P -> C
                    // current is preceded by a process so we simply change it
                    // into a hole
                    current.setPid(0);
                    // X -> P -> H
                }
            }
        }
    }
    
    /**
     * Simply checks to see if a process of the same ID as the passed argument
     * exists in memory already.
     */
    private boolean doesSegmentExist(int pid)
    {
        Segment current = start;
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
     * This method simply finds the first node long enough to accommodate the
     * new job. Very simple. It uses {@link #addLink(Segment, Job)} for help.
     */
    private boolean firstFitAllocation(Job job)
    {
        Segment spot = findNextEmpty(start, job.getSize());
        if (spot != null)
        {
            addLink(spot, job);
            return true;
        }
        System.out.println(" No open slots available.");
        return false;
    }
    
    /**
     * This method simply finds the first node that is large enough starting
     * from a bookmarked segment called {@link #bookmark}. Once it has found an
     * appropriate segment, it will save its place as a {@link #bookmark} again
     * so that the next time nf is called it can start its search from the
     * {@link #bookmark}. It uses {@link #findNextEmpty(Segment, int)} for
     * help.
     */
    private boolean nextFitAllocation(Job job)
    {
        Segment spot = findNextEmpty(bookmark, job.getSize());
        if (spot != null)
        {
            addLink(spot, job);
            bookmark = spot;
            return true;
        }
        System.out.println(" No open slots available.");
        return false;
    }
    
    /**
     * This method must search the entire list for the smallest hole that could
     * fit the new process to be allocated.
     */
    private boolean bestFitAllocation(Job job)
    {
        Segment current = start;
        Segment shortest = null;
        
        while (current != null)
        {
            if (current.getPid() == 0)
            {
                if (current.getLength() >= job.getSize())
                {
                    if (shortest == null)
                    {
                        shortest = current;
                    }
                    else
                    {
                        if (shortest.getLength() > current.getLength())
                        {
                            shortest = current;
                        }
                    }
                }
                
            }
            current = current.getNext();
        }
        
        if (shortest != null)
        {
            addLink(shortest, job);
            return true;
        }
        else
        {
            System.out.println(" No open slots available.");
            return false;
        }
    }
    
    /**
     * Similar to the {@link #bestFitAllocation(Job)}, this method searches the
     * entire list for the largest hole to fit the new process.
     */
    private boolean worstFitAllocation(Job job)
    {
        Segment current = start;
        Segment longest = null;
        
        while (current != null)
        {
            if (current.getPid() == 0)
            {
                if (current.getLength() >= job.getSize())
                {
                    if (longest == null)
                    {
                        longest = current;
                    }
                    else
                    {
                        if (longest.getLength() < current.getLength())
                        {
                            longest = current;
                        }
                    }
                }
                
            }
            current = current.getNext();
        }
        
        if (longest != null)
        {
            addLink(longest, job);
            return true;
        }
        else
        {
            System.out.println(" No open slots available.");
            return false;
        }
    }
    
    /**
     * This method finds the next empty segment. It is used by
     * {@link #firstFitAllocation(Job)} and {@link #nextFitAllocation(Job)}.
     * It simply finds the first Segment it encounters starting from the
     * passed segment (current). 
     */
    private Segment findNextEmpty(Segment current, int size)
    {
        while (current != null)
        {
            if (current.getPid() == 0)
            {
                // hole found
                if (current.getLength() >= size)
                {
                    return current;
                }
            }
            current = current.getNext();
        }
        return null;
    }
    
    /**
     * This method just generates the list.
     */
    @Override
    public String toString()
    {
        Segment current = start;
        String list = "";
        
        while (current != null)
        {
            list += current.toString() + " ";
            current = current.getNext();
        }
        
        return list;
    }
}
