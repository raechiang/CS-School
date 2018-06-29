package cs311.sorting.rchiang.tests;

import java.util.List;

import cs331.sorting.rchiang.InsertionSort;
import cs331.sorting.rchiang.MergeSort;
import cs331.sorting.rchiang.QuickSort;
import cs331.sorting.rchiang.QuickSortGeneral;
import cs331.sorting.rchiang.QuickSortRandom;
import cs331.sorting.rchiang.Sort;

public class Tests
{
    private static final int ITERATIONS = 8;
    
    private double[] randAvg;
    private double[] sortAvg;
    private double[] sameAvg;
    private double[] alteAvg;
    private double[] revSortAvg;
    
    public Tests()
    {
        randAvg = new double[4];
        sortAvg = new double[4];
        sameAvg = new double[4];
        alteAvg = new double[4];
        revSortAvg = new double[4];
        for (int i = 0; i < 4; ++i)
        {
            randAvg[i] = 0;
            sortAvg[i] = 0;
            sameAvg[i] = 0;
            alteAvg[i] = 0;
            revSortAvg[i] = 0;
        }
    }
    
  /*  public void runMeanTests()
    {
        List<Sort<Integer>> sortingAlgorithms = new ArrayList<>();
        sortingAlgorithms.add(new InsertionSort<Integer>());
        sortingAlgorithms.add(new MergeSort<Integer>());
        sortingAlgorithms.add(new QuickSort<Integer>());
        sortingAlgorithms.add(new QuickSortRandom<Integer>());
        
        for (int ai = 0; ai < sortingAlgorithms.size(); ++ai)
        {
            System.out.println("Testing "
                    + sortingAlgorithms.get(ai).getClass().getName());
            for (int sizeStep = 32; sizeStep <= 131072; sizeStep *= 2)
            {
                System.out.println("  Current Size: " + sizeStep);
                TestCases tester = new TestCases(sizeStep);
                double randAvg = 0;
                double sortAvg = 0;
                double sameAvg = 0;
                double alteAvg = 0;
                double revSortAvg = 0;
                for (int i = 0; i < ITERATIONS; ++i)
                {
                    tester.genRandomTestList();
                    randAvg += testSortTime(sortingAlgorithms.get(ai), tester.getList());
                    tester.clearList();
                    
                    tester.genSortedList();
                    sortAvg += testSortTime(sortingAlgorithms.get(ai), tester.getList());
                    tester.clearList();
                    
                    tester.genIdenticalList();
                    sameAvg += testSortTime(sortingAlgorithms.get(ai), tester.getList());
                    tester.clearList();
                    
                    tester.genAltList();
                    alteAvg += testSortTime(sortingAlgorithms.get(ai), tester.getList());
                    tester.clearList();
                    
                    tester.genRevSortedList();
                    revSortAvg += testSortTime(sortingAlgorithms.get(ai), tester.getList());
                    tester.clearList();
                }
                System.out.println("    Randomly Generated Elements:\n\t" + randAvg/ITERATIONS);
                System.out.println("    Ordered Elements:\n\t" + sortAvg/ITERATIONS);
                System.out.println("    Same Elements:\n\t" + sameAvg/ITERATIONS);
                System.out.println("    Alternating Elements:\n\t" + alteAvg/ITERATIONS);
                System.out.println("    Reverse Ordered Elements:\n\t" + revSortAvg/ITERATIONS);
            }
        }
    }*/
    
    public void run()
    {
        runMeanTests(new InsertionSort<Integer>());
        runMeanTests(new MergeSort<Integer>());
        runMeanTests(new QuickSort<Integer>());
        runMeanTests(new QuickSortRandom<Integer>());
    }
    
