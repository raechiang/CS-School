/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This program attempts to solve the n-queens problem, where n=21 using the two
strategies, steepest-ascent hill climbing algorithm (HC) and genetic algorithm
(GA).

The user will be prompted to input an integer value corresponding to options in
a menu:
 (1) Test three cases with both
     This will execute the runBoth() procedure, in which both algorithms will
     be given three cases to solve. The solutions that they find will be printed
     to console.
 (2) Test many steepest-ascent hill climbing cases
     This will execute runHCTests(int). The user will provide the number of
     cases that they desire to test, and the program will generate that number
     of initial states for the steepest-ascent hill climbing algorithm to try to
     solve. Up to five optimal solutions will be recorded into a .txt file and
     statistics regarding the solving procedure will be recorded to a .csv file.
 (3) Test many genetic algorithm cases
     This will execute runGATests(int,int). The user will provide the number of
     cases that they desire to test, and the program will generate that number
     of initial populations for the genetic algorithm to try to solve. Up to
     five optimal solutions will be recorded into a .txt file and statistics
     regarding the solving procedure will be recorded to a .csv file.
 (4) Test many genetic algorithm cases with an inputted mutation probability
     This will also execute runGATests(int,int). The user will provide both the
     number of cases that they desire to test as well as their desired mutation
     probability, which must be an integer value bounded in the interval
     [0,100].
*/
#include <iostream>
#include <sstream>
#include <vector>
#include <chrono>
#include <fstream>

#include "Constants.h"
#include "RandomStateGenerator.h"
#include "HillClimbingSolver.h"
#include "GeneticAlgorithmSolver.h"
#include "Solver.h"

void runBoth();
void runHCTests(int iterations);
void runGATests(int iterations, int mutProb);

/*
This displays the menu and controls which function to execute based off of the
user's desired mode input. It handles all user input.
*/
int main()
{
    std::cout << "Welcome.\n";
    
    // Requests the mode
    int mode = 0;
    do
    {
        std::cout << "Please select a testing mode:\n";
        printf(" %s\n %s\n %s\n %s\n>", "(1) Test three cases with both",
            "(2) Test many steepest-ascent hill climbing cases",
            "(3) Test many genetic algorithm cases",
            "(4) Test many genetic algorithm cases with an inputted mutation probability");
        std::cin >> mode;
    } while (mode < 1 || mode > 4);

    if (mode == 1)
    {
        // test three cases with both
        runBoth();
    }
    else if (mode == 2 || mode == 3 || mode == 4)
    {
        // test one of the three "many" cases modes
        std::cout << "How many cases would you like to run?\n";
        int its = 0;
        while (its <= 0)
        {
            std::cout << ">";
            std::cin >> its;
        }

        if (mode == 2)
        {
            // test cases with hc
            runHCTests(its);
        }
        else
        {
            if (mode == 3)
            {
                // test cases with ga
                std::cout << "mode " << mode << std::endl;
                runGATests(its, -1);
            }
            else
            {
                // test cases with ga with set mutation probability
                std::cout << "Please provide a mutation percentage [0,100].\n";
                int mp = -1;
                while (mp < 0 || mp > 100)
                {
                    std::cout << ">";
                    std::cin >> mp;
                }
                runGATests(its, mp);
            }
        }
    }
    
    // bye-bye
    std::cout << "\nThanks. Good-bye." << std::endl;

    return 0;
}

/*
This method runs three cases each for both algorithms. It will print the
solution that the search found to the console.
*/
void runBoth()
{
    std::cout << "Executing three cases each for solving 21-queens with steepest-ascent hill climbing and genetic algorithm.\n";
    RandomStateGenerator rsg;
    
    // HC
    for (int i = 0; i < 3; ++i)
    {
        std::cout << "Steepest-Ascent Hill Climbing " << i;
        HillClimbingSolver hcSolver(rsg.getRandState());
        std::string solution = hcSolver.hillClimb();
        std::cout << Solver::boardToString(solution) << std::endl;
    }

    // GA
    for (int i = 0; i < 3; ++i)
    {
        std::cout << "Genetic Algorithm " << i;

        std::vector<std::string> population;
        for (int i = 0; i < Constants::POPULATION_SIZE; ++i)
        {
            population.push_back(rsg.getRandState());
        }
        GeneticAlgorithmSolver gaSolver(population, -1);
        std::string solution = gaSolver.geneticAlgorithm();
        std::cout << Solver::boardToString(solution) << std::endl;
    }
}

