/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

This class simply randomly generates puzzles.
*/
#include "RandomPuzzleGenerator.h"

#include <cstdlib>
#include <ctime>

#include "Constants.h"
#include "PuzzleManipulator.h"

// Constructor
RandomPuzzleGenerator::RandomPuzzleGenerator()
{
    srand(time(NULL));
}

/*
This makes big puzzles. Big as in, with solution lengths that exceed 16.
*/
int RandomPuzzleGenerator::getBigPuzzle()
{
    // 1 1111 1111 = 511
    int checklist = 511;
    int puzzle = 0;
    for (int i = 0; i < 9; ++i)
    {
        int next = (rand() % 9);
        
        int newChecklist = checkValue(checklist, next);
        
        if (newChecklist == checklist)
        {
            --i;
        }
        else
        {
            checklist = newChecklist;
            puzzle *= 10;
            puzzle += next;
        }
    }
    return puzzle;
}

/*
This gets one random puzzle with a random solution depth. Why 35? After a few
test runs, it seemed like using 35 random movements would generate puzzles with
solution lengths d in [1,19] with a decent spread except toward the upper
values. Increasing the value to be 40 didn't make much of a difference, and 20
was too short, since the random movements are only history-sensitive by one step
backwards.
*/
int RandomPuzzleGenerator::getRandomPuzzle()
{
    return getControlledPuzzle(rand() % 35);
}

/*
Generates a random puzzle by using random movements. It is history-sensitive by
one move only.
*/
int RandomPuzzleGenerator::getControlledPuzzle(int depth)
{
    int state = Constants::GOAL_STATE;
    int prevMove = 0;

    for (int i = 0; i < depth; ++i)
    {
        int blankPlace = PuzzleManipulator::getBlankPlace(state);
        int move = 0;
        // pick a random move until we get a legal move
        do
        {
            move = (rand() % 4);
        } while (!(PuzzleManipulator::isLegalMove(blankPlace, move))
            && (prevMove != move));
        // get the place to move to
        int swapPlace = PuzzleManipulator::getSwapPlace(blankPlace, move);
        int newState = state;
        if (swapPlace != -1)
        {
            newState = PuzzleManipulator::swap(state, blankPlace, swapPlace);
            state = newState;
        }
        prevMove = reverseMove(move);
    }
    return state;
}

/*
This function simply grabs the move that is reverse of the passed move, which is
only used in getControlledPuzzle(int).
*/
int RandomPuzzleGenerator::reverseMove(int move)
{
    if (move == Constants::UP)
    {
        return Constants::DOWN;
    }
    else if (move == Constants::DOWN)
    {
        return Constants::UP;
    }
    else if (move == Constants::LEFT)
    {
        return Constants::RIGHT;
    }
    else // (move == RIGHT)
    {
        return Constants::LEFT;
    }
}

/*
This function helps getBigPuzzle() by checking off values that have already been
rolled.
*/
int RandomPuzzleGenerator::checkValue(int checklist, int value)
{
    switch (value)
    {
        case 0:
            if ((checklist & 256) == 256)
            {
                return (checklist - 256);
            }
            break;
        case 1:
            if ((checklist & 128) == 128)
            {
                return (checklist - 128);
            }
            break;
        case 2:
            if ((checklist & 64) == 64)
            {
                return (checklist - 64);
            }
            break;
        case 3:
            if ((checklist & 32) == 32)
            {
                return (checklist - 32);
            }
            break;
        case 4:
            if ((checklist & 16) == 16)
            {
                return (checklist - 16);
            }
            break;
        case 5:
            if ((checklist & 8) == 8)
            {
                return (checklist - 8);
            }
            break;
        case 6:
            if ((checklist & 4) == 4)
            {
                return (checklist - 4);
            }
            break;
        case 7:
            if ((checklist & 2) == 2)
            {
                return (checklist - 2);
            }
            break;
        case 8:
            if ((checklist & 1) == 1)
            {
                return (checklist - 1);
            }
            break;
        default:
            return checklist;
    }
    return checklist;
}