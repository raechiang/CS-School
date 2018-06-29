/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#ifndef USERINTERFACE_H
#define USERINTERFACE_H

#include <cstdint> // int64_t
#include <vector> // vector

class UserInterface
{
    private:
        std::vector<std::string> cMoves;
        std::vector<std::string> hMoves;
        int humanOrderSelection;
        int64_t stringToBin(std::string move);
        std::string binToString(int64_t move);
    public:
        UserInterface();
        void writeBoard(const int64_t &xPositions, const int64_t &oPositions);
        void writeComputerMove(int64_t move);
        void writeGameOver(int status);
        void writeMoves(int64_t hMove, int64_t cMove);
        int getComputerTurn(); // for turn init
        double getTimeLimit();
        int64_t getHumanMove(); // rq human move
};

#endif