/**
 * Rachel Chiang
 * CS 431-02
 * Project 4: File Allocation Table Simulation
 */
import java.util.Scanner;

/**
 * This is the "manager" of the file allocation table, list of inodes, and
 * bitmap. It is where they all communicate and also provides the console inter-
 * face through which the user accesses the structures.
 */
public class FileAllocationManager
{
    /**
     * The file allocation table (FAT) is represented by an array of integers
     * which record the pointers for each block for an allocated file. The
     * end of a file is indicated by -1. It is instantiated in the constructor
     * {@link #FileAllocationManager()} and modified in {@link #fillFAT(int)}.
     * It is read from in {@link #delCommand(String)} and
     * {@link #printINodes()}.
     */
    private int[] fileAllocationTable;
    
    /**
     * This is a list that holds the inodes. It is instantiated as an empty list
     * in {@link #FileAllocationManager()} and modified when adding a file with 
     * {@link #putCommand(String)} and removing a file with
     * {@link #delCommand(String)}. It is also used in {@link #printINodes()}
     * for printing inodes and its pointers which are stored in the
     * {@link #fileAllocationTable}.
     */
    private INodesList iNodesList;
    
    /**
     * This is the bitmap, which marks whether a block is allocated or free. It
     * is instantiated in the {@link #FileAllocationManager()} and in its class,
     * as zero. In binary, the 0's denote a free block, and the 1's denote an
     * allocated block. Like the others, it is modified when adding or removing
     * (specifically in {@link #fillFAT(int)} and {@link #delCommand(String)}),
     * and it will print when the command "bitmap" is input.
     */
    private Bitmap bitmap;
    
    /**
     * This is the constructor. It instantiates the values and makes sure that
     * all of the indices in {@link #fileAllocationTable} start as -1.
     */
    public FileAllocationManager()
    {
        fileAllocationTable = new int[64];
        iNodesList = new INodesList();
        bitmap = new Bitmap();
        
        for (int i = 0; i < fileAllocationTable.length; ++i)
        {
            fileAllocationTable[i] = -1;
        }
    }
    
    /**
     * This method simply holds the user input loop and calls other functions
     * to handle with the actual command (see {@link #processCommand(String)}.
     * Note, user input is requested following a "> ".
     */
    public void run()
    {
        welcome();
        
        Scanner sc = new Scanner(System.in);
        boolean wantContinue = true;
        while (wantContinue)
        {
            // user input request
            System.out.print("> ");
            String command = sc.nextLine();
            // terminate loop with e or exit
            if (command.equalsIgnoreCase("e")
                    || command.equalsIgnoreCase("exit"))
            {
                wantContinue = false;
            }
            else
            {
                // process the command
                if (processCommand(command))
                {
                    // if it returns true that means the command worked
                    System.out.println("Command \'" + command
                                    + "\' was successful!");
                }
            }
        }
        System.out.println("Exiting.");
        sc.close();
    }
    
    /**
     * This method is called only once by {@link #run()}. It simply prints a
     * little welcome message and lists the commands.
     */
    private void welcome()
    {
        System.out.println("Welcome." + "\nThere are five commands:"
                       + "\n put name,size - Put a file with <name> of <size> blocks into the file system"
                       + "\n del name      - Delete existing file of <name> from the file system"
                       + "\n bitmap        - Print the 8x8 bitmap labeled by the starting block number"
                       + "\n inodes        - Print all of the i-nodes and the list of pointers from the FAT"
                       + "\n exit (e)      - Exits the program."
                       + "\n--------------------------------------------------------------------------------");
    }
    
