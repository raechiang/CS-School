/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This solves a given state using the steepest-ascent hill climbing algorithm. To
initiate the solve using this class, use hillClimb(). This will return a string
that the solver has deduced as the best solution it could find.
*/
#include "HillClimbingSolver.h"

#include <sstream>
#include <iostream>
#include <string>
#include <cstdlib>

#include "Constants.h"
#include "Solver.h"

// Constructor
HillClimbingSolver::HillClimbingSolver(std::string state)
    : initialState(state), searchCost(0)
{
}

/*
This is the hill climbing algorithm. Given a fully-instantiated board, we move
one randomly-selected queen to another row. We only make movements if they are
more optimal or as optimal as our current state. When we reach h=0 (that is,
when there are 0 attacking queens), we have achieved our goal, and we may exit
the loop and return the solution state. It is possible that we never reach our
goal, in which case, we will end after moving a queen 4000 times.
*/
std::string HillClimbingSolver::hillClimb()
{
    // start with a board, state
    std::string bestState = initialState;
    int bestH = getHeuristic(initialState);
    int counter = Constants::COUNTER; // never really reaches 4000 with 21 queens

    std::string currentState = initialState;
    while (bestH != 0 && counter != 0)
    {
        // pick random queen/col
        int queen = (rand() % currentState.length());
        
        // check queen's vertical moves so we can place her in a square
        // that minimizes conflict
        for (int row = 0; row < currentState.length(); ++row)
        {
            if (currentState[queen] != (row + Constants::OFFSET_A))
            {
                // We want to consider a different neighbor state
                std::string prospectiveState = currentState;
                prospectiveState[queen] = (row + Constants::OFFSET_A);
                int prospectiveH = getHeuristic(prospectiveState);
                if (prospectiveH <= bestH) // = means we let sideways moves
                {
                    // if the h of the prospective state is lower than our
                    // current best replace the best
                    bestH = prospectiveH;
                    bestState = prospectiveState;
                }
            }
        }
        // We move our state to the best state we found
        currentState = bestState;

        --counter;
        ++searchCost;
    }

    return bestState;
}

/*
This will call Solver to count the number of attacking pairs.
*/
int HillClimbingSolver::getHeuristic(std::string state)
{
    return Solver::countAttackingPairs(state);
}

/*
This will get the search cost.
*/
int HillClimbingSolver::getSearchCost()
{
    return searchCost;
}