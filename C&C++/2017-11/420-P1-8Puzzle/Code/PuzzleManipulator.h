/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

This manipulates puzzle configurations.
*/
#pragma once
#include <iostream>

namespace PuzzleManipulator
{
    // state transitioning
    int getBlankPlace(int current);
    int getSwapPlace(int blankPlace, int move);
    bool isLegalMove(int blankPlace, int move);
    int swap(int current, int blankPlace, int swapPlace);
    // puzzle validity
    bool checkPuzzleValidity(int puzzle);
    // string creation
    std::string str(int state);
}