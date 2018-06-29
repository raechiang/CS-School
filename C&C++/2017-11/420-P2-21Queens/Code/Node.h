/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This is a very simple node that represents a tuple of (state,its fitness). It is
used only by the GeneticAlgorithmSolver.
*/
#ifndef NODE_H
#define NODE_H

#include <string>

class Node
{
    private:
        std::string state;
        int fitness;
    public:
        Node(std::string state, int fitness);
        std::string getState();
        int getFitness();
};

#endif