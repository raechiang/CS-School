// Rachel Chiang
// Exercise 9
// 2016-03-16

#ifndef CHROMOSOME_H
#define CHROMOSOME_H

#include <string>
#include <iostream>
#include <sstream>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <algorithm>

class Chromosome
{
private:
    std::string data;

	int fit;
public:
    Chromosome(std::string d);
    
    std::string getData() const;
    Chromosome mutate() const;
    Chromosome crossover(const Chromosome& other) const;
    int fitness(const std::string& target) const;

	void detFit(const std::string& target);
	int getFit() const;
};

std::ostream& operator<<(std::ostream& os, const Chromosome& c);

#endif