    private void runMeanTests(Sort<Integer> sortAlgorithm)
    {
        System.out.println("Testing "
                + sortAlgorithm.getClass().getName());
        TestCasesGen tester = new TestCasesGen();
        for (int sizeStep = 32; sizeStep <= 131072; sizeStep *= 2)
        {
            System.out.println("  Current Size: " + sizeStep);
            tester.changeSize(sizeStep);
            double randAvg = 0;
            double sortAvg = 0;
            double sameAvg = 0;
            double alteAvg = 0;
            double revSortAvg = 0;
            System.out.print("    ");
            
            if (sortAlgorithm instanceof QuickSortGeneral && sizeStep >= 1024)
            {
                // Can't do
            }
            else
            {
                for (int i = 0; i < ITERATIONS; ++i)
                {
                    System.out.print(".");
                    tester.genRandomTestList();
                    randAvg += testSortTime(sortAlgorithm, tester.getList());
                    tester.clearList();

                    tester.genSortedList();
                    sortAvg += testSortTime(sortAlgorithm, tester.getList());
                    tester.clearList();

                    tester.genIdenticalList();
                    sameAvg += testSortTime(sortAlgorithm, tester.getList());
                    tester.clearList();

                    tester.genAltList();
                    alteAvg += testSortTime(sortAlgorithm, tester.getList());
                    tester.clearList();

                    tester.genRevSortedList();
                    revSortAvg += testSortTime(sortAlgorithm, tester.getList());
                    tester.clearList();
                }
                System.out.println("\n    Randomly Generated Elements:\n\t" + randAvg
                        / ITERATIONS);
                System.out.println("    Ordered Elements:\n\t" + sortAvg
                        / ITERATIONS);
                System.out.println("    Same Elements:\n\t" + sameAvg / ITERATIONS);
                System.out.println("    Alternating Elements:\n\t" + alteAvg
                        / ITERATIONS);
                System.out.println("    Reverse Ordered Elements:\n\t" + revSortAvg
                        / ITERATIONS);
            }
        }
    }
    
    public void runTests()
    {
        testRandomized();
        testOrdered();
        testIdentical();
        testAlternating();
        testRevOrdered();
    }
    
    private void testRandomized()
    {
        // Tests random identical sets of given size, up to 2^17
        TestCasesGen tester = new TestCasesGen();
        for (int i = 32; i <= 131072; i *= 2)
        {
            System.out.println("\n\nMode: Identical Random Test Lists"
                    + " of Size " + i);
            tester.changeSize(i);
            tester.genRandomTestList();
            
            double sum;
            
      //      System.out.println("\nINSERTION SORT");
            sum = testSort(new InsertionSort<Integer>(), tester.getCopyOfList());
            randAvg[0] += sum;
            
      //      System.out.println("\nMERGE SORT");
            sum = testSort(new MergeSort<Integer>(), tester.getCopyOfList());
            randAvg[1] += sum;
            
       //     System.out.println("\nQUICK SORT (FIRST)");
            sum = testSort(new QuickSort<Integer>(), tester.getCopyOfList());
            randAvg[2] += sum;
            
       //     System.out.println("\nQUICK SORT (RAND)");
            sum = testSort(new QuickSortRandom<Integer>(), tester.getCopyOfList());
            randAvg[3] += sum;
            
            tester.clearList();
        }
    }
    
    private void testOrdered()
    {
        TestCasesGen tester = new TestCasesGen();
        // Tests up to 2^17
        for (int i = 32; i <= 131072; i *= 2)
        {
            System.out.println("\n\nMode: Ordered Test Lists"
                    + " of Size " + i);
            tester.changeSize(i);
            tester.genSortedList();
            
            double sum;
            
        //    System.out.println("\nINSERTION SORT");
            sum = testSort(new InsertionSort<Integer>(), tester.getCopyOfList());
            sortAvg[0] += sum;
            
       //     System.out.println("\nMERGE SORT");
            sum = testSort(new MergeSort<Integer>(), tester.getCopyOfList());
            sortAvg[1] += sum;
            
            if (i <= 8192)
            {
      //          System.out.println("\nQUICK SORT (FIRST)");
                sum = testSort(new QuickSort<Integer>(), tester.getCopyOfList());
                sortAvg[2] += sum;
            }
            
      //      System.out.println("\nQUICK SORT (RAND)");
            sum = testSort(new QuickSortRandom<Integer>(), tester.getCopyOfList());
            sortAvg[3] += sum;
            
            tester.clearList();
        }
    }
    
