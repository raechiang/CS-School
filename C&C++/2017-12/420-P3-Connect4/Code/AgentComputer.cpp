/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#include "AgentComputer.h"

#include <limits> // infinity
#include "Constants.h"

namespace
{
    double inf = std::numeric_limits<double>::infinity();

    double centerWeight = 2;
    double blockWeight = 1000;

    int fourWeight = 10000;
    int threeWeight = 100;
    int twoWeight = 10;
}

AgentComputer::AgentComputer(int pType, double time)
    : playerType(pType), maxDepth(1), currentBestMove(0), currentBestValue(-1 * inf), timer(0), maxTime(time)
{
}

int64_t AgentComputer::getMove(const int64_t &x, const int64_t &o, int64_t hMove)
{
    currentBestMove = 0;
    currentBestValue = -1 * inf;
    
    search(x, o, hMove);

    explored.clear(); // dumps old tree info
    maxDepth = 1;

    return currentBestMove;
}

void AgentComputer::search(const int64_t &x, const int64_t &o, int64_t hMove)
{
    timer = 0;
    std::chrono::time_point<std::chrono::system_clock> startTime = std::chrono::system_clock::now();

    while (timer < maxTime)
    {
        double value = findMaxValue(x, o, (-1 * inf), inf, 0, hMove, startTime);
        auto end = std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed_seconds = end - startTime;
        timer = elapsed_seconds.count();
        ++maxDepth;
    }
}

int64_t AgentComputer::getLower(const int64_t hMove)
{
    if (hMove != 0x1 && hMove != 0x8000000000000000 && hMove != 0)
    {
        return (hMove >> 1);
    }
    else
    {
        return 0x80000000;
    }
}
int64_t AgentComputer::getUpper(const int64_t hMove)
{
    if (hMove != 0x1 && hMove != 0x8000000000000000 && hMove != 0)
    {
        return (hMove << 1);
    }
    else
    {
        return 0x0100000000;
    }
}

double AgentComputer::findMaxValue(int64_t x, int64_t o, double alpha, double beta, int depth, int64_t hMove, std::chrono::time_point<std::chrono::system_clock> startTime)
{
    if (isCutoff(x, o, depth, startTime))
    {
        return evaluate(x, o);
    }

    double value = -1 * inf;

    if (explored.find(depth) != explored.end())
    {
        // we have been here before

        std::priority_queue<Node, std::vector<Node>, std::greater<Node>> pQueue(explored.at(depth));

        while (!(pQueue.empty()))
        {
            // take the first Node
            Node node = pQueue.top();
            pQueue.pop();

            if (playerType == Constants::PLAYER_X)
            {
                value = max(value, findMinValue(act(x, node.getAction()), o, alpha, beta, depth + 1, hMove, startTime));
            }
            else
            {
                value = max(value, findMinValue(x, act(o, node.getAction()), alpha, beta, depth + 1, hMove, startTime));
            }

            // add updated value to explored

            if (depth == 0 && value > currentBestValue)
            {
                currentBestMove = node.getAction();
                currentBestValue = value;
            }

            if (value >= beta)
            {
                return value;
            }

            alpha = max(alpha, value);
        }
    }
    else
    {
        
        // we have not been here before
        int64_t actionSet = getValidActions(x, o);

        int64_t actionLower = getLower(hMove);
        int64_t actionUpper = getUpper(hMove);
        bool canContinue = true;
        bool checkedMax = false;
        bool checkedMin = false;
        
        std::priority_queue<Node, std::vector<Node>, std::greater<Node>> pQueue;
        explored.emplace(depth, pQueue);

        while (canContinue)
        {
            if (!(checkedMax))
            {
                if (actionUpper == 0x8000000000000000)
                {
                    checkedMax = true;
                }
                if ((actionSet & actionUpper) == actionUpper) // for each action in actionSet
                {
                    if (playerType == Constants::PLAYER_X) // MAX(X)
                    {
                        value = max(value, findMinValue(act(x, actionUpper), o, alpha, beta, depth + 1, hMove, startTime));
                    }
                    else // MAX(O)
                    {
                        value = max(value, findMinValue(x, act(o, actionUpper), alpha, beta, depth + 1, hMove, startTime));
                    }

                    // add to pQueue
                    explored.at(depth).push(Node(actionUpper, value));

                    if (depth == 0 && value > currentBestValue)
                    {
                        currentBestMove = actionUpper;
                        currentBestValue = value;
                    }

                    if (value >= beta)
                    {
                        return value;
                    }

                    alpha = max(alpha, value);
                }
                actionUpper = actionUpper << 1;
            }
            if (!(checkedMin))
            {
                if (actionLower == 0x1)
                {
                    checkedMin = true;
                }
                if ((actionSet & actionLower) == actionLower) // for each action in actionSet
                {
                    if (playerType == Constants::PLAYER_X) // MAX(X)
                    {
                        value = max(value, findMinValue(act(x, actionLower), o, alpha, beta, depth + 1, hMove, startTime));
                    }
                    else // MAX(O)
                    {
                        value = max(value, findMinValue(x, act(o, actionLower), alpha, beta, depth + 1, hMove, startTime));
                    }

                    // add to pQueue
                    explored.at(depth).push(Node(actionLower, value));

                    if (depth == 0 && value > currentBestValue)
                    {
                        currentBestMove = actionLower;
                        currentBestValue = value;
                    }

                    if (value >= beta)
                    {
                        return value;
                    }

                    alpha = max(alpha, value);
                }
                actionLower = actionLower >> 1;
            }

            if (checkedMax && checkedMin)
            {
                canContinue = false;
            }
        }
    }
    
    return value;
}

