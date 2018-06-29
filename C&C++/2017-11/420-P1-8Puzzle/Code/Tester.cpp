/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

This class controls running the tests, including calling the random puzzle
generator and handling user interaction.
*/
#include "Tester.h"

#include <stdio.h>
#include <iostream>
#include <fstream>
#include <stack>
#include <sstream>
#include <chrono>
#include <ctime>

#include "PuzzleSolver.h"
#include "Node.h"
#include "RandomPuzzleGenerator.h"
#include "PuzzleManipulator.h"

// Constructor
Tester::Tester()
    : rpg(RandomPuzzleGenerator())
{
}

/*
This namespace simply holds a bunch of constants.
*/
namespace {
    const std::string H1_STR =
        "|                        Heuristic 1: Misplaced Numbers                        |\n";
    const std::string H2_STR =
        "|                        Heuristic 2: Manhattan Distance                       |\n";
    const std::string BORDER =
        "+------------------------------------------------------------------------------+\n";

    const int DESIRED_CTRLITS = 1000;
    const int DESIRED_LONGITS = 10;
    const int DESIRED_SAMPLES = 3;
}

/*
This loops the main menu until the user inputs a 5 to exit.
*/
void Tester::run()
{
    std::cout << "Welcome!" << std::endl;
    bool wantContinue = true;

    while (wantContinue)
    {
        int mode = requestMode();
        if (mode == 2)
        {
            bool goodInput = false;
            int puzzle = 0;
            while (!(goodInput))
            {
                puzzle = requestPuzzle();
                if (PuzzleManipulator::checkPuzzleValidity(puzzle))
                {
                    goodInput = true;
                }
                else
                {
                    std::cout << "Sorry, that puzzle is not a valid puzzle."
                        << std::endl;
                }
            }
            std::cout << "User Input Mode." << std::endl;
            runSingleCaseMode(puzzle);
        }
        else if (mode == 3)
        {
            std::cout << DESIRED_CTRLITS << " Nicer Cases Mode" << std::endl;
            runCasesMode(mode);
        }
        else if (mode == 4)
        {
            std::cout << DESIRED_LONGITS << " Longer Cases Mode" << std::endl;
            runCasesMode(mode);
        }
        else if (mode == 5)
        {
            wantContinue = false;
        }
        else
        {
            runSingleCaseMode(rpg.getRandomPuzzle());
        }
    }
    std::cout << "Thank you and good-bye." << std::endl;
}

/*
This requests for the user to input an integer value in the interval [1,5] and
will return the corresponding user integer input. Note that if the user fails
to input a valid number, it will default to option (1).
*/
int Tester::requestMode()
{
    // Ask if user wants to test with
    //  (1) randomly-generated 8-puzzle
    //  (2) a single user-inputted puzzle in form 1 2 4 0 5 6 8 3 7
    //  (3) randomly-generated cases
    //  (4) random cases with large depths
    //  (5) exit
    std::cout << "Please input the number corresponding to the desired option:"
        << std::endl;
    std::cout << " (1) Solve a randomly-generated puzzle (default)"
        << std::endl;
    std::cout << " (2) Solve a user-provided puzzle" << std::endl;
    std::cout << " (3) Solve random cases with semi-distributed depths"
        << std::endl;
    std::cout << " (4) Solve random cases with large depths" << std::endl;
    std::cout << " (5) Exit" << std::endl;
    std::cout << ">";
    int mode = 1;
    std::cin >> mode;
    return mode;
}

/*
If the user selected option 2, this method will be fired off and requests the
user to enter a puzzle whose tile values are separated by spaces or an integer
value that represents the same configuration without spaces. The blank is
represented by 0.
*/
int Tester::requestPuzzle()
{
    std::cout << "Please input a puzzle, imitating one of the following forms:"
        << std::endl;
    std::cout << " Separated: 4 3 2 1 7 5 6 8 0" << std::endl;
    std::cout << " Together:  432175680" << std::endl;
    std::cout << ">";
    int puzzle = 0;
    int nextValue = 0;
    std::cin >> nextValue;
    // senses user input
    if (nextValue > 8)
    {
        return nextValue;
    }
    else
    {
        puzzle *= 10;
        puzzle += nextValue;

        for (int i = 0; i < 8; ++i)
        {
            puzzle *= 10;
            std::cin >> nextValue;
            puzzle += nextValue;
        }
        std::cout << "Puzzle recieved: " << puzzle << std::endl;
    }

    return puzzle;
}

/*
This is used to run a single test case, which means that it is called when the
user selected option 1 or 2 from the main menu. It solves the puzzle and then
prints the solution sequence in two modes. The two modes are differentiated
by the integers 1 and 2, which correspond to the heuristic function selector
values in PuzzleSolver. 1 is h1 which is the misplaced tile heuristic function,
and 2 is h2 which is the Manhattan distance heuristic function.
*/
void Tester::runSingleCaseMode(int puzzle)
{
    std::cout << BORDER << H1_STR << BORDER << std::endl;
    solveAndPrint(1, puzzle);

    std::cout << BORDER << H2_STR << BORDER << std::endl;
    solveAndPrint(2, puzzle);
}

