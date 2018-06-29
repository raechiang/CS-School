/**
 * Rachel Chiang
 * CS 431-02
 * Project 3 Swapping
 */
import java.util.Scanner;

/**
 * Simple UI stuff.
 */
public class UserInterface
{
    private Scanner sc;
    
    public UserInterface()
    {
        sc = new Scanner(System.in);
        welcome();
    }
    
    private void welcome()
    {
        System.out.println("Greetings." +
                "\nHere are the possible commands:" +
                "\n  add n,s   Adds a job number n of size s to the list of jobs on the system." +
                "\n  jobs      Prints the list of jobs on the system." +
                "\n  list      Prints the current contents of the linked list of segments." +
                "\n  ff n      Invokes the first fit allocation for job number n." +
                "\n  nf n      Invokes the next fit allocation for job number n." +
                "\n  bf n      Invokes the best fit allocation for job number n." +
                "\n  wf n      Invokes the worst fit allocation for job number n." +
                "\n  de n      Invokes deallocation for job number n." +
                "\n  q OR exit Exits the program." +
                "\nNote: Both n and s are integers. Input is not case-sensitive.");
        System.out.println("--------------------------------------------------------------------------------");
    }
    
    public boolean wantsToStop(String command)
    {
        if (command.equalsIgnoreCase("q")
                || command.equalsIgnoreCase("exit"))
        {
            return true;
        }
        return false;
    }
    
    public String getUserCommand()
    {
        System.out.print("> ");
        String cmd = "";
        if (sc.hasNextLine())
        {
            cmd = sc.nextLine();
            return cmd;
        }
        return null;
    }
    
    public void printExitMessage()
    {
        System.out.println("Exiting program.");
    }
    
    public void printBadInputMessage()
    {
        System.out.println("Please try again.");
    }
}
