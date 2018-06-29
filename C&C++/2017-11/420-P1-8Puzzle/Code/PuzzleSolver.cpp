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
#include "PuzzleSolver.h"

#include <queue>
#include <functional>
#include <unordered_map>
#include <stack>

#include "Constants.h"
#include "PuzzleManipulator.h"

// Constructor
PuzzleSolver::PuzzleSolver(int heuristicSelector, Node initial)
    : hSelector(heuristicSelector), initialState(initial), nodesGenerated(1)
{
    // Note: nodesChecked starts at 1 because it includes the initial state
}

/*
This will search for the solution sequence and return it as a stack of integer
values which represent states. The frontier is actually represented by two
data structures which is admittedly wasteful, but it seemed nice to have a
constant time search through the frontier (which is necessary since we check if
a state has been expanded into from an earlier state yet has not been explored)
and a logarithmic time sorted queue for popping and pushing nodes.
*/
std::stack<int> PuzzleSolver::search()
{
    // initialize frontier with initial state
    // Space = 2 * NodesGenerated
    std::unordered_map<int, Node> frontier;
    std::priority_queue<Node, std::vector<Node>, std::greater<Node>>
        frontierPQ;
    frontier.insert({ initialState.getState(), initialState }); // Run: O(1)
    frontierPQ.push(initialState); // Run: O(1) because it's empty

    // initialize empty explored state
    std::unordered_map<int, int> explored;
    // loop
    while (true) // This will loop d times where d is the solution length
    {
        // if frontier is empty, return failure
        if (frontier.empty()) // Run: O(1)
        {
            // Run: O(d) <-- solution set
            return getSequence(
                explored, initialState.getState(), initialState.getState());
        }

        if (frontierPQ.empty()) // Run: O(1)
        {
            // This shouldn't actually be reached since the search should only
            // be initiated if there is indeed a solution, but it's nice to
            // feel a little safe
            return getSequence(
                explored, initialState.getState(), initialState.getState());
        }
        // remove node from frontier
        Node current = frontierPQ.top();
        frontierPQ.pop(); // logarithmic time
        frontier.erase(current.getState());
        
        // if node is goal, return solution
        if (current.getState() == Constants::GOAL_STATE)
        {
            return getSequence(
                explored, initialState.getState(), current.getPreviousState());
        }

        // add node to explored
        explored.insert(
            { current.getState(), current.getPreviousState() }); // Run: O(1)

        // expand current
        std::vector<Node> expansion = expand(current); // max size is 4 nodes

        // add expanded nodes if they don't exist in frontier or explored
        for (auto& e : expansion) // loops at most 4 times
        {
            ++nodesGenerated;
            if (explored.find(e.getState()) == explored.end()
                && frontier.find(e.getState()) == frontier.end())
            {
                frontierPQ.push(e); // logarithmic time
                frontier.insert({ e.getState(), e });
            }
        }
    }
}

/*
This expands a given node, depending on its state. It uses PuzzleManipulator to
find tiles and move them around.
*/
std::vector<Node> PuzzleSolver::expand(Node current)
{
    std::vector<Node> expansion;
    int blankPlace = PuzzleManipulator::getBlankPlace(current.getState());

    // 4 times because max of 4 moves for a tile
    for (int move = 0; move < 4; ++move)
    {
        if (PuzzleManipulator::isLegalMove(blankPlace, move)) // const time
        {
            int swapPlace = PuzzleManipulator::getSwapPlace(
                blankPlace, move); // also constant time
            int newState = current.getState();
            if (swapPlace != -1)
            {
                newState = PuzzleManipulator::swap(
                    current.getState(), blankPlace, swapPlace);
                expansion.push_back(
                    Node(
                        newState,
                        current.getState(),
                        findH(newState),
                        current.getG() + 1));
            }
        }
    }

    return expansion;
}

