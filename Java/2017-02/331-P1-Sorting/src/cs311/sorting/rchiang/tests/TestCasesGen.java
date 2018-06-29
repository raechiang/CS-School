package cs311.sorting.rchiang.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCasesGen
{
    private int size;
    private List<Integer> unsorted;
    
    public TestCasesGen()
    {
        unsorted = new ArrayList<>();
        size = 32;
    }
    
    public TestCasesGen(int s)
    {
        unsorted = new ArrayList<>();
        size = s;
    }
    
    // Special Case 1. Already Sorted
    public void genSortedList()
    {
        for (int i = 0; i < size; ++i)
        {
            unsorted.add(i);
        }
    }
    
    // Special Case 2. All Identical
    public void genIdenticalList()
    {
        Random rng = new Random(System.currentTimeMillis());
        int someNumber = rng.nextInt();
        for (int i = 0; i < size; ++i)
        {
            unsorted.add(someNumber);
        }
    }
    
    // Special Case 3. Alternating Greater/Less
    public void genAltList()
    {
        Random rng = new Random(System.currentTimeMillis());
        for (int i = 0; i < size; ++i)
        {
            if (i%2 == 0)
            {
                // even indices will always be positive values
                unsorted.add(Math.abs(rng.nextInt()));
            }
            else
            {
                // odd indices will always be negative values
                unsorted.add(Math.abs(rng.nextInt()) * -1);
            }
        }
    }
    
    // Special Case 4. Sorted in Reverse
    public void genRevSortedList()
    {
        for (int i = size - 1; i >= 0; --i)
        {
            unsorted.add(i);
        }
    }
    
    public void genRandomTestList()
    {
        // Test identical lists from size 32(=2^5)
        Random rng = new Random(System.currentTimeMillis());

        for (int i = 0; i < size; ++i)
        {
            unsorted.add(rng.nextInt());
        }
    }
    
    public List<Integer> getCopyOfList()
    {
        List<Integer> copy = new ArrayList<>();
        copy.addAll(unsorted);
        return copy;
    }
    
    public List<Integer> getList()
    {
        return unsorted;
    }
    
    public void changeSize(int n)
    {
        size = n;
    }
    
    public void clearList()
    {
        unsorted.clear();
    }
}