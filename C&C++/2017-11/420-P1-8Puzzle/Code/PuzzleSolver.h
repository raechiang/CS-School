/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

This class commits the actual solving of the puzzle. It has the ability to
search using two different heuristics, h1 and h2, which are selected when the
PuzzleSolver is first instantiated using the hSelector.
- h1 uses the misplaced tiles heuristic function
- h2 uses the Manhattan distance heuristic function
*/
#ifndef PUZZLESOLVER_H
#define PUZZLESOLVER_H

#include "Node.h"
#include <vector>
#include <stack>
#include <unordered_map>

class PuzzleSolver
{
    private:
        // fields
        int hSelector; // picks the heuristic to use
        int nodesGenerated; // counts the search cost
        Node initialState; // the initial state of the puzzle

        // methods
        // utility
        std::vector<Node> expand(Node current);
        std::stack<int> getSequence(const std::unordered_map<int, int>& space, const int initialState, const int endState);
        // heuristics
        int findH(int state);
        int misplacedH(int state);
        int manhattanH(int state);
        int countManhattanRows(int tile, int place);
        int countManhattanCols(int tile, int place);
    public:
        // constructor
        PuzzleSolver(int heuristicSelector, Node initial);
        // utility
        std::stack<int> search();
        // getters
        int getNodesGenerated();
};

#endif
