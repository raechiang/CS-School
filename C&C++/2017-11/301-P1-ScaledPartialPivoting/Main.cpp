/*
Rachel Chiang
CS 301.01
2017-10-30
Project 1
Scaled Partial Pivoting for Gaussian Elimination
--------------------------------------------------------------------------------
This program begins by requesting the user to input an integer [0,3], which
correspond to the following actions:
 (0) Run tests on varying sizes
     Selecting 0 will calculate solutions for systems of n equations, where 
     n = {2^x | 0 <= x <= 10}. For each size, it will attempt 30 iterations and
     find the average run time over these 30 iterations. Note that the matrices
     will grow very fast, so, while the whole procedure will take around two
     minutes (give or take), it will also use a moderately large amount of
     memory. l, s, and b all have the same length as the size and the matrix of
     coefficients will have size^2. Information will be outputed to a .csv file
     named "outputreport.csv" in this format: n,average time,iterations
 (1) Input equations' coefficients directly
     Selecting 1 will subsequently request for further input from the user; that
     is, it will ask the user to input a system of linear equations in the
     following form:
         Given some equations, they must be written as coefficients, separated
         by spaces with each equation separated by a new line.
         +--------------------+--------------------+
         | Equations          | As Input           |
         +--------------------+--------------------+
         | 1x + 1y + 1z = 5  -->  "1 1 1 5"        |
         | 2x + 3y + 5z = 8  -->  "2 3 5 8"        |
         | 4x      + 5z = 2  -->  "4 0 5 2"        |
         +--------------------+--------------------+
     Failure to input the proper matrix form as seen above will result in a
     restart of the outer menu loop. An uncalled for amount of spaces is
     acceptable, but please do not use unnecessary new lines.
     The program will then solve the system of equations and output the results
     as xi, with i = [0,n), if the solution exists.
 (2) Input filename for a system of linear equations
     Selecting 2 will request for further input from the user; that is, it will
     ask the user to input a file name that contains a matrix in the form
     detailed above. The program will then solve the system of equations and
     output the results as xi, with i = [0,n), if the solution exists.
 (3) Exit
     Exits the main loop.
When solving the system of equations, the program uses scaled partial pivoting
with Gaussian Elimination.
*/

#include <iostream>
#include <string>
#include <ctime>
#include <chrono>
#include <fstream>
#include <regex>

#include "SysEquations.h"

// user input
int requestMode();
// the different modes and solving
void runInputMode(); // mode 1, user inputs a system
void runFileMode(); // mode 2, user inputs a filename
void runTestsMode(); // mode 0, randomly generates cases, outputs to csv
bool solve(std::istream& is);
// utility for parsing the equations
int getVarCount(std::string equation);
// matrix creation and deletion
double** allocateMatrix(int size);
void deallocMatrix(double** m, int size);
// utility for mode 0
void populateRandCoeffMatrix(double** m, int size);
double* populateB(int size);
double getRandomDouble();
// extra fluff
void printExampleMatrix();

int main()
{
    srand(time(NULL));
    std::cout << "Welcome!" << std::endl;
    int mode = 0;
    // menu loop
    while (mode != 3)
    {
        mode = requestMode();
        if (mode == 1)
        {
            // user wants to input a system of linear equations
            runInputMode();
        }
        else if (mode == 2)
        {
            // user wants to just input a file name that has the equations
            runFileMode();
        }
        else if (mode == 3)
        {
            // user wants to exit
            std::cout << "Thank you and good-bye.\n";
        }
        else if (mode == 0)
        {
            // runs tests and outputs into csv files
            runTestsMode();
        }
        else
        {
            std::cout << "Invalid input. Please try again.\n>";
        }
    }
    return 0;
}

/*
Retrieves the user's desired mode.
*/
int requestMode()
{
    std::cout <<
        "\nPlease select one of the following operations by inputting ";
    std::cout << "the corresponding integer:\n";
    printf(" (0) %s\n (1) %s\n (2) %s\n (3) %s\n>",
        "Run tests on varying sizes",
        "Input equations' coefficients directly",
        "Input filename for a system of linear equations",
        "Exit");
    std::string cmd;
    std::getline(std::cin, cmd);
    return stoi(cmd);
}

/*
Solves a system of linear equations given by the user's console input.
*/
void runInputMode()
{
    printExampleMatrix();

    std::cout <<
        "Following the format above, please input the coefficient matrix for your system:\n";
    solve(std::cin);
}

/*
Solves a system of linear equations given by the user's file input.
*/
void runFileMode()
{
    // ask for file name that has a matrix
    printExampleMatrix();

    std::cout <<
        "Please input the name of a file that contains a matrix following the format above.\n>";

    std::string fileName;
    std::getline(std::cin, fileName);
    std::ifstream fis(fileName);
    if (fis.is_open())
    {
        solve(fis);
        
        fis.close();
    }
    else
    {
        std::cout << "Could not open file " << fileName << ".\n";
    }
}