double AgentComputer::findMinValue(int64_t x, int64_t o, double alpha, double beta, int depth, int64_t hMove, std::chrono::time_point<std::chrono::system_clock> startTime)
{
    if (isCutoff(x, o, depth, startTime))
    {
        return evaluate(x, o);
    }

    double value = inf;

    if (explored.find(depth) != explored.end())
    {
        // we have been here before

        std::priority_queue<Node, std::vector<Node>, std::greater<Node>> pQueue(explored.at(depth));

        while (!(pQueue.empty()))
        {
            // take the first Node
            Node node = pQueue.top();
            pQueue.pop();

            if (playerType == Constants::PLAYER_X)
            {
                value = min(value, findMaxValue(act(x, node.getAction()), o, alpha, beta, depth + 1, hMove, startTime));
            }
            else
            {
                value = min(value, findMaxValue(x, act(o, node.getAction()), alpha, beta, depth + 1, hMove, startTime));
            }

            if (value <= alpha)
            {
                return value;
            }

            beta = min(beta, value);
        }
    }
    else
    {

        // we have not been here before
        int64_t actionSet = getValidActions(x, o);

        int64_t actionLower = getLower(hMove);
        int64_t actionUpper = getUpper(hMove);
        bool canContinue = true;
        bool checkedMax = false;
        bool checkedMin = false;

        std::priority_queue<Node, std::vector<Node>, std::greater<Node>> pQueue;
        explored.emplace(depth, pQueue);
        
        while (canContinue)
        {
            if (!(checkedMax))
            {
                if (actionUpper == 0x8000000000000000)
                {
                    checkedMax = true;
                }
                if ((actionSet & actionUpper) == actionUpper) // for each action in actionSet
                {
                    if (playerType == Constants::PLAYER_X) // MAX(X) -> MIN(O)
                    {
                        value = min(value, findMaxValue(x, act(o, actionUpper), alpha, beta, depth + 1, hMove, startTime));
                    }
                    else // MAX(O) -> MIN(X)
                    {
                        value = min(value, findMaxValue(act(x, actionUpper), o, alpha, beta, depth + 1, hMove, startTime));
                    }

                    // add to pQueue
                    explored.at(depth).push(Node(actionUpper, value));

                    if (value <= alpha)
                    {
                        return value;
                    }

                    beta = min(beta, value);
                }
                actionUpper = actionUpper << 1;
            }

            if (!(checkedMin))
            {
                if (actionLower == 0x1)
                {
                    checkedMin = true;
                }
                if ((actionSet & actionLower) == actionLower) // for each action in actionSet
                {
                    if (playerType == Constants::PLAYER_X) // MAX(X) -> MIN(O)
                    {
                        value = min(value, findMaxValue(x, act(o, actionLower), alpha, beta, depth + 1, hMove, startTime));
                    }
                    else // MAX(O) -> MIN(X)
                    {
                        value = min(value, findMaxValue(act(x, actionLower), o, alpha, beta, depth + 1, hMove, startTime));
                    }

                    // add to pQueue
                    explored.at(depth).push(Node(actionLower, value));

                    if (value <= alpha)
                    {
                        return value;
                    }

                    beta = min(beta, value);
                }
                actionLower = actionLower >> 1;
            }

            if (checkedMax && checkedMin)
            {
                canContinue = false;
            }
        }
    }

    return value;
}