/*
Given some number of iterations, it will run that many cases using the steepest-
ascent hill climbing algorithm. It will output up to 5 optimal solutions to
sampleOutput.txt and it will output certain information gathered to stats.csv,
in the format type,searchCost,avgTime,totalOptimal,iterations. Every time it
finishes searching one case, it will print a period "." to console. Just so you
know it's still alive.
*/
void runHCTests(int iterations)
{
    printf(
        "Executing %d runs for solving 21-queens with steepest-ascent hill climbing.\n",
        iterations);

    RandomStateGenerator rsg;
    std::ofstream samplefos;
    samplefos.open("sampleOutput.txt");
    std::ofstream statsfos;
    statsfos.open("stats.csv");
    statsfos << "type,searchCost,avgTime,totalOptimal,iterations" << std::endl;

    int sampleCount = 5;
    double searchCost = 0;
    double time = 0;
    double completeOptimal = 0;

    for (int i = 0; i < iterations; ++i)
    {
        HillClimbingSolver hcSolver(rsg.getRandState());
        auto start = std::chrono::system_clock::now();
        std::string solution = hcSolver.hillClimb();
        auto end = std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed_seconds = end - start;

        time += elapsed_seconds.count();
        searchCost += hcSolver.getSearchCost();

        if (Solver::countAttackingPairs(solution) == 0)
        {
            ++completeOptimal;
            if (sampleCount > 0)
            {
                --sampleCount;
                samplefos << "\nSolution Using Hill Climbing";
                samplefos << Solver::boardToString(solution);
            }
        }
        std::cout << ".";
    }
    statsfos << "HC," << ((searchCost) / iterations) << "," << (time / iterations) << "," << completeOptimal << "," << iterations << "\n";

    samplefos.close();
    statsfos.close();
}

/*
Given some number of iterations, it will run that many cases using the genetic
algorithm. It will output up to 5 optimal solutions to sampleOutput.txt and it
will output certain information gathered to stats.csv, in the following format:
type,searchCost,avgTime,totalOptimal,iterations. Every time it finishes
searching one case, it will print a period "." to console. Just so you know it's
still alive. If the user provided mutProb, it will set the probability for
mutation to that percentage. If not, it is by default -1, which will cause the
algorithm to use a different non-constant approach of mutation.
Caution: This one may take a while. 1 full case at 4000 generations with
population size of 100 generally takes around 1 minute on my computer.
*/
void runGATests(int iterations, int mutProb)
{
    printf(
        "Executing %d runs for solving 21-queens with genetic algorithm.\n",
        iterations);

    RandomStateGenerator rsg;
    std::ofstream samplefos;
    samplefos.open("sampleOutput.txt");
    std::ofstream statsfos;
    statsfos.open("stats.csv");
    statsfos << "type,searchCost,avgTime,totalOptimal,iterations" << std::endl;

    int sampleCount = 5;
    double searchCost = 0;
    double time = 0;
    double completeOptimal = 0;

    for (int i = 0; i < iterations; ++i)
    {
        std::vector<std::string> population;
        for (int i = 0; i < Constants::POPULATION_SIZE; ++i)
        {
            population.push_back(rsg.getRandState());
        }
        
        GeneticAlgorithmSolver gaSolver(population, mutProb);

        auto start = std::chrono::system_clock::now();
        std::string solution = gaSolver.geneticAlgorithm();
        auto end = std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed_seconds = end - start;

        time += elapsed_seconds.count();
        searchCost += gaSolver.getSearchCost();

        if (Solver::countAttackingPairs(solution) == 0)
        {
            ++completeOptimal;
            if (sampleCount > 0)
            {
                --sampleCount;
                samplefos << "\nSolution Using Genetic Algorithm\n";
                samplefos << Solver::boardToString(solution);
            }
        }
        std::cout << ".";
    }

    statsfos << "GA," << ((searchCost) / iterations) << "," << (time / iterations) << "," << completeOptimal << "," << iterations << "\n";

    samplefos.close();
    statsfos.close();
}