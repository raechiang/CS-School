// Rachel Chiang
// CS 256-EX5
// 02-21-2016

#ifndef RAT_H
#define RAT_H

#include <iostream>
#include <string>
#include <sstream>

class RatTester;

class rat
{
    friend class RatTester;

private:
    // declare private members here
	int num;
	int den;
	void reduce();
	int gcd(int x, int y);
	int lcm(int x, int y);
public:
    // constructors
	rat();
	rat(int n);
	rat(int n, int d);

	// getters
	int getNum() const;
	int getDen() const;
	
	// math magic
	rat reciprocal() const;
	rat operator+(const rat& right) const;
	rat operator-(const rat& right) const;
	rat operator*(const rat& right) const;
	rat operator/(const rat& right) const;

	// relational
	bool operator==(const rat& right) const;
	bool operator!=(const rat& right) const;
	bool operator>(const rat& right) const;
	bool operator<(const rat& right) const;
	bool operator>=(const rat& right) const;
	bool operator<=(const rat& right) const;

	// string
	std::string str() const;
};

#endif