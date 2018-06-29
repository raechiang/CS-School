/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

This class controls running the tests, including calling the random puzzle
generator and handling user interaction.
*/
#ifndef TESTER_H
#define TESTER_H

#include "RandomPuzzleGenerator.h"
#include <iostream>

class Tester
{
    private:
        RandomPuzzleGenerator rpg;
        // requests input from user
        int requestMode();
        int requestPuzzle();
        // solving and related i/o
        void runSingleCaseMode(int puzzle);
        void runCasesMode(int mode);
        void solveAndPrint(int h, int puzzle);
    public:
        // constructor
        Tester();
        void run();
};

#endif