    private void testIdentical()
    {
        TestCasesGen tester = new TestCasesGen();
        for (int i = 32; i <= 131072; i *= 2)
        {
            System.out.println("\n\nMode: Identical Elements List"
                    + " of Size " + i);
            tester.changeSize(i);
            tester.genIdenticalList();
            
      //      System.out.println("\nINSERTION SORT");
            testSort(new InsertionSort<Integer>(), tester.getCopyOfList());
            
      //      System.out.println("\nMERGE SORT");
            testSort(new MergeSort<Integer>(), tester.getCopyOfList());
            
            if (i <= 8192)
            {
      //          System.out.println("\nQUICK SORT (FIRST)");
                testSort(new QuickSort<Integer>(), tester.getCopyOfList());
                
      //          System.out.println("\nQUICK SORT (RAND)");
                testSort(new QuickSortRandom<Integer>(), tester.getCopyOfList());
            }
            
            tester.clearList();
        }
    }
    
    private void testAlternating()
    {
        TestCasesGen tester = new TestCasesGen();
        for (int i = 32; i <= 131072; i *= 2)
        {
            System.out.println("\n\nMode: Alternating Elements Lists"
                    + " of Size " + i);
            tester.changeSize(i);
            tester.genAltList();
            
    //        System.out.println("\nINSERTION SORT");
            testSort(new InsertionSort<Integer>(), tester.getCopyOfList());
            
    //        System.out.println("\nMERGE SORT");
            testSort(new MergeSort<Integer>(), tester.getCopyOfList());
            
     //       System.out.println("\nQUICK SORT (FIRST)");
            testSort(new QuickSort<Integer>(), tester.getCopyOfList());
            
     //       System.out.println("\nQUICK SORT (RAND)");
            testSort(new QuickSortRandom<Integer>(), tester.getCopyOfList());
            
            tester.clearList();
        }
    }
    
    private void testRevOrdered()
    {
        TestCasesGen tester = new TestCasesGen();

        for (int i = 32; i <= 131072; i *= 2)
        {
            System.out.println("\n\nMode: Reverse Ordered Test Lists"
                    + " of Size " + i);
            
            tester.changeSize(i);
            tester.genRevSortedList();
            
    //        System.out.println("\nINSERTION SORT");
            testSort(new InsertionSort<Integer>(), tester.getCopyOfList());
            
     //       System.out.println("\nMERGE SORT");
            testSort(new MergeSort<Integer>(), tester.getCopyOfList());
            
            if (i <= 8192)
            {
   //             System.out.println("\nQUICK SORT (FIRST)");
                testSort(new QuickSort<Integer>(), tester.getCopyOfList());
            }
            
     //       System.out.println("\nQUICK SORT (RAND)");
            testSort(new QuickSortRandom<Integer>(), tester.getCopyOfList());
            
            tester.clearList();
        }
    }
    
    private <T extends S, S extends Comparable<T>> double testSort(Sort<T> s, List<T> initialList)
    {
        // Test
        //  init
        //  begin, and take time
        s.init(initialList);
        long startTime = System.nanoTime();
        s.getSortedList();
        long endTime = System.nanoTime();
  //      System.out.println("Time elapsed: "+ (endTime - startTime) + " ns");
        return (endTime - startTime);
        
    }
    
    private <T extends S, S extends Comparable<T>> double testSortTime(Sort<T> s, List<T> initialList)
    {
        // Test
        //  init
        //  begin, and take time
        s.init(initialList);
        double startTime = System.nanoTime();
        s.getSortedList();
        double endTime = System.nanoTime();
        return (endTime - startTime);
    }
}