    /**
     * This method processes the passed command and is called only in
     * {@link #run()}. The accepted commands are put, del, bitmap, and inodes.
     * "Exit" will have been processed in the loop before this method is called.
     * The cmd will be put through a series of if statements to check if the
     * command matches one of the four accepted commands. Depending on the com-
     * mand, it may do another validity check or it may send the command to
     * another method to handle.
     * put name,size - The method checks for a comma and if it finds one, it
     *          will pass cmd to {@link #putCommand(String)}. It will return
     *          false if it cannot add the file or if there was some other error
     *          (such as not indicating a proper size).
     * del name - It will check to see if the cmd's length exceeds 4 (since
     *          "del " is four characters long) and if it does, it will send the
     *          cmd to {@link #delCommand(String)}. It will return false if it
     *          was for whatever reason unable to remove the specified file.
     * bitmap - It will print the {@link #bitmap} and return true.
     * inodes - It will print the inodes using {@link #printINodes()} and return
     *          true.
     * 
     * @param cmd - The command provided by the user.
     * @return - True or false depending on if the command was executed success-
     *          fully.
     */
    private boolean processCommand(String cmd)
    {
        // this is so that it's not case-sensitive
        String lowerCommand = cmd.toLowerCase();
        
        if (lowerCommand.startsWith("put "))
        {
            if (cmd.contains(","))
            {
                return putCommand(cmd);
            }
        }
        
        if (lowerCommand.startsWith("del "))
        {
            if (cmd.length() > 4)
            {
                return delCommand(cmd);
            }
        }
        
        if (cmd.equalsIgnoreCase("bitmap"))
        {
            bitmap.print();
            return true;
        }
        
        if (cmd.equalsIgnoreCase("inodes"))
        {
            printINodes();
            return true;
        }
        
        // If it hits this, something was wrong with the cmd
        System.out.println(cmd + " is not a valid command.");
        return false;
    }
    
    /**
     * This method is used for adding a new file to the system. It's called by
     * {@link #processCommand(String)} only. To add a new file to the system,
     * this method first parses the name and the size of the file. Only if the
     * size is greater than zero will it begin attempting to add the file. It 
     * then checks with {@link #iNodesList} to see if a name of the same file
     * already exists in the file system. No duplicates allowed! If one with the
     * same name already exists, it returns false. Otherwise, it checks to see
     * if the {@link #bitmap} has enough blocks available. If it does, it will
     * finally add to the {@link #iNodesList} and accordingly adjust the
     * {@link #fileAllocationTable} using {@link #fillFAT(int)}. If there are
     * not enough blocks available, it will return false.
     * 
     * @param cmd - "put name,size" which is originally input by the user.
     * @return - It will return true only if the file was added successfully.
     *          It must pass these conditions: (1) the input does not cause a
     *          number format exception; (2) the specified size is greater than
     *          zero; (3) the {@link #iNodesList} does not already have a file
     *          of the same name; and (4) the {@link #bitmap} has enough blocks
     *          available.
     */
    private boolean putCommand(String cmd)
    {
        try {
            int commaIndex = cmd.indexOf(',');
            // grabbing the file name from the input
            String name = cmd.substring(4, commaIndex);
            // grabbing the size of the file from the input
            int size = Integer.parseInt(cmd.substring(commaIndex + 1).trim());
            
            // can't have a negative or 0 size!
            if (size > 0)
            {
                // check if a file with the same name already exists
                if (iNodesList.contains(name))
                {
                    System.out.println("File of name \'" + name
                                    + "\' already exists!");
                    return false;
                }
                // file to add is not already in the system
                if (bitmap.hasAvailable(size))
                {
                    return iNodesList.add(name, fillFAT(size), size);
                }
                else
                {
                    System.out.println(
                            " There are not enough blocks available.");
                    return false;
                }
            }
        } catch (NumberFormatException nfe)
        {
            System.out.println("The size of a file must be an integer.");
        }
        return false;
    }
    
