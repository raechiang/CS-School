/**
 * Rachel Chiang
 * CS 431-02
 * Project 4: File Allocation Table Simulation
 * 
 * This project simulates a file system with 64 blocks. It requires three data
 * structures for the allocation table (an integer array), the i-nodes (a list),
 * and the bitmap (a long). The user is able to input one of four commands to
 * modify or read about the system: (1) put name,size; (2) del name; (3) bitmap;
 * and (4) inodes. 
 */
public class FileSystem
{
    /**
     * This method simply begins the program.
     */
    public static void main(String args[])
    {
        FileAllocationManager fam = new FileAllocationManager();
        fam.run();
    }
}
