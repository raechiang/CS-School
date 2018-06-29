/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This is a simple class that creates a string representation of the state of a
board by randomly generating row positions for a queen.
*/
#include "RandomStateGenerator.h"

#include <cstdlib>
#include <ctime>
#include <sstream>

#include "Constants.h"

/*
Constructor. Seeds the random number generator.
*/
RandomStateGenerator::RandomStateGenerator()
{
    srand(time(NULL));
}

/*
This returns a state, which is a string representation of a board of n queens
situated in random row locations in each column. The positions themselves
correspond to ASCII characters--capital letters A-U.
*/
std::string RandomStateGenerator::getRandState()
{
    std::ostringstream sout;

    if (Constants::N >= 1 && Constants::N <= 61)
    {
        for (int i = 0; i < Constants::N; ++i)
        {
            sout << ((char)((rand() % Constants::N) + Constants::OFFSET_A));
        }

        return sout.str();
    }
    else
    {
        std::cout << "Bad n. n has been defaulted to 21." << std::endl;
        int size = 21;

        for (int i = 0; i < size; ++i)
        {
            sout << ((char)((rand() % Constants::N) + Constants::OFFSET_A));
        }

        return sout.str();
    }
}