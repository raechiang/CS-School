/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This solves a given state using the steepest-ascent hill climbing algorithm.
*/
#ifndef HILLCLIMBINGSOLVER_H
#define HILLCLIMBINGSOLVER_H

#include <string>

class HillClimbingSolver
{
    private:
        // tracks the number of queen moves
        int searchCost;
        // a string that is the initial state
        std::string initialState;
        // this just counts the number of attacking pairs of queens
        int getHeuristic(std::string state);
    public:
        // constructor
        HillClimbingSolver(std::string state);
        // the bulk of the algorithm
        std::string hillClimb();
        // returns the searchCost
        int getSearchCost();
};

#endif