/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#ifndef AGENTCOMPUTER_H
#define AGENTCOMPUTER_H

#include <cstdint> // int64_t
#include <unordered_map> // std::unordered_map
#include <string>
#include <queue> // std::priority_queue
#include <functional> // std::greater
#include <ctime> // timer
#include <chrono> // timer
#include "Node.h"

class AgentComputer
{
    private:
        // Fields
        // key=depth, pQueue=unchanged states+action+uValue
        std::unordered_map<int, std::priority_queue<Node, std::vector<Node>, std::greater<Node>> > explored;
        double timer;
        double maxTime;
        // corresponds to whether it is X or O
        int playerType;
        int maxDepth;
        int64_t currentBestMove;
        double currentBestValue;

        // Search
        void search(const int64_t &x, const int64_t &o, int64_t hMove); // uses alpha-beta
        double findMaxValue(int64_t x, int64_t o, double alpha, double beta, int depth, int64_t hMove, std::chrono::time_point<std::chrono::system_clock> startTime);
        double findMinValue(int64_t x, int64_t o, double alpha, double beta, int depth, int64_t hMove, std::chrono::time_point<std::chrono::system_clock> startTime);
        // Search Helpers
        bool isCutoff(int64_t x, int64_t o, int depth, std::chrono::time_point<std::chrono::system_clock> startTime);
        int64_t act(const int64_t &state, const int64_t &action);
        double min(const double &a, const double &b);
        double max(const double &a, const double &b);
        int64_t getLower(const int64_t hMove);
        int64_t getUpper(const int64_t hMove);
        // Evaluation
        double evaluate(const int64_t &x, const int64_t &o);
        // Evaluation Helpers
        double getUtility(const int64_t &me, const int64_t &them);
        int countLines(const int64_t &state, const int64_t &actionSet);
        int countRowLines(const int64_t &state, const int64_t &actionSet);
        int countColLines(const int64_t &state, const int64_t &actionSet);
        int countNRows(const int64_t &state, const int64_t &actionSet, int n, int64_t *rCheck);
        int countNCols(const int64_t &state, const int64_t &actionSet, int n, int64_t *cCheck);
        int hasGameOver(const int64_t &me, const int64_t &them);
        // General Helpers
        int64_t getValidActions(const int64_t &x, const int64_t &o);
        
    public:
        AgentComputer(int pType, double time);
        int64_t getMove(const int64_t &x, const int64_t &o, int64_t hMove);
        int getPlayerType();
};

#endif