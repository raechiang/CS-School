/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#include "UserInterface.h"

#include <iostream>
#include <regex> // regular expression
#include <string>
#include <sstream> // sstream
#include "Constants.h"

UserInterface::UserInterface()
{
}

void UserInterface::writeBoard(const int64_t &xPositions, const int64_t &oPositions)
{
    char label[8] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
    unsigned long long bIterator = 0x8000000000000000;

    std::cout << "  1 2 3 4 5 6 7 8" << std::endl;
    for (int i = 0; i < 8; ++i)
    {
        std::cout << label[i] << " ";
        for (int j = 0; j < 8; ++j)
        {
            int64_t x = (xPositions & bIterator);
            int64_t o = (oPositions & bIterator);
            
            if (x == 0 && o == 0)
            {
                std::cout << "- ";
            }
            else
            {
                if (x == 0)
                {
                    std::cout << "O ";
                }
                else
                {
                    std::cout << "X ";
                }
            }

            bIterator = bIterator >> 1;
        }
        std::cout << std::endl;
    }
}

void UserInterface::writeComputerMove(int64_t move)
{
    std::string m = binToString(move);
    std::cout << "Player's Move: " << m << std::endl;
}

void UserInterface::writeGameOver(int status)
{
    if (status == Constants::XWIN)
    {
        std::cout << "X WINS!" << std::endl;
    }
    else if (status == Constants::OWIN)
    {
        std::cout << "O WINS!" << std::endl;
    }
    else // DRAW
    {
        std::cout << "DRAW!" << std::endl;
    }
}

void UserInterface::writeMoves(int64_t hMove, int64_t cMove)
{
    hMoves.push_back(binToString(hMove));
    cMoves.push_back(binToString(cMove));
    int size = 0;
    if (cMoves.size() < hMoves.size())
    {
        size = hMoves.size();
    }
    else
    {
        size = cMoves.size();
    }

    if (humanOrderSelection == Constants::PLAYER_X)
    {
        // human wanted to go first (X Piece)
        std::cout << "Opponent vs. Player" << std::endl;
        for (int i = 0; i < size; ++i)
        {
            std::cout << "\t" << (i + 1) << ". ";

            if (i < hMoves.size())
            {
                std::cout << hMoves[i];
            }

            std::cout << "  ";

            if (i < cMoves.size())
            {
                std::cout << cMoves[i];
            }

            std::cout << "\n";
        }
    }
    else
    {
        // human wanted to go second (O piece)
        std::cout << "Player vs. Opponent" << std::endl;

        for (int i = 0; i < size; ++i)
        {
            std::cout << "\t" << (i + 1) << ". ";

            if (i < cMoves.size())
            {
                std::cout << cMoves[i];
            }

            std::cout << "  ";

            if (i < hMoves.size())
            {
                std::cout << hMoves[i];
            }

            std::cout << "\n";
        }
    }
}

int UserInterface::getComputerTurn()
{
    std::cout << "Would you like to make move 1 and play as piece X" << std::endl;
    std::cout << " or would you like to make move 2 and play as piece O ? [1 / 2]\n";
    std::string orderString = "";

    while (!(std::regex_match(orderString, std::regex("[1-2]"))))
    {
        std::cout << ">";
        std::cin >> orderString;
    }
    
    humanOrderSelection = std::stoi(orderString);

    if (humanOrderSelection == Constants::PLAYER_X) // human is X
    {
        return Constants::PLAYER_O;
    }

    return Constants::PLAYER_X;
}

int64_t UserInterface::getHumanMove()
{
    std::cout << "Choose Opponent's Move\n";
    std::string move = "";
    
    while (!(std::regex_match(move, std::regex("([a-h]|[A-H])[1-8]"))))
    {
        std::cout << ">";
        std::cin >> move;
    }

    return stringToBin(move);
}

int64_t UserInterface::stringToBin(std::string move)
{
    std::string p2 = move.substr(1, 1);
    int colNum = std::stoi(p2);
    int colOffset = 8 - colNum;
    
    int rowOffset = (int)move[0];
    if (rowOffset < 72 && rowOffset > 65)
    {
        // capital
        rowOffset = rowOffset - 65 + 1;
    }
    else
    {
        // lower
        rowOffset = rowOffset - 97 + 1;
    }
    rowOffset = 8 - rowOffset;

    int shift = (rowOffset * 8) + colOffset;

    int64_t value = 1;

    return (value << shift);
}

std::string UserInterface::binToString(int64_t move)
{
    int64_t value = 1;
    int shift = 0;
    while (value != move)
    {
        ++shift;
        value = value << 1;
    }

    int rowOffset = shift / 8;
    int asciiValue = 104 - rowOffset;

    int colOffset = shift % 8;
    int colNum = 8 - colOffset;

    std::ostringstream sout;
    sout << ((char)asciiValue) << colNum;

    return sout.str();
}

double UserInterface::getTimeLimit()
{
    std::cout << "How many seconds will you allow the computer to make a decision?\n";
    double time = 0;

    std::string timeString = "";
    
    while (!(std::regex_match(timeString, std::regex("[0-9]+"))))
    {
        std::cout << ">";
        std::cin >> timeString;
    }
    time = std::stod(timeString);

    return time;
}