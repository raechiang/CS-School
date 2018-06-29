/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This is a simple class that creates a string representation of the state of a
board by randomly generating row positions for a queen.
*/
#ifndef RANDOMSTATEGENERATOR_H
#define RANDOMSTATEGENERATOR_H

#include <string>
#include <iostream>

class RandomStateGenerator
{
    public:
        RandomStateGenerator();
        std::string getRandState();
};

#endif