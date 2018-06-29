// Rachel Chiang
// Exercise 9
// 2016-03-16

#include "Chromosome.h"

Chromosome::Chromosome(std::string d)
	: data(d)
{
}

std::string Chromosome::getData() const
{
    return data;
}

Chromosome Chromosome::mutate() const
{
	int change = rand() % data.size();
	int mutChar = (int)data[change];
	int select = rand() % 2;

	std::ostringstream sout;
	for (int i = 0; i < change; ++i)
	{
		sout << data[i];
	}

	if (select == 0)
	{
		if (mutChar == 97)
		{
			mutChar = 122;
		}
		else
		{
			mutChar--;
		}
	}
	else
	{
		if (mutChar == 122)
		{
			mutChar = 97;
		}
		else
		{
			mutChar++;
		}
	}

	sout << (char)mutChar;

	for (int i = change + 1; i < data.size(); ++i)
	{
		sout << data[i];
	}

	Chromosome newChrom(sout.str());

	return newChrom;
}

Chromosome Chromosome::crossover(const Chromosome& c) const
{
    // implement crossover here
	int index = rand() % data.size();

	std::ostringstream sout;

	for (int i = 0; i < index; ++i)
	{
		sout << this->data[i];
	}
	for (int i = index; i < data.size(); ++i)
	{
		sout << c.data[i];
	}

	Chromosome newChrom(sout.str());
	return newChrom;
}

int Chromosome::fitness(const std::string& target) const
{
    // computes fitness by sum of differences of each character
    // smaller is better (0 is perfect)

    int diff = 0;
    for (int i = 0; i < data.size(); i++)
    {
        int change = std::abs(data[i] - target[i]);
        if (change > 13) // handles wrap around for letters
        {
            change = 26 - change;
        }
        diff += change;
    }
    return diff;
}

std::ostream& operator<<(std::ostream& os, const Chromosome& c)
{
    os << c.getData();
    return os;
}


void Chromosome::detFit(const std::string& target)
{
	fit = fitness(target);
}
int Chromosome::getFit() const
{
	return fit;
}