package cs311.sorting.rchiang.tests;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cs331.sorting.rchiang.InsertionSort;
import cs331.sorting.rchiang.MergeInsertionSort;
import cs331.sorting.rchiang.MergeSort;
import cs331.sorting.rchiang.QuickSort;
import cs331.sorting.rchiang.QuickSortRandom;
import cs331.sorting.rchiang.Sort;


public class SortTest
{
    private static final int SIZE =  (int)Math.pow(2, 16);
    
    public void testInsertionSort()
    {
        System.out.println("INSERTION SORT");
        
        List<Integer> unsorted = genUnsortedList(SIZE);
        
        Sort<Integer> insertionSort = new InsertionSort<>();
        
        sortTest(unsorted, insertionSort);
    }
    
    public void testMergeSort()
    {
        System.out.println("MERGE SORT");
        
        List<Integer> unsorted = genUnsortedList(SIZE);
        
        Sort<Integer> mergeSort = new MergeSort<>();
        
        sortTest(unsorted, mergeSort);
    }
    
    public void testQuickSort()
    {
        System.out.println("FIRST QUICK SORT");
        List<Integer> unsorted = genUnsortedList(SIZE);
        
        Sort<Integer> QuickSort = new QuickSort<>();
        
        sortTest(unsorted, QuickSort);
        
        System.out.println("RANDOM QUICK SORT");
        List<Integer> unsorted2 = genUnsortedList(SIZE);
        
        Sort<Integer> RandQuickSort = new QuickSortRandom<>();
        
        sortTest(unsorted2, RandQuickSort);
    }

    public void testMergeInsertionSort()
    {
        System.out.println("MERGE INSERTION SORT");
        List<Integer> unsorted = genUnsortedList(SIZE);
        
        Sort<Integer> mergeInsertionSort = new MergeInsertionSort<>();
        
        sortTest(unsorted, mergeInsertionSort);
    }
    
    public <T extends S, S extends Comparable<T>> void sortTest(List<T> unsorted, Sort<T> s) {
        s.init(unsorted);

        List<T> sorted = s.getSortedList();

        // Does the sorted list contain every element in the unsorted list?
        if (sorted.containsAll(unsorted))
        {
            System.out.println("  The sorted list contains every element of the unsorted list.");
        }
        else
        {
            System.out.println("  Elements are missing.");
        }
        
        // Is the list actually sorted?
        if (isListSorted(sorted))
        {
            System.out.println("  The list is sorted!");
        }
        else
        {
            System.out.println("  The list is unsorted.");
        }
        
        System.out.println();
    }

    private static <T extends S, S extends Comparable<T>> boolean isListSorted(List<T> list) {
        Iterator<T> i = list.iterator();
        T prev = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (next.compareTo(prev) < 0)
            {
                System.out.println("Found offending values: " + next + " and " + prev);
                return false;
            }
            prev = next;
        }
        return true;
    }

    private static List<Integer> genUnsortedList(int n) {
        List<Integer> unsorted = new ArrayList<>(n);

        Random gen = new Random(System.currentTimeMillis());

        for (int i = 0; i < n; i++)
            unsorted.add(gen.nextInt());

        return unsorted;
    }
}