#ifndef CONWAY_H
#define CONWAY_H

#include <string>

class Conway
{
private:
	bool** board;
	int rows;
	int cols;
public:
	// Constructors and destructor
	Conway(std::string infilename);
	Conway(const Conway& other);
	~Conway();
	// operator overload
	Conway& operator=(const Conway& other);

	bool alive(int row, int col) const;
	void flip(int row, int col);
	std::string str() const;
	void step();
	void play(int n);

	// Extra functions
	bool inBound(int row, int col, const Conway& cur) const;
	int checkNeighbors(int row, int col, const Conway& cur);
};

#endif