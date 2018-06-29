package cs311.sorting.rchiang.tests;

import java.util.List;

import cs331.sorting.rchiang.InsertionSort;
import cs331.sorting.rchiang.MergeInsertionSort;
import cs331.sorting.rchiang.MergeSort;
import cs331.sorting.rchiang.QuickSort;
import cs331.sorting.rchiang.QuickSortRandom;
import cs331.sorting.rchiang.Sort;

public class Tests2
{    
    public double testInsertionSort(List<Integer> unsorted)
    {
        Sort<Integer> insertionSort = new InsertionSort<>();
        
        return sortTest(unsorted, insertionSort);
    }
    
    public double testMergeSort(List<Integer> unsorted)
    {
        Sort<Integer> mergeSort = new MergeSort<>();
        
        return sortTest(unsorted, mergeSort);
    }
    
    public double testMergeInsertionSort(List<Integer> unsorted)
    {
        Sort<Integer> mergeInsertion = new MergeInsertionSort<>();
        
        return sortTest(unsorted, mergeInsertion);
    }
    
    public double testQuickSort(List<Integer> unsorted)
    {
        Sort<Integer> QuickSort = new QuickSort<>();
        
        return sortTest(unsorted, QuickSort);
    }
    
    public double testRandQuickSort(List<Integer> unsorted)
    {
        Sort<Integer> RandQuickSort = new QuickSortRandom<>();
        
        return sortTest(unsorted, RandQuickSort);
    }
    
    public <T extends S, S extends Comparable<T>> double sortTest(List<T> initialList, Sort<T> s)
    {
        s.init(initialList);
        double startTime = System.nanoTime();
        s.getSortedList();
        double endTime = System.nanoTime();
        return (endTime - startTime);
    }
}
