package cs311.sorting.rchiang.tests;

import java.util.List;

public class TestDriver
{
    // 2^17
    private static final int SIZE = 131072;
    
    public void testGeneral()
    {
        SortTest test = new SortTest();
        
     //   test.testInsertionSort();
        
        test.testMergeSort();
        
     //   test.testMergeInsertionSort();
        
     //   test.testQuickSort();
    }
    
    public void testInsertion()
    {
        System.out.println("Insertion Sort");
        insertionRand();
        insertionAlt();
        insertionSame();
        insertionOrd();
        insertionRevOrd();
    }
    
    private void insertionAlt()
    {
        System.out.println(" Alternating Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genAltList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testInsertionSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void insertionSame()
    {
        System.out.println(" Identical Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genIdenticalList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testInsertionSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void insertionRand()
    {
        System.out.println(" Random Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRandomTestList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testInsertionSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void insertionRevOrd()
    {
        System.out.println(" Reverse Ordered Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRevSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testInsertionSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void insertionOrd()
    {
        System.out.println(" Ordered Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testInsertionSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    public void testMerge()
    {
        System.out.println("Merge Sort");
        mergeRand();
        mergeSame();
        mergeAlt();
        mergeOrd();
        mergeRevOrd();
    }
    
    private void mergeAlt()
    {
        System.out.println(" Alternating Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genAltList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testMergeSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void mergeSame()
    {
        System.out.println(" Identical Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genIdenticalList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testMergeSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void mergeRand()
    {
        System.out.println(" Random Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRandomTestList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testMergeSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void mergeRevOrd()
    {
        System.out.println(" Reverse Ordered Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRevSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testMergeSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void mergeOrd()
    {
        System.out.println(" Ordered Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testMergeSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    public void testFQuick()
    {
        System.out.println("Quick (First) Sort");
        quickFRand();
        quickFSame();
        quickFAlt();
        quickFOrd();
        quickFRevOrd();
    }
    
    private void quickFAlt()
    {
        System.out.println(" Alternating Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genAltList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void quickFSame()
    {
        System.out.println(" Identical Elements");
        // Max is 65536
        for (int size = 32; size <= SIZE/2; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genIdenticalList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void quickFRand()
    {
        System.out.println(" Random Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRandomTestList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void quickFRevOrd()
    {
        System.out.println(" Reverse Ordered Elements");
        for (int size = 32; size <= SIZE/2; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRevSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void quickFOrd()
    {
        System.out.println(" Ordered Elements");
        for (int size = 32; size <= SIZE/2; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    public void testRQuick()
    {
        System.out.println("Quick (Rand) Sort");
        quickRRand();
        quickRSame();
        quickRAlt();
        quickROrd();
        quickRRevOrd();
    }
    
    private void quickRAlt()
    {
        System.out.println(" Alternating Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genAltList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testRandQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void quickRSame()
    {
        System.out.println(" Identical Elements");
        // Max is 65536
        for (int size = 32; size <= SIZE/2; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genIdenticalList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testRandQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void quickRRand()
    {
        System.out.println(" Random Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRandomTestList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testRandQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void quickRRevOrd()
    {
        System.out.println(" Reverse Ordered Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRevSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testRandQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    private void quickROrd()
    {
        System.out.println(" Ordered Elements");
        for (int size = 32; size <= SIZE; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testRandQuickSort(listGen.getCopyOfList());
            }
            System.out.println("  SIZE: " + size + "\t" + "AVERAGE: " + avg/10.0);
        }
    }
    
    public void testMergeInsertion()
    {
        System.out.println("Merge Insertion Sort");
        merInOrd();
        merInSame();
        merInRand();
    }
    
    private void merInRand()
    {
        System.out.println(" Random Elements");
        for (int size = 32; size <= SIZE/2; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genRandomTestList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testMergeInsertionSort(listGen.getCopyOfList());
            }
            System.out.println("  " + size + "," + avg/10.0);
        }
    }
    
    private void merInSame()
    {
        System.out.println(" Identical Elements");
        for (int size = 32; size <= SIZE/2; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genIdenticalList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testMergeInsertionSort(listGen.getCopyOfList());
            }
            System.out.println("  " + size + "," + avg/10.0);
        }
    }
    
    private void merInOrd()
    {
        System.out.println(" Ordered Elements");
        for (int size = 32; size <= SIZE/2; size *= 2)
        {
            Tests2 specialTests = new Tests2();
            
            TestCasesGen listGen = new TestCasesGen(size);
            listGen.genSortedList();
            
            double avg = 0;
            for (int i = 0; i < 10; ++i)
            {
                avg += specialTests.testMergeInsertionSort(listGen.getCopyOfList());
            }
            System.out.println("  " + size + "," + avg/10.0);
        }
    }
}