/**
 * Rachel Chiang
 * CS 431-02
 * Project 3 Swapping
 * 
 * This class's important purposes are to contain an instance of the Memory 
 * and JobList (as {@link #jobsInMem} and {@link #jobsInSys}), to check the
 * validity of the user-given command with {@link #isValid}, and to process
 * the commands by giving it to the proper class to handle.
 * 
 * I meant to use it to tie all the classes together nicely and make a more 
 * organized UserInterface class that actually handled all the UI stuff, but I
 * am tired and lazy. Maybe TODO that for some other time.
 */
public class MemoryManager
{
    private Memory jobsInMem;
    private JobList jobsInSys;
    private String command;
    
    public MemoryManager()
    {
        jobsInMem = new Memory();
        jobsInSys = new JobList();
        command = "";
    }
    
    /**
     * This method simply checks the validity of a passed String command.
     */
    public boolean isValid(String command)
    {
        if (command.equalsIgnoreCase("jobs") 
                || command.equalsIgnoreCase("list"))
        {
            this.command = command;
            return true;
        }
        
        command = command.toLowerCase();
        if (command.startsWith("add") && command.contains(","))
        {
            this.command = command;
            return true;
        }
        
        if (command.startsWith("ff") 
                || command.startsWith("nf")
                || command.startsWith("bf") 
                || command.startsWith("wf") 
                || command.startsWith("de"))
        {
            this.command = command;
            return true;
        }
        
        System.out.println("Invalid command.");
        return false;
    }
    
    /**
     * This method actually processes the commands.
     */
    public boolean processCommand()
    {
        if (command.equalsIgnoreCase("jobs"))
        {
            // Lists the jobs in the system
            System.out.println(jobsInSys.toString());
            return true;
        }
        else if (command.equalsIgnoreCase("list"))
        {
            // Lists the processes in the memory
            System.out.println(jobsInMem.toString());
            return true;
        }
        else if (command.startsWith("add"))
        {
            // Adds a job to jobsInSys
            int commaIndex = command.indexOf(',');
            try {
                int jobID = Integer.parseInt(command.substring(4, commaIndex).trim());
                int size = Integer.parseInt(command.substring(commaIndex + 1).trim());
                if (jobID != 0 && size > 0)
                {
                    return jobsInSys.addJob(jobID, size);
                }
                else
                {
                    System.out.println("Job ID cannot be 0 and size must be greater than 0.");
                    return false;
                }
            } catch (NumberFormatException nfe)
            {
                System.out.println("For command add n,s: n and s must be integers.");
                return false;
            } catch (StringIndexOutOfBoundsException siooe)
            {
                System.out.println("Please use the format add n,s.");
                return false;
            }
        }
        else
        {
            // It must be allocation or deallocation since it's already
            // passed the command validity check.
            try {
                int jobID = (Integer.parseInt(command.substring(3).trim()));
                if (command.startsWith("de"))
                {
                    return jobsInMem.deallocate(jobID);
                }
                else
                {
                    if (jobsInSys.doesJobExist(jobID))
                    {
                        // allocate
                        return jobsInMem.allocate(command, jobsInSys.getJob(jobID));
                    }
                    else
                    {
                        System.out.println("The job does not exist in system!");
                        return false;
                    }
                }
            } catch (NumberFormatException e)
            {
                System.out.println("Bad number input.");
                return false;
            } catch (StringIndexOutOfBoundsException siooe)
            {
                System.out.println("Please specify the job ID.");
                return false;
            }
        }
    }
}
