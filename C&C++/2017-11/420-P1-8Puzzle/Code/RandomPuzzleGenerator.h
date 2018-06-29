/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

This class simply randomly generates puzzles.
*/
#ifndef RANDOMPUZZLEGENERATOR_H
#define RANDOMPUZZLEGENERATOR_H

class RandomPuzzleGenerator
{
    private:
        int checkValue(int checklist, int value);
        int reverseMove(int move);
    public:
        RandomPuzzleGenerator();
        int getBigPuzzle();
        int getControlledPuzzle(int depth); 
        int getRandomPuzzle();
};

#endif