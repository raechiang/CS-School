/*
Rachel Chiang
Project 3: Interpolating Polynomials with Divided Difference
CS 301-01
*/
#include <iostream>
#include <regex>
#include <fstream>
#include <string>

#include "DividedDifference.h"

bool solve(std::istream& is);
int getNodeCount(std::string line);
void printResult(std::string table, std::string ip, std::string simp);

int main()
{
    // accept file string
    std::cout << "Please input the text file name to read from.\n";
    std::string name = "";

    // read from file and save points (up to 50)
    std::getline(std::cin, name);
    std::ifstream fis(name);
    if (fis.is_open())
    {
        solve(fis);

        fis.close();
    }
    else
    {
        std::cout << "Could not open file " << name << ".\n";
    }

    std::cout << "Thank you and goodbye." << std::endl;

    return 0;
}

bool solve(std::istream& is)
{
    std::string xIn;
    std::getline(is, xIn);
    // check size
    int size = getNodeCount(xIn);
    if (size <= 0)
    {
        std::cout << "Bad input." << std::endl;
        return false;
    }

    if (size > 50)
    {
        std::cout << "Maximum allowed number of nodes is 50." << std::endl;
    }

    std::string fxIn;
    std::getline(is, fxIn);

    if (size != getNodeCount(fxIn))
    {
        std::cout << "Bad input." << std::endl;
        return false;
    }

    DividedDifference dd(xIn, fxIn, size);

    printResult(dd.getTable(), dd.getInterpolatingPolynomial(), dd.getSimplifiedPolynomial());
}

/*
This counts the number of values given and has a loose invalid input check.
(This was used in Project 1.)
*/
int getNodeCount(std::string line)
{
    std::string::size_type nextInString = 0;
    std::size_t offset = 0;
    int numValsGiven = 0;

    // a lenient check that looks more complicated than it is that just makes
    // sure there is at least one number given in the line
    std::regex properFullForm(
        "(((([\\s]*(((\\+|-)?[0-9]+)(\\.([0-9]+)?)?)[\\s]*))|(([\\s]*(((\\+|-)?[0-9]*)(\\.([0-9]+)))[\\s]*))))+");

    if (std::regex_match(line, properFullForm))
    {
        while (offset + 1 < line.length())
        {
            // offset+1 because substr attempts to cut from beyond the offset
            double val = std::stod(line.substr(offset), &nextInString);
            offset = offset + nextInString;
            ++numValsGiven;
        }
    }

    return numValsGiven;
}

void printResult(std::string table, std::string ip, std::string simp)
{
    std::cout << "\n\nResult:\n\nTable:\n";
    std::cout << table;
    std::cout << "Interpolating Polynomial:\n";
    std::cout << ip << "\n\n";
    std::cout << "Simplified Polynomial:\n";
    std::cout << simp << "\n\n";
}