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
Naturally, it contains a bunch of getters. It has a special overload though.
*/
#ifndef NODE_H
#define NODE_H

#include <iostream>

class Node
{
    private:
        // fields
        int state; // current state
        int previousState; // state that led to this state
        int h; // the estimated cost of this state
        int g; // the accumulated path cost from the initial state to this one
    public:
        // constructor
        Node(int s, int p, int estimatedCost, int accumulatedPathCost);
        // overload
        bool operator>(const Node& other) const;
        // getters
        int getState();
        int getPreviousState();
        int getG();
        int getAStar();
};

#endif
