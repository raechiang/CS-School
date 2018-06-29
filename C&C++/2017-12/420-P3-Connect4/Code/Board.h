/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#ifndef BOARD_H
#define BOARD_H

#include <cstdint> // int64_t

class Board
{
    private:
        // Fields
        int64_t xPositions;
        int64_t oPositions;
        // constant
        const static int64_t fullBoard = 0xFFFFFFFFFFFFFFFF;
        // Board Status
        bool isWin(const int64_t pos);
        bool isHorizontalWin(const int64_t pos);
        bool isVerticalWin(const int64_t pos);
    public:
        // Constructor
        Board();
        // Board Update
        bool updateXPositions(int64_t xMove);
        bool updateOPositions(int64_t oMove);
        // Board Status
        bool isValidMove(int64_t move);
        int checkGameOver();
        // Getters
        int64_t getXPositions();
        int64_t getOPositions();
};

#endif