/*
This method calls the PuzzleSolver with the given heuristic selection h and
passes the given puzzle into a new Node that represents the initial state. The
solution is thusly generated and put into a stack, in which the states can be
popped and printed. PuzzleManipulator is used to turn the states into a nicer
format than a single integer string (like 12345678).
*/
void Tester::solveAndPrint(int h, int puzzle)
{
    std::cout << "Solution for " << puzzle << std::endl;
    PuzzleSolver solver(h, Node(puzzle, puzzle, 0, 0));
    std::stack<int> solutionStack = solver.search();
    std::cout << "Nodes Generated: " << solver.getNodesGenerated() << std::endl;
    // solution for actions does not include the initial state, so subtract 1
    std::cout << "Solution Length, Excluding Initial: "
        << (solutionStack.size() - 1) << std::endl;

    while (!(solutionStack.empty()))
    {
        std::cout << PuzzleManipulator::str(solutionStack.top());
        solutionStack.pop();
    }
}

/*
This function prints 3 simple cases with solution depths d > 10 into a new file
called "sampleOutputs.txt". It will also generate two .csv files that correspond
to the used heuristics. The mode argument will dictate how many test cases will
run, since this function is used by both option 3 and 4. DESIRED_CTRLITS and
DESIRED_LONGITS dictate how many test cases will be run; the former accompanies
option 3, whereas the latter accompanies option 4. The .csv files will document
the depth (number of actions to solve the puzzle), the number of nodes generated
for each depth, and the time it took to search for the solution.
Total files generated: 3 - sampleOutputs.txt, h1Stats.csv, h2Stats.csv
*/
void Tester::runCasesMode(int mode)
{
    std::ofstream samplefos;
    samplefos.open("sampleOutputs.txt");
    std::ofstream h1fos;
    h1fos.open("h1Stats.csv");
    std::ofstream h2fos;
    h2fos.open("h2Stats.csv");
    h1fos << "depth,nodes generated,run time\n";
    h2fos << "depth,nodes generated,run time\n";

    int sampleCount = 0;
    int depth = 0;
    int nodesGenerated = 0;
    int desiredIts = 0;
    if (mode == 4)
    {
        desiredIts = DESIRED_LONGITS;
    }
    else
    {
        desiredIts = DESIRED_CTRLITS;
    }

    for (int iterations = 0; iterations < desiredIts; ++iterations)
    {
        // gets a valid random puzzle
        int puzzle = 0;
        if (mode == 4)
        {
            // mode is 4
            do
            {
                puzzle = rpg.getBigPuzzle();
            } while (!(PuzzleManipulator::checkPuzzleValidity(puzzle)));
        }
        else
        {
            // mode is 3
            puzzle = rpg.getControlledPuzzle(35);
        }
        // solves the same puzzle twice using the two strategies
        for (int hType = 1; hType < 3; ++hType)
        {
            std::cout << "Solving " << puzzle << " with h" << hType
                << std::endl;
            PuzzleSolver solver(hType, Node(puzzle, puzzle, 0, 0));

            // time the solve
            auto start = std::chrono::system_clock::now();
            std::stack<int> solutionStack = solver.search();
            auto end = std::chrono::system_clock::now();
            std::chrono::duration<double> elapsed_seconds = end - start;
            std::time_t end_time = std::chrono::system_clock::to_time_t(end);

            // doubled because there are two heuristics
            if (sampleCount < (DESIRED_SAMPLES * 2))
            {
                // print 3 samples of depth > 10 to sampleOutputs.txt
                depth = solutionStack.size();
                if (depth > 10)
                {
                    // write general information
                    if (hType == 1)
                    {
                        samplefos << BORDER << H1_STR << BORDER << std::endl;
                    }
                    else
                    {
                        samplefos << BORDER << H2_STR << BORDER << std::endl;
                    }
                    samplefos << "Solution for " << puzzle << std::endl;
                    samplefos << "  Nodes Generated: "
                        << solver.getNodesGenerated() << std::endl;
                    samplefos << "  Solution Length (Depth): "
                        << (solutionStack.size() - 1) << std::endl;

                    // write actual solutions
                    while (!(solutionStack.empty()))
                    {
                        samplefos << PuzzleManipulator::str(
                            solutionStack.top());
                        solutionStack.pop();
                    }
                    ++sampleCount;
                }
            }
            else
            {
                // we only need to save integers
                // excludes the initial state, so -1
                depth = (solutionStack.size() - 1);
            }
            nodesGenerated = solver.getNodesGenerated();

            // write to csv files
            if (hType == 1)
            {
                // write to h1Stats.csv depth,nodesGenerated,runTime
                h1fos << depth << "," << nodesGenerated << ","
                    << ((elapsed_seconds.count()) * 1000.0) << "\n";
            }
            else
            {
                // write to h2Stats.csv
                h2fos << depth << "," << nodesGenerated << ","
                    << ((elapsed_seconds.count()) * 1000.0) << "\n";
            }
        }
        
    }

    std::cout << "Files generated." << std::endl;

    samplefos.close();
    h1fos.close();
    h2fos.close();
}