// Heuristics
/*
Depending on the hSelector, which is set when the class is initialized, it
deploys the misplaced tile heuristic or the Manhattan distance heuristic.
*/
int PuzzleSolver::findH(int state)
{
    if (state == Constants::GOAL_STATE)
    {
        return 0;
    }

    if (hSelector == 1)
    {
        return misplacedH(state);
    }
    else // hSelector == 2
    {
        return manhattanH(state);
    }
}
/*
This counts the number of misplaced number tiles in the passed in configuration,
state.
*/
int PuzzleSolver::misplacedH(int state)
{
    int misplacedTiles = 0;
    // counts the number of tiles in the wrong place
    // goal position: 012345678
    // ten exp:       876543210
    for (int place = 0; place < 9; ++place) // loops over 9 places
    {
        int tens = ((int)pow(10, place));
        int tile = state / tens % 10;
        if (tile != 0) // does not count blank
        {
            if (tile != (Constants::GOAL_STATE / tens % 10))
            {
                ++misplacedTiles;
            }
        }
    }
    return misplacedTiles;
}
/*
This counts the total distances of each tile from its proper goal configuration.
It counts the rows and columns by using countManhattanRows(int,int) and
countManhattanCols(int,int).
*/
int PuzzleSolver::manhattanH(int state)
{
    int distances = 0;
    // sums the distances of all tiles from their goal position
    // goal position: 012345678
    // 0 1 2
    // 3 4 5
    // 6 7 8
    // ten exp:       876543210
    // 8 7 6
    // 5 4 3
    // 2 1 0
    for (int place = 0; place < 9; ++place) // loops over 9 places
    {
        int tile = state / ((int)pow(10, place)) % 10;
        // count by off rows and columns
        if (tile != 0) // Manhattan Distance does not include the blank
        {
            distances += (countManhattanRows(tile, place)
                + countManhattanCols(tile, place));
        }
    }
    return distances;
}
/*
This is called from manhattanH(int) and counts the number of rows that a tile is
off by.
*/
int PuzzleSolver::countManhattanRows(int tile, int place)
{
    // constant time
    int row = 0;

    if (tile >= 0 && tile <= 2)
    {
        // 0 1 2
        // N N N <- 5 4 3
        // N N N <- 2 1 0
        if (place >= 3 && place <= 5)
        {
            row = row + 1; // off by one row
        }
        else if (place >= 0 && place <= 2)
        {
            row = row + 2; // off by two rows
        }
    }
    else if (tile >= 3 && tile <= 5)
    {
        // N N N <- 8 7 6
        // 3 4 5
        // N N N <- 2 1 0
        if ((place >= 6 && place <= 8) || (place >= 0 && place <= 2))
        {
            row = row + 1; // off by one row
        }
    }
    else if (tile >= 6 && tile <= 8)
    {
        // N N N <- 8 7 6
        // N N N <- 5 4 3
        // 6 7 8
        if (place >= 6 && place <= 8)
        {
            row = row + 2; // off by two rows
        }
        else if (place >= 3 && place <= 5)
        {
            row = row + 1; // off by one row
        }
    }

    return row;
}
/*
This is called from manhattanH(int) and counts the number of columns that a tile
is off by.
*/
int PuzzleSolver::countManhattanCols(int tile, int place)
{
    // constant time
    int col = 0;

    if (tile == 0 || tile == 3 || tile == 6)
    {
        // 0 N N <- Y 7 6
        // 3 N N <- Y 4 3
        // 6 N N <- Y 1 0
        if (place == 7 || place == 4 || place == 1)
        {
            col = col + 1; // off by one col
        }
        else if (place == 6 || place == 3 || place == 0)
        {
            col = col + 1; // off by two cols
        }
    }
    else if (tile == 1 || tile == 4 || tile == 7)
    {
        // N 1 N <- 8 Y 6
        // N 4 N <- 5 Y 3
        // N 7 N <- 2 Y 0
        if (place != 7 || place != 4 || place != 1)
        {
            col = col + 1; // off by one col
        }
    }
    else if (tile == 2 || tile == 5 || tile == 8)
    {
        // N N 2 <- 8 7 Y
        // N N 5 <- 5 4 Y
        // N N 8 <- 2 1 Y
        if (place == 8 || place == 5 || place == 2)
        {
            col = col + 2; // off by 2 cols
        }
        else if (place == 7 || place == 4 || place == 1)
        {
            col = col + 1; // off by 1 col
        }
    }

    return col;
}

/*
This just collects the solution sequence by tracing backwards through the
explored map.
*/
std::stack<int> PuzzleSolver::getSequence(
    const std::unordered_map<int, int>& space,
    const int initialState,
    const int endState)
{
    std::stack<int> solution;

    solution.push(Constants::GOAL_STATE); // Run: O(1)
    int cursor = endState; // Run: O(1)

    // Run: O(d) where d is the solution length (depth) because
    // this will loop from the goal to the initial according to the
    // solution path
    while (cursor != initialState)
    {
        // Run: O(1)
        auto search = space.find(cursor);
        if (search != space.end())
        {
            solution.push(search->first);
            cursor = search->second;
        }
    }

    solution.push(cursor);

    return solution;
}

/*
This simply gets the nodesGenerated.
*/
int PuzzleSolver::getNodesGenerated()
{
    // Run: O(1)
    return nodesGenerated;
}