bool AgentComputer::isCutoff(int64_t x, int64_t o, int depth, std::chrono::time_point<std::chrono::system_clock> startTime)
{
    auto endTime = std::chrono::system_clock::now();
    std::chrono::duration<double> elapsed_seconds = endTime - startTime;
    timer = elapsed_seconds.count();
    if (timer > maxTime)
    {
        return true;
    }

    if (depth > maxDepth)
    {
        return true;
    }

    // terminal states (no moves remaining, full board)
    if ((~(x | o) ^ 0) == 0)
    {
        return true;
        // full board = all 11111 -> not'ed to all 0000's XORed with 0 -> 0000's because they're all 0
    }

    return false;
}

/*
What are some things that may lead to victory?
- placing pieces in cols/rows where they are shared/CAN make 4 in a line (3 in a line means you'll definitely win! 2 less so. 1 even less so. 0 no indication)
- placing pieces in cols/rows where they can BLOCK the enemy from making 4 in a line (may actually take precedent over the first?)
*/
double AgentComputer::evaluate(const int64_t &x, const int64_t &o)
{
    if (playerType == Constants::PLAYER_X)
    {
        return getUtility(x, o);
    }
    else // Player is O
    {
        return getUtility(o, x);
    }
}

int AgentComputer::countLines(const int64_t &state, const int64_t &actionSet)
{
    //  Want 1-move wins and 1-move blocks to be more important
    //  than 2-move wins and 2-move blocks
    //  and so on
    //    Count lines of uninterrupted 3's out of 4
    //       Rows (bin): 0 1 1 1, 1 0 1 1, 1 1 0 1, 1 1 1 0
    //       Cols (hex): 0x00010101, 0x01000101, 0x01010001, 0x01010100
    //    Count lines of uninterrupted 2's out of 4
    //       Rows (bin): 0 0 1 1, 0 1 0 1, 1 0 0 1, 0 1 1 0, 1 0 1 0, 1 1 0 0
    //       Cols (hex): 0x0101, 0x010001, 0x01000001, 0x010100, 0x01000100, 0x01010000
    //    Count lines of uninterrupted 1's out of 4
    //       Rows (bin): 0 0 0 1, 0 0 1 0, 0 1 0 0, 1 0 0 0
    //       Cols (hex): 0x01000000, 0x010000, 0x0100, 0x01
    return countRowLines(state, actionSet) + countColLines(state, actionSet);
}

int AgentComputer::countRowLines(const int64_t &state, const int64_t &actionSet)
{
    int lines = 0;
    // rows of 3
    int64_t threes[4] = { 0x7, 0xB, 0xD, 0xE };
    lines = lines + (threeWeight * countNRows(state, actionSet, 4, threes));

    // rows of 2
    int64_t twos[6] = { 0x3, 0x5, 0x9, 0x6, 0xA, 0xC };
    lines = lines + (twoWeight * countNRows(state, actionSet, 6, twos));

    return lines;
}

int AgentComputer::countNRows(const int64_t &state, const int64_t &actionSet, int n, int64_t *rCheck)
{
    int64_t rCheckFull = 0xF;

    int lines = 0;

    for (int r = 0; r < 8; ++r)
    {
        for (int i = 0; i < 5; ++i)
        {
            for (int checkIndex = 0; checkIndex < n; ++checkIndex)
            {
                if ((state & *(rCheck + checkIndex)) == *(rCheck + checkIndex))
                {
                    if (((actionSet ^ *(rCheck + checkIndex)) & rCheckFull) == rCheckFull) // (^ is XOR)
                    {
                        ++lines;
                    }
                }
                *(rCheck + checkIndex) = *(rCheck + checkIndex) << 1;
            }
            rCheckFull = rCheckFull << 1;
        }

        for (int checkIndex = 0; checkIndex < n; ++checkIndex)
        {
            *(rCheck + checkIndex) = *(rCheck + checkIndex) << 3;
        }
        rCheckFull = rCheckFull << 3;
    }

    return lines;
}

int AgentComputer::countColLines(const int64_t &state, const int64_t &actionSet)
{
    int lines = 0;
    
    // cols of 3
    int64_t threes[4] = { 0x00010101, 0x01000101, 0x01010001, 0x01010100 };
    lines = lines + (threeWeight * countNCols(state, actionSet, 4, threes));

    // cols of 2
    int64_t twos[6] = { 0x0101, 0x010001, 0x01000001, 0x010100, 0x01000100, 0x01010000 };
    lines = lines + (twoWeight * countNCols(state, actionSet, 6, twos));

    return lines;
}

