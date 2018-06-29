/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

This class simply represents an entity whose primary role is to contain the
following information:
 - state: the current state
 - previousState: the state that led to this state
 - h: the estimated cost of this state according to some heuristic function
 - g: the accumulated actual path cost since the initial state
Naturally, it contains a bunch of getters. It has a special overload though,
which is useful for a priority queue.
*/
#include "Node.h"

// Constructor and Destructor
Node::Node(int s, int p, int estimatedCost, int accumulatedPathCost)
    : state(s), previousState(p), h(estimatedCost), g(accumulatedPathCost)
{

}

// Overload
/*
Purpose: In PuzzleSolver, Nodes are needed for the priority queue because
std::greater<Node> is being used to order the contents.
*/
bool Node::operator>(const Node& other) const
{
    return ((this->h + this->g) > (other.h + other.g));
}

// Getters
int Node::getState()
{
    return state;
}
int Node::getPreviousState()
{
    return previousState;
}
int Node::getG()
{
    return g;
}
int Node::getAStar()
{
    return h + g;
}
