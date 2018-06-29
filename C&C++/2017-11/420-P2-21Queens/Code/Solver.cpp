/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This is a small namespace that holds very simple functions that are shared
across the HC and GA solvers.
*/
#include "Solver.h"

#include <sstream>

#include "Constants.h"

/*
This counts the number of attacking queen pairs.
*/
int Solver::countAttackingPairs(std::string state)
{
    // counts attacking queens
    int h = 0;
    for (int i = 0; i < state.length(); ++i)
    {
        for (int j = i + 1; j < state.length(); ++j)
        {
            // checks to see if they are in the same row
            if (state[i] == state[j])
            {
                ++h;
            }
            // checks to see if state[j] is in state[i]'s diagonal
            int diagonal = j - i;
            if (state[i] == state[j] - diagonal || state[i] == state[j] + diagonal)
            {
                ++h;
            }
        }
    }
    return h;
}

/*
This accepts a state of the board as a string and outputs a more human-friendly
visual representation of the board as a string. Empty spaces are represented by
a [ ] and a queen space is represented by a [Q].
*/
std::string Solver::boardToString(std::string state)
{
    std::ostringstream sout;
    sout << "\nBoard: " << state << "\n";
    sout << "Number of attacking pairs: " << countAttackingPairs(state) << "\n";
    sout << "[1][2][3][4][5][6][7][8][9][0][1][2][3][4][5][6][7][8][9][0][1]\n";
    bool** qArray = new bool*[state.length()];
    for (int i = 0; i < state.length(); ++i)
    {
        qArray[i] = new bool[state.length()];
    }
    for (int i = 0; i < state.length(); ++i)
    {
        for (int j = 0; j < state.length(); ++j)
        {
            *(*(qArray + j) + i) = false;
        }
    }

    for (int col = 0; col < state.length(); ++col)
    {
        int row = state[col] - Constants::OFFSET_A;
        *(*(qArray + col) + row) = true;
    }

    for (int i = 0; i < state.length(); ++i)
    {
        for (int j = 0; j < state.length(); ++j)
        {
            if ((*(*(qArray + j) + i)))
            {
                sout << "[Q]";
            }
            else
            {
                sout << "[ ]";
            }
        }
        sout << "\n";
    }

    // deallocate
    for (int i = 0; i < state.length(); ++i)
    {
        delete[] qArray[i];
        qArray[i] = nullptr;
    }
    delete[] qArray;
    qArray = nullptr;

    return sout.str();
}