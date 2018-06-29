#include "Conway.h"

#include <iostream>
#include <fstream>
#include <sstream>

/*
   Given a string, this constructor will read in the information from the file with that string name.
   The first two integers of the file will determine the size of the board, and the subsequent 1's and
   0's will determine whether a cell is alive (1, true) or dead (0, false).
*/
Conway::Conway(std::string infilename)
{
	std::ifstream fin(infilename.c_str());

	if (!(fin))
	{
		std::cout << "Invalid file used." << std::endl;
		// Default.
		rows = 0;
		cols = 0;
		board = nullptr;
	}
	else
	{
		int readIn;

		// size of array
		fin >> rows;
		fin >> cols;
		
		// Create the board
		board = new bool*[rows];
		for (int i = 0; i < rows; ++i)
		{
			board[i] = new bool[cols];
		}

		// Set the values of the board
		for (int r = 0; r < rows; ++r)
		{
			for (int c = 0; c < cols; ++c)
			{
				fin >> readIn;
				
				if (readIn == 0)
				{
					board[r][c] = false;
				}
				else
				{
					board[r][c] = true;
				}
			}
		}
	}

}

/*
   This constructor copies another Conway.
*/
Conway::Conway(const Conway& other)
	: board(new bool*[other.rows]), rows(other.rows), cols(other.cols)
{
	// Create the board
	for (int i = 0; i < rows; ++i)
	{
		this->board[i] = new bool[cols];
	}

	// Copy the values from the other board into this board
	for (int r = 0; r < rows; ++r)
	{
		for (int c = 0; c < cols; ++c)
		{
			this->board[r][c] = other.board[r][c];
		}
	}
}

/*
   This is the Conway destructor.
*/
Conway::~Conway()
{
	// Step through board array, delete
	for (int i = 0; i < rows; ++i)
	{
		delete[] board[i];
		board[i] = nullptr;
	}
	// Delete the board
	delete[] board;
	board = nullptr;
	
	rows = 0;
	cols = 0;
}

/*
   This function produces a string representing the board. If it is alive (true), an X will be saved to the
   sout ostringstream, and if it is dead (false), a . will be saved. Spaces will only be printed if it is
   not the end of the board.
*/
std::string Conway::str() const
{
	std::ostringstream sout;

	for (int r = 0; r < rows; ++r)
	{
		for (int c = 0; c < cols; ++c)
		{
			if (board[r][c])
			{
				sout << "X";
			}
			else
			{
				sout << ".";
			}

			if (c != cols - 1)
			{
				sout << " ";
			}
		}
		sout << "\n";
	}

	return sout.str();
}

/*
   Overload of the assignment operator for copying.
*/
Conway& Conway::operator=(const Conway& other)
{
	// Deals with self copying
	if (this == &other)
	{
		return *this;
	}

	if (this->rows != other.rows || this->cols != other.cols)
	{
		for (int i = 0; i < this->rows; ++i)
		{
			delete[] this->board[i];
			this->board[i] = nullptr;
		}
		delete[] this->board;
		this->board = nullptr;

		this->rows = other.rows;
		this->cols = other.cols;
		this->board = new bool*[this->rows];
		for (int i = 0; i < this->rows; ++i)
		{
			this->board[i] = new bool[this->cols];
		}
	}

	for (int r = 0; r < rows; ++r)
	{
		for (int c = 0; c < cols; ++c)
		{
			this->board[r][c] = other.board[r][c];
		}
	}

	return *this;
}

/*
   This will return whether the cell given is alive or dead.
*/
bool Conway::alive(int row, int col) const
{
	return board[row][col];
}

/*
   Switches whether a cell is alive or dead.
*/
void Conway::flip(int row, int col)
{
	if (inBound(row, col, *this))
	{
		if (board[row][col])
		{
			board[row][col] = false;
		}
		else
		{
			board[row][col] = true;
		}
	}
}

/*
   This function goes through each of the cells and depending on the current state of the board, the cell 
   will change.
    1. Any living cell with fewer than two living neighbors dies
    2. Any living cell with two or three living neighbors lives on
    3. Any living cell with more than three living neighbors dies
    4. Any dead cell with exactly three living neighbors comes to life
*/
void Conway::step()
{
	Conway current(*this);

	for (int r = 0; r < rows; ++r)
	{
		for (int c = 0; c < cols; ++c)
		{
			int lifeCounter = checkNeighbors(r, c, current);

			if (current.board[r][c])
			{
				// Fewer than 2 living neighbors => death
				if (lifeCounter < 2)
				{
					flip(r, c);
				}
				// More than 3 living neighbors => death
				else if (lifeCounter > 3)
				{
					flip(r, c);
				}
				
				// 2 or 3 living neighbors => live (do nothing)
			}
			else
			{
				// Exactly 3 living neighbors => life
				if (lifeCounter == 3)
				{
					flip(r, c);
				}
			}
		}
	}

}

void Conway::play(int n)
{
	std::cout << "Initial State:\n" << str() << std::endl;
	for (int i = 0; i < n; ++i)
	{
		step();
		std::cout << "Iteration: " << i + 1 << std::endl;
		std::cout << str() << std::endl;
	}
}

/*
   Bounds checking.
*/
bool Conway::inBound(int row, int col, const Conway& cur) const
{
	if (row >= cur.rows || col >= cur.cols
		|| row < 0 || col < 0)
	{
		return false;
	}
	return true;
}

/*
   Count the living.
*/
int Conway::checkNeighbors(int row, int col, const Conway& cur)
{
	int lifeCounter = 0;

	// check row + 1 (south directions)
	for (int i = -1; i < 2; ++i)
	{
		if (inBound(row + 1, col + i, cur))
		{
			if (cur.board[row + 1][col + i])
			{
				++lifeCounter;
			}
		}
	}
	// check row - 1 (north directions)
	for (int i = -1; i < 2; ++i)
	{
		if (inBound(row - 1, col + i, cur))
		{
			if (cur.board[row - 1][col + i])
			{
				++lifeCounter;
			}
		}
	}
	// check row + 0 (inline directions)
	if (inBound(row, col - 1, cur))
	{
		if (cur.board[row][col - 1])
		{
			++lifeCounter;
		}
	}
	if (inBound(row, col + 1, cur))
	{
		if (cur.board[row][col + 1])
		{
			++lifeCounter;
		}
	}

	return lifeCounter;
}