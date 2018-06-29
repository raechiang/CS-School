import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Tester
{
    private SquareMatrix M1;
    private SquareMatrix M2;
    private Random rng = new Random(System.currentTimeMillis());
    
    public Tester(int size)
    {
        if (size == 0)
        {
            initBase(size);
        }
        else
        {
            initGen(size);
        }
    }
    
    private void initBase(int size)
    {
        double[][] m1 = {
                {1.0, 2.0, 3.0, 4.0, 5.0},
                {2.0, 2.0, 2.0, 2.0, 2.0},
                {3.0, 1.0, 1.0, 1.0, 3.0},
                {0.0, 0.0, 2.0, 3.0, -2.0},
                {4.0, 4.0, -4.0, 0.0, 0.0}
        };
        double[][] m2 = {
                {1.0, 0.0, 0.0, 0.0, 0.0},
                {1.0, 2.0, 1.0, 2.0, 1.0},
                {0.0, 0.0, 1.0, 0.0, 0.0},
                {1.0, 1.0, 1.0, 1.0, 1.0},
                {2.0, 2.0, -2.0, 2.0, 2.0}
        };
        M1 = new SquareMatrix(m1.length, m1);
        M2 = new SquareMatrix(m2.length, m2);
        
        run();
    }
    
    private void initGen(int size)
    {
        double[][] m1 = new double[size][size];
        double[][] m2 = new double[size][size];
        
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                m1[i][j] = genRandDoub();
                m2[i][j] = genRandDoub();
            }
        }
        M1 = new SquareMatrix(size, m1);
        M2 = new SquareMatrix(size, m2);
        
        timeTests();
    }
    
    private void run()
    {
        System.out.println("Welcome.");
        System.out.println("Matrix 1 (M1):");
        M1.write();
        System.out.println("Matrix 2 (M2):");
        M2.write();
        int command = menuSelection();
        while (command != 4)
        {
            getResult(command);
            command = menuSelection();
        }
        System.out.println("Goodbye.");
    }
    
    private int menuSelection()
    {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        int command = 0;
        while (invalidInput)
        {
            try
            {
                invalidInput = false;
                printMenu();
                command = sc.nextInt();
                if (command < 1 || command > 4)
                {
                    System.out.println("Please input an integer 1-4");
                    invalidInput = true;
                }
            } catch (InputMismatchException ime)
            {
                System.out.println("Please input an integer 1-4");
                invalidInput = true;
                sc.next();
            }
        }
        return command;
    }
    
//--------//--------//--------//--------//--------//--------//--------//--------
    private void printMenu()
    {
        System.out.println("Please select one of the following"
                + " operations by inputting the corresponding integer: ");
        System.out.println(" (1) M3 = M1 + M2\n"
                + " (2) M4 = M1 - M2\n"
                + " (3) M5 = M1 * M2\n"
                + " (4) Exit");
    }
    
    private void getResult(int command)
    {
        if (command == 1)
        {
            System.out.println("M3 = M1 + M2");
            SquareMatrix M3 = M1.addition(M2);
            M3.write();
        }
        else if (command == 2)
        {
            System.out.println("M4 = M1 - M2");
            SquareMatrix M4 = M1.subtraction(M2);
            M4.write();
        }
        else if (command == 3)
        {
            System.out.println("M5 = M1 * M2");
            SquareMatrix M5 = M1.multiplication(M2);
            M5.write();
        }
    }
    
    private double genRandDoub()
    {
        return rng.nextDouble();
    }
    
    private void timeTests()
    {
        double avg = 0;
        for (int i = 0; i < 5; ++i)
        {
            double startTime = System.nanoTime();
            M1.addition(M2);
            double endTime = System.nanoTime();
            avg += (endTime - startTime);
        }
        System.out.println("ADD AVG TIME: " + avg/5);
        
        avg = 0;
        for (int i = 0; i < 5; ++i)
        {
            double startTime = System.nanoTime();
            M1.subtraction(M2);
            double endTime = System.nanoTime();
            avg += (endTime - startTime);
        }
        System.out.println("SUB AVG TIME: " + avg/5);
        
        avg = 0;
        for (int i = 0; i < 5; ++i)
        {
            double startTime = System.nanoTime();
            M1.multiplication(M2);
            double endTime = System.nanoTime();
            avg += (endTime - startTime);
        }
        System.out.println("MUL AVG TIME: " + avg/5);
    }
}
