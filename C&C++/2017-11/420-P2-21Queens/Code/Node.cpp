/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This is a very simple node that represents a tuple of (state,its fitness). It is
used only by the GeneticAlgorithmSolver.
*/
#include "Node.h"

#include <iostream>

Node::Node(std::string state, int fitness)
{
    this->state = state;
    this->fitness = fitness;
}

std::string Node::getState()
{
    return state;
}

int Node::getFitness()
{
    return fitness;
}