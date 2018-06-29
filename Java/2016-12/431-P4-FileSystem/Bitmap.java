/**
 * Rachel Chiang
 * CS 431-02
 * Project 4: File Allocation Table Simulation
 */

/**
 * This class contains the bitmap (a long) and operations on the bitmap. The
 * bitmap tracks empty and allocated blocks in the file system. 0 indicates a 
 * free block and 1 indicates an allocated block. 
 */
public class Bitmap
{
    /**
     * This is the bitmap long. It is instantiated to be 0 in the constructor
     * {@link #Bitmap()} and can be modified in {@link #add()} and
     * {@link #remove(int)}.
     */
    private long bitmap;
    
    /**
     * The constructor.
     */
    public Bitmap()
    {
        bitmap = 0x0L;
    }
    
    /**
     * This method checks to see if there are enough blocks available in the
     * {@link #bitmap} for when you are putting files into the file system.
     * Its main operations require (1) creating a remaining available blocks
     * long; (2) ANDing the last bit of a remaining available long bitmap with 
     * 1; and (3) shifting right. 
     * @param blocks - The number of blocks of the new file that you are
     *          attempting to add.
     * @return A boolean, true if there is enough space or false if there is not
     */
    public boolean hasAvailable(int blocks)
    {
        long max = 0xFFFFFFFFFFFFFFFFL;
        int available = 0;
        // By XORing bitmap and what a completely-full bitmap would be, the
        // result's 1 values would represent an available index
        long result = bitmap ^ max;
        
        for (int i = 0; i < 64; ++i)
        {
            // By ANDing the available bitmap with 0x1, we can see if the last
            // bit is open for allocation. If the result is not zero, then it is
            // available.
            if ((result & 0x1) != 0x0)
            {
                ++available;
            }
            
            // If there are enough spots, regardless of whether there are more
            // than enough, we return true
            if (available == blocks)
            {
                return true;
            }
            // We shift the bitmap of available spots by one to the right so
            // that the first if check (the &!=0) works
            result = (result >> 1);
        }
        
        // after going through the whole list, we've found that there are not
        // enough available blocks, so we return false.
        return false;
    }
    
    /**
     * This method marks an index as allocated for one block of a file when it
     * is being added to the file system. It must find the empty block before
     * it can add, and thusly a value representing the new block being allocated
     * "allocate" is shifted left until it finds an open space. The index is
     * noted as the bit is shifted. Finally, the {@link #bitmap} is modified and
     * the index of the modified block is returned.
     * @return index - This integer is the index of the bit at which the file
     *          was allocated. It must be returned because it must be noted
     *          in the file allocation table, which resides in  different class.
     */
    public int add()
    {
        // allocate represents the bit we are going to switch from 0 to 1 in the
        // bitmap and index is the corresponding index of the bit
        long allocate = 0x1L;
        int index = 0;
        
        // allocate and index keep increasing until it finds an empty space
        while ((bitmap - (bitmap ^ allocate)) == allocate
                && index < 63)
        {
            allocate = (allocate << 1);
            ++index;
        }
        
        // bitmap is ORed with allocate to fill the unallocated block
        bitmap |= allocate;
        return index;
    }
    
    /**
     * This method deallocates a block from the {@link #bitmap}.
     * @param index - The index of the block to be deallocated.
     */
    public void remove(int index)
    {
        long indexHex = (0x1L << index);
        bitmap = bitmap - indexHex;
    }
    
    /**
     * This method prints the {@link #bitmap} as an 8x8 square of bits with each
     * line labeled by the starting block number.
     */
    public void print()
    {
        // We can't simply print the bits since the order of the bits is most
        // significant to least, so first the bitmap is converted into an array
        // of characters (0's and 1's)
        char[] binChars = Long.toBinaryString(bitmap).toCharArray();
        int binIndex = binChars.length - 1;
        
        for (int counter = 0; counter < 64; ++counter)
        {
            // This is for printing the first index of the 8 blocks in a row
            if (counter % 8 == 0)
            {
                if (counter != 0)
                {
                    System.out.println();
                }
                System.out.printf("%2d ", counter);
            }
            
            // This is for printing the bits themselves
            if (binIndex >= 0)
            {
                System.out.print(binChars[binIndex]);
                --binIndex;
            }
            else
            {
                // The upper (more significant) bits need to be explicitly
                // printed as 0 because the Long.toBinaryString doesn't store
                // the upper zeros.
                System.out.print("0");
            }
        }
        System.out.println();
    }
}
