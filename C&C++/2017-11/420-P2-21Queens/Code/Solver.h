/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This is a small namespace that holds very simple functions that are shared
across the HC and GA solvers.
*/
#pragma once
#include <string>

namespace Solver
{
    // counts the pairs of queens that are attacking
    int countAttackingPairs(std::string state);
    // converts a state into a more human-friendly visual representation
    std::string boardToString(std::string state);
}