    /**
     * This method modifies the {@link #fileAllocationTable} and is called only
     * by {@link #putCommand(String)} when putting a new file into the system.
     * It must call {@link #Bitmap}'s add() to find the index at which the free
     * block for allocation is for as many blocks that the file needs. The
     * {@link #fileAllocationTable} is adjusted according to the free index that
     * is returned. Once the first index (which is stored into the local
     * variable startIndex) has been established, the currentIndex will point to
     * the next index. If a file is at its last block, it must indicate that it
     * points to nothing else by using a -1.
     * 
     * @param blocks - This is the size of the new file being added, which is 
     *          indicated by the user.
     * @return It returns the startIndex (which is the first available block in
     *          the bitmap).
     */
    private int fillFAT(int blocks)
    {
        // We have to start somewhere
        boolean first = false;
        int startIndex = -1;
        // since the startIndex must be saved in the inode, we have to use
        // another index to track the current (and another for the next)
        int currentIndex = -1;
        
        for (int i = 0; i < blocks; ++i)
        {
            // allocating blocks one at a time
            int index = bitmap.add();
            if (!(first))
            {
                // the first must be saved specially
                startIndex = index;
                first = true;
                currentIndex = startIndex;
            }
            else
            {
                // the currentIndex is actually sort of more like the
                // "previously-allocated" index...
                fileAllocationTable[currentIndex] = index;
                currentIndex = index;
            }
            
            if (i == blocks - 1)
            {
                // it's the last block to add, set FAT[index] to -1
                fileAllocationTable[index] = -1;
            }
        }
        return startIndex;
    }
    
    /**
     * This method removes a file from the file system. The name is provided by
     * user input in {@link #run()} and this method is called by the
     * {@link #processCommand(String)}. The startIndex is acquired from the
     * {@link #iNodesList} when calling the remove(String) method. If the
     * specified file does not exist, the startIndex will have been returned -1
     * by the remove(String) method. Otherwise, we loop through the
     * {@link #fileAllocationTable} so we can grab the indices of the
     * {@link #bitmap} that must be switched to empty (0).
     * @param cmd - "del name"
     * @return - If we can successfully delete the inode and reset the corre-
     *          sponding bitmap bits to 0, we return true. Otherwise, false.
     */
    private boolean delCommand(String cmd)
    {
        String name = cmd.substring(4);
        // search for corresponding file name, get its startIndex if it exists
        int startIndex = iNodesList.remove(name);
        if (startIndex != -1)
        {
            // the file exists
            int index = startIndex;
            while (fileAllocationTable[index] != -1)
            {
                // adjust the bitmap indices
                bitmap.remove(index);
                // and keep going until we reach -1
                index = fileAllocationTable[index];
            }
            bitmap.remove(index);
            
            return true;
        }
        // returned -1, meaning the file does not exist!
        return false;
    }
    
    /**
     * This method prints all of the i-nodes in {@link #iNodesList} and the
     * pointers from the {@link #fileAllocationTable}. It is called in
     * {@link #processCommand(String)} when the user inputs "inodes". It
     * traverses through the nodes of the {@link #iNodesList}, grabbing the 
     * starting index and then using the starting index to move through the
     * {@link #fileAllocationTable}.
     */
    private void printINodes()
    {
        // start with the head
        INode current = iNodesList.getHead();
        if (current == null)
        {
            // if there is no head, that means there are no inodes!
            System.out.println(" There are no inodes.");
        }
        else
        {
            // we keep moving through the inodes list until there are no
            // more to process
            while (current != null)
            {
                String nextPrint = "";
                // "name: "
                nextPrint += " " + current.getFileName() + ": ";
                
                int index = current.getStartIndex();
                // "name: startIndex"
                nextPrint += index;
                for (int i = 0; i < current.getSize(); ++i)
                {
                    if (fileAllocationTable[index] != -1)
                    {
                        // "name: startIndex -> nextIndex"
                        nextPrint += " -> " + fileAllocationTable[index];
                        
                        index = fileAllocationTable[index];
                    }
                    
                    // Just so the output is a little more uniform
                    if ((i+1) % 9 == 0
                            && (i + 2) < current.getSize())
                    {
                        nextPrint += "\n   ";
                    }
                }
                System.out.println(nextPrint);
                current = current.getNext();
            }
        }
    }
}