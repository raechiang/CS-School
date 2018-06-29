/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#include "Board.h"

#include "Constants.h"

Board::Board()
{
    xPositions = 0x0000000000000000;
    oPositions = 0x0000000000000000;
}

bool Board::updateXPositions(int64_t xMove)
{
    if (isValidMove(xMove))
    {
        xPositions = xPositions | xMove;
        return true;
    }
    return false;
}

bool Board::updateOPositions(int64_t oMove)
{
    if (isValidMove(oMove))
    {
        oPositions = oPositions | oMove;
        return true;
    }
    return false;
}

bool Board::isValidMove(int64_t move)
{
    int64_t b = xPositions | oPositions;
    if ((b & move) != 0)
    {
        return false;
    }
    return true;
}

int Board::checkGameOver()
{
    if ((xPositions | oPositions) == fullBoard)
    {
        // draw
        return Constants::DRAW;
    }
    if (isWin(xPositions))
    {
        // x win
        return Constants::XWIN;
    }
    if (isWin(oPositions))
    {
        // o win
        return Constants::OWIN;
    }
    
    return Constants::NOT_OVER;
}

bool Board::isWin(const int64_t pos)
{
    if (isHorizontalWin(pos) | isVerticalWin(pos))
    {
        return true;
    }
    return false;
}

bool Board::isHorizontalWin(const int64_t pos)
{
    int64_t rCheck = 0xF;
    for (int r = 0; r < 8; ++r)
    {
        for (int i = 0; i < 5; ++i)
        {
            if ((pos & rCheck) == rCheck)
            {
                return true;
            }
            rCheck = rCheck << 1;
        }
        rCheck = rCheck << 3;
    }
    return false;
}

bool Board::isVerticalWin(const int64_t pos)
{
    int64_t cCheck = 0x01010101;
    for (int c = 0; c < 40; ++c)
    {
        if ((pos & cCheck) == cCheck)
        {
            return true;
        }
        cCheck = cCheck << 1;
    }
    return false;
}

int64_t Board::getXPositions()
{
    return xPositions;
}

int64_t Board::getOPositions()
{
    return oPositions;
}