/*
This deals with parsing and solving a system of linear equations.
*/
bool solve(std::istream& is)
{
    std::string equation;
    std::getline(is, equation);
    // loose check for invalid inputs
    int size = getVarCount(equation);
    if (size <= 0)
    {
        std::cout << "Bad input." << std::endl;
        return false;
    }
    // the number of equations used will be known based off of the first line
    double** coefficients = allocateMatrix(size);
    double* bs = new double[size];

    for (int i = 0; i < size; ++i)
    {
        if (i != 0)
        {
            std::getline(is, equation);
        }
        // loose check to ensure that the number of coefficients given matches
        if (getVarCount(equation) != size)
        {
            std::cout << "Bad input! Please try again.\n";
            return false;
            i = size;
        }
        else
        {
            std::string::size_type nextInString = 0;
            std::size_t offset = 0;
            for (int j = 0; j < size; ++j)
            {
                *(*(coefficients + j) + i)
                    = std::stod(equation.substr(offset), &nextInString);
                offset = offset + nextInString;
            }
            bs[i] = std::stod(equation.substr(offset), &nextInString);
        }
    }

    if (size != 0)
    {
        SysEquations sys = SysEquations(coefficients, bs, size);
        sys.findSolution();
    }
    else
    {
        std::cout << "Invalid input." << std::endl;
    }

    deallocMatrix(coefficients, size);
    delete[] bs;
    bs = nullptr;

    return true;
}

/*
This counts the number of values given and has a loose invalid input check.
*/
int getVarCount(std::string equation)
{
    std::string::size_type nextInString = 0;
    std::size_t offset = 0;
    int numValsGiven = 0;

    // a lenient check that looks more complicated than it is that just makes
    // sure there is at least one number given in the line
    std::regex properFullForm(
        "(((([\\s]*(((\\+|-)?[0-9]+)(\\.([0-9]+)?)?)[\\s]*))|(([\\s]*(((\\+|-)?[0-9]*)(\\.([0-9]+)))[\\s]*))))+");

    if (std::regex_match(equation, properFullForm))
    {
        while (offset + 1 < equation.length())
        {
            // offset+1 because substr attempts to cut from beyond the offset
            double val = std::stod(equation.substr(offset), &nextInString);
            offset = offset + nextInString;
            ++numValsGiven;
        }
    }
    // -1 because this is for the variable count and the vector b is included
    // in the input
    return (numValsGiven - 1);
}

/*
2D pointer arrays for the matrices.
*/
double** allocateMatrix(int size)
{
    double** mat = new double*[size];
    for (int i = 0; i < size; ++i)
    {
        mat[i] = new double[size];
    }
    return mat;
}
void deallocMatrix(double** m, int size)
{
    for (int i = 0; i < size; ++i)
    {
        delete[] m[i];
        m[i] = nullptr;
    }
    delete[] m;
    m = nullptr;
}

/*
--------------------------------------------------------------------------------
Randomly generates n^2 coefficients, with n=[2,1024] doubling in each iteration.
It will output the following information into a .csv file:
 n,average time,iterations of n
*/
void runTestsMode()
{
    // Randomly make coefficients with n = 2, to 1024
    // output the results into csv: n,average time,iterations
    // Run a minimum of 30 times per n-set
    int min = 2;
    int max = 1024;
    std::ofstream fos;
    fos.open("outputreport.csv");
    fos << "n,average time,iterations\n";

    for (int s = min; s <= max; s*=2)
    {
        double avgTime = 0;
        int iterations = 30;
        int trueIts = 0;
        for (int i = 0; i < iterations; ++i)
        {
            double** matrix = allocateMatrix(s);
            populateRandCoeffMatrix(matrix, s);
            double* b = populateB(s);
            SysEquations sys = SysEquations(matrix, b, s);
            deallocMatrix(matrix, s);
            delete[] b;
            b = nullptr;

            auto start = std::chrono::system_clock::now();
            if (sys.sppGauss())
            {
                sys.solve();
                auto end = std::chrono::system_clock::now();
                std::chrono::duration<double> elapsed_seconds = end - start;
                avgTime += elapsed_seconds.count();
                ++trueIts;
            }
        }
        avgTime = ((avgTime * 1000.0) / (double) trueIts); // in ms
        
        fos << s << "," << avgTime << "," << trueIts << "\n";
    }
    fos.close();
}

/*
Random generation of equations.
*/
void populateRandCoeffMatrix(double** m, int size)
{
    for (int i = 0; i < size; ++i)
    {
        for (int j = 0; j < size; ++j)
        {
            *(*(m + j) + i) = getRandomDouble();
        }
    }
}
double* populateB(int size)
{
    double* b = new double[size];
    for (int i = 0; i < size; ++i)
    {
        b[i] = getRandomDouble();
    }
    return b;
}
double getRandomDouble()
{
    double f = (double)rand() / RAND_MAX;
    return -20000 + f * (40000);
}

/*
Fluff to show the user how they should format their inputs.
*/
void printExampleMatrix()
{
    std::cout << "    (example)" << std::endl;
    printf("    %dx + %dy + %dz = %d", 1, 1, 1, 5);
    printf("  -->  \"%d %d %d %d\"\n", 1, 1, 1, 5);
    printf("    %dx + %dy + %dz = %d", 2, 3, 5, 8);
    printf("  -->  \"%d %d %d %d\"\n", 2, 3, 5, 8);
    printf("    %dx      + %dz = %d", 4, 5, 2);
    printf("  -->  \"%d %d %d %d\"\n", 4, 0, 5, 2);
}