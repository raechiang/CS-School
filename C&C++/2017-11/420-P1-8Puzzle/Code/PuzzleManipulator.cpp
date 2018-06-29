/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

This deals with manipulation of the puzzle, which includes checking certain
conditions, retrieving place values, and changing the state of a puzzle.
*/
#include "PuzzleManipulator.h"

#include <sstream>
#include "Constants.h"

// State Transitions
/*
This simply grabs the place value of the blank (0) tile. For example, in the
configuration 312645078, the blank is in place 2.
current - The integer value that represents the current state.
*/
int PuzzleManipulator::getBlankPlace(int current)
{
    for (int i = 0; i < 8; ++i)
    {
        if ((current / ((int)pow(10, i)) % 10) == 0)
        {
            return i;
        }
    }
    // 8th place is zero
    return 8;
}
/*
Given the blank place and the move that is wanted, it will return the correct
place value for the tile to swap with. For example in the configuration
312645078, given the move UP=0 and the blankPlace=2, it will return the place
value of 6 which is 5. Otherwise, it will return -1, which actually should
never happen assuming isLegalMove(int,int) is being used too.
blankPlace - the place value corresponding to the blank (0)
move - the move, where UP=0, DOWN=1, LEFT=2, RIGHT=3
*/
int PuzzleManipulator::getSwapPlace(int blankPlace, int move)
{
    int swapPlace = blankPlace;

    if (move == Constants::UP) // up
    {
        swapPlace = blankPlace + 3;
    }
    else if (move == Constants::DOWN) // down
    {
        swapPlace = blankPlace - 3;
    }
    else if (move == Constants::LEFT) // left
    {
        swapPlace = blankPlace + 1;
    }
    else if (move == Constants::RIGHT) // right
    {
        swapPlace = blankPlace - 1;
    }

    if (swapPlace >= 0 && swapPlace <= 8)
    {
        return swapPlace;
    }
    else
    {
        return -1;
    }
}
/*
This will check if the passed int move is a valid move. The blank cannot move
out of the puzzle after all.
blankPlace - the place value corresponding to the blank (0)
move - the move, where UP=0, DOWN=1, LEFT=2, RIGHT=3
*/
bool PuzzleManipulator::isLegalMove(int blankPlace, int move)
{
    if (move == Constants::UP)
    {
        // cannot move up
        // 6 7 8
        if (blankPlace == 6 || blankPlace == 7 || blankPlace == 8)
        {
            return false;
        }
    }
    else if (move == Constants::DOWN)
    {
        // cannot move down
        // 0 1 2
        if (blankPlace == 0 || blankPlace == 1 || blankPlace == 2)
        {
            return false;
        }
    }
    else if (move == Constants::LEFT)
    {
        // cannot move left
        // 8 5 2
        if (blankPlace == 8 || blankPlace == 5 || blankPlace == 2)
        {
            return false;
        }
    }
    else if (move == Constants::RIGHT)
    {
        // cannot move right
        // 6 3 0
        if (blankPlace == 6 || blankPlace == 3 || blankPlace == 0)
        {
            return false;
        }
    }

    return true;
}
/*
This swaps the blank with the desired swap place in the current configuration.
Example:
 current = 120345678, blankPlace = 6, swapPlace = 7
 state = 120345678
 actualSwapPlace = (10^7) = 10000000
 swapTile = 120345678 / 10000000 % 10 = 2
 state = 120345678 - (2 * 10000000) = 100345678
 state = 100345678 + (2 * 10^6) = 102345678
current - the current state
blankPlace - the place value where the blank is
swapPlace - the place value where the blank wants to go
*/
int PuzzleManipulator::swap(int current, int blankPlace, int swapPlace)
{
    int state = current;
    int actualSwapPlace = ((int)pow(10, swapPlace));
    int swapTile = state / actualSwapPlace % 10;
    state = state - ((int)(swapTile * actualSwapPlace));
    state = state + ((int)(swapTile * pow(10, blankPlace)));
    return state;
}

// Puzzle Validity
/*
This checks the validity of a puzzle, using the definition given in by the
project guidelines. To check if a puzzle is solvable, we consider inversions.
An inversion occurs when one tile is at any place succeeded by a tile whose
value is less than its. If the sum of all inversions in the puzzle is even, the
puzzle is solvable. Otherwise, if it is odd, it is not solvable.
 For example,
   124056837 --> inversions = 1+1+2+1+1+1 = 7
   Therefore, 124056837 is not solvable.
puzzle - the puzzle to check
*/
bool PuzzleManipulator::checkPuzzleValidity(int puzzle)
{
    int inversions = 0;

    for (int place = 100000000; place > 0; place /= 10)
    {
        int tile = puzzle / place % 10;
        for (int placeCompare = place / 10; placeCompare > 0;
            placeCompare /= 10)
        {
            int tileCompare = puzzle / placeCompare % 10;
            if (tileCompare != 0)
            {
                if (tileCompare < tile)
                {
                    ++inversions;
                }
            }

            if (tileCompare == tile)
            {
                return false;
            }
        }
    }

    if ((inversions % 2) == 0)
    {
        return true;
    }
    return false;
}

// String Creation
/*
This simply makes a better-looking string representation of the state. It would
look like this:
STATE: 12345678
0 1 2
3 4 5
6 7 8
state - the configuration to represent
*/
std::string PuzzleManipulator::str(int state)
{
    std::ostringstream sout;
    sout << "STATE: " << state << "\n";
    int count = 0;
    
    for (int place = 100000000; place > 0; place /= 10)
    {
        ++count;
        int tile = state / place % 10;
        sout << tile << " ";
        if (count % 3 == 0)
        {
            sout << "\n";
        }
    }
    sout << "\n";

    return sout.str();
}