int AgentComputer::countNCols(const int64_t &state, const int64_t &actionSet, int n, int64_t *cCheck)
{
    int64_t cCheckFull = 0x01010101;

    int lines = 0;

    for (int c = 0; c < 40; ++c)
    {
        for (int checkIndex = 0; checkIndex < n; ++checkIndex)
        {
            if ((state & *(cCheck + checkIndex)) == *(cCheck + checkIndex))
            {
                if (((actionSet ^ *(cCheck + checkIndex)) & cCheckFull) == cCheckFull) // (^ is XOR)
                {
                    ++lines;
                }
            }
        }
        for (int checkIndex = 0; checkIndex < n; ++checkIndex)
        {
            *(cCheck + checkIndex) = *(cCheck + checkIndex) << 1;
        }
        cCheckFull = cCheckFull << 1;
    }

    return lines;
}

int AgentComputer::hasGameOver(const int64_t &me, const int64_t &them)
{
    // rows
    int64_t rCheck = 0xF;
    for (int r = 0; r < 8; ++r)
    {
        for (int i = 0; i < 5; ++i)
        {
            if ((me & rCheck) == rCheck)
            {
                return 1; // win
            }
            if ((them & rCheck) == rCheck)
            {
                return -1; // loss
            }
            rCheck = rCheck << 1;
        }
        rCheck = rCheck << 3;
    }

    // cols
    int64_t cCheck = 0x01010101;
    for (int c = 0; c < 40; ++c)
    {
        if ((me & cCheck) == cCheck)
        {
            return 1; // win
        }

        if ((them & cCheck == cCheck))
        {
            return -1; // loss
        }
        cCheck = cCheck << 1;
    }

    return 0; // draw
}

double AgentComputer::getUtility(const int64_t &me, const int64_t &them)
{
    /*
    Notes:
    I have X in a row (If i have 3 in a row, that's better than only two in a row - your function should favor adding to longer rows over shorter ones)
    My opponent has X in a row (Likewise, the more he/she has in a row, the worse off we are)
    Count how many rows you are filling in (Placing a piece and forming 2 rows of 3 is better than placing a piece and only forming one row of 3)
    Count how many rows you are blocking (similarly, if you can drop a piece and block two opponents rows of 3, that's better than blocking a single row of 2)

    One basic evaluation function is as suggested in another answer, we can calculate the number of possible 4 in a rows the player can still make and subtract
    it from the opponent. You can give different weights or importance to blocks that already have three tiles compared to blocks that have only 1 tile.

    Another stronger evaluation function can be built using the concept of threats. threat is a square that connects 4 when a tile is dropped there
    by the opponent. You can simply return the difference in the number of threats by each player, but we can do much better by actually filtering useless
    threats (like a threat just above an opponents threat, or all threats above a threat by both players) and even assigning bonus for some threats (like
    lowermost threat of a column or 2 consecutive threats by the same player).
    */

    double utilityEstimation = 0;
    int gameOver = hasGameOver(me, them);
    if (gameOver == -1)
    {
        utilityEstimation -= fourWeight;
    }
    if (gameOver == 1)
    {
        utilityEstimation += fourWeight;
    }

    int64_t potential = getValidActions(me, them);

    // count how many 4-line me's can be made
    utilityEstimation = utilityEstimation + countLines(me, potential);

    // count how many opponent (x) 4-lines can be made
    utilityEstimation = utilityEstimation + (-1 * countLines(them, potential));

    // count how many lines you are blocking
    utilityEstimation = utilityEstimation + (blockWeight * countLines(them, me));

    // center bias
    unsigned long long centerIntersections = me & 0x3c3c3c3c0000;
    if (centerIntersections == 0)
    {
        utilityEstimation = utilityEstimation - (centerWeight * 16);
    }
    else
    {
        int centerCount = 0;
        centerIntersections = centerIntersections >> 18;
        while (centerIntersections != 0)
        {
            if ((centerIntersections & 1) == 1)
            {
                ++centerCount;
            }
            else
            {
                --centerCount;
            }
            centerIntersections = centerIntersections >> 1;
        }
        utilityEstimation = utilityEstimation + (centerWeight * centerCount);
    }

    return utilityEstimation;
}

int64_t AgentComputer::getValidActions(const int64_t &x, const int64_t &o)
{
    return (~(x | o));
}

int64_t AgentComputer::act(const int64_t &state, const int64_t &action)
{
    return (state | action);
}

double AgentComputer::min(const double &a, const double &b)
{
    if (a > b)
    {
        return b;
    }
    return a;
}

double AgentComputer::max(const double &a, const double &b)
{
    if (a < b)
    {
        return b;
    }

    return a;
}

int AgentComputer::getPlayerType()
{
    return playerType;
}