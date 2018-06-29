/*
Rachel Chiang
CS 301.01
Project 2
Numerical Methods for Finding Roots
--------------------------------------------------------------------------------
Note, this does spit out a lot of .dat and .csv files.
*/
#include <iostream>
#include <stdio.h>
#include <sstream>
#include <string>

#include "Spec.h"
#include "Bisection.h"
#include "NewtonRaphson.h"
#include "Secant.h"
#include "FalsePosition.h"
#include "ModifiedSecant.h"

void runBisection();
void runNewtonRaphson();
void runSecant();
void runFalsePosition();
void runModifiedSecant();
std::string makeFileName(std::string method, int i, std::string type);

int main()
{
    // Bisection
    runBisection();

    printf("\n\n\n\n");

    // Newton-Raphson
    runNewtonRaphson();
    
    printf("\n\n\n\n");
    
    // Secant
    runSecant();

    printf("\n\n\n\n");

    // False-Position
    runFalsePosition();

    printf("\n\n\n\n");

    // ModifiedSecant
    runModifiedSecant();
    
    printf("\n\n\n\n");

    return 0;
}

std::string makeFileName(std::string method, int i, std::string type)
{
    std::ostringstream sout;
    sout << method << i << "." << type;
    return sout.str();
}

void runBisection()
{
    double piece = (Spec::a::bBound - Spec::a::aBound) / Spec::a::nRootsInI;
    // Bisection
    // Part A
    Bisection bisA(0);
    // 3 is num of roots in [0,4]
    for (int i = 0; i < Spec::a::nRootsInI; ++i)
    {
        bisA.findRoot(Spec::a::aBound + (i * piece),
                      Spec::a::aBound + ((i + 1) * piece),
                      makeFileName("bisA", i, "dat"),
                      makeFileName("bisA", i, "csv"),
                      Spec::a::roots[i],
                      Spec::maxIts, Spec::ea);
    }
    // Part B
    std::cout << "\n";
    Bisection bisB(1);
    // 1 root in [120,130]
    bisB.findRoot(Spec::b::aBound, Spec::b::bBound,
                  makeFileName("bisB", 0, "dat"),
                  makeFileName("bisB", 0, "csv"),
                  Spec::b::root,
                  Spec::maxIts, Spec::ea);
}

void runNewtonRaphson()
{
    double piece = (Spec::a::bBound - Spec::a::aBound) / Spec::a::nRootsInI;
    // Newton-Raphson
    // Part A
    NewtonRaphson nrA(0);
    // 3 is num of roots in [0,4]
    for (int i = 0; i < Spec::a::nRootsInI; ++i)
    {
        nrA.findRoot(Spec::a::aBound + (i * piece),
                     makeFileName("nwrA", i, "dat"),
                     makeFileName("nwrA", i, "csv"),
                     Spec::a::roots[i],
                     Spec::maxIts, Spec::ea);
    }
    // Part B
    std::cout << "\n";
    NewtonRaphson nrB(1);
    // 1 root in [120,130]
    nrB.findRoot((Spec::b::aBound + Spec::b::bBound) / 2.0,
                 makeFileName("nwrB", 0, "dat"),
                 makeFileName("nwrB", 0, "csv"),
                 Spec::b::root,
                 Spec::maxIts, Spec::ea);
}

void runSecant()
{
    double piece = (Spec::a::bBound - Spec::a::aBound) / Spec::a::nRootsInI;
    // Secant
    // Part A
    Secant sA(0);
    for (int i = 0; i < Spec::a::nRootsInI; ++i)
    {
        sA.findRoot(Spec::a::aBound + (i * piece),
                    Spec::a::aBound + ((i + 1) * piece),
                    makeFileName("secA", i, "dat"),
                    makeFileName("secA", i, "csv"),
                    Spec::a::roots[i],
                    Spec::maxIts, Spec::ea);
    }
    // Part B
    std::cout << "\n";
    Secant sB(1);
    // 1 root in [120,130]
    sB.findRoot(Spec::b::aBound, Spec::b::bBound,
                makeFileName("secB", 0, "dat"),
                makeFileName("secB", 0, "csv"),
                Spec::b::root,
                Spec::maxIts, Spec::ea);
}

void runFalsePosition()
{
    double piece = (Spec::a::bBound - Spec::a::aBound) / Spec::a::nRootsInI;
    // False-Position
    // Part A
    FalsePosition fpA(0);
    for (int i = 0; i < Spec::a::nRootsInI; ++i)
    {
        fpA.findRoot(Spec::a::aBound + (i * piece),
                     Spec::a::aBound + ((i + 1) * piece),
                     makeFileName("fpoA", i, "dat"),
                     makeFileName("fpoA", i, "csv"),
                     Spec::a::roots[i],
                     Spec::maxIts, Spec::ea);
    }
    // Part B
    std::cout << "\n";
    FalsePosition fpB(1);
    // 1 root in [120,130]
    fpB.findRoot(Spec::b::aBound, Spec::b::bBound,
                 makeFileName("fpoB", 0, "dat"),
                 makeFileName("fpoB", 0, "csv"),
                 Spec::b::root,
                 Spec::maxIts, Spec::ea);
}

void runModifiedSecant()
{
    double piece = (Spec::a::bBound - Spec::a::aBound) / Spec::a::nRootsInI;
    // Modified Secant
    // Part A
    ModifiedSecant msA(0);
    // 3 roots in [0,4]
    for (int i = 0; i < Spec::a::nRootsInI; ++i)
    {
        msA.findRoot(Spec::a::aBound + ((i + 0.25) * piece),
                     makeFileName("mseA", i, "dat"),
                     makeFileName("mseA", i, "csv"),
                     Spec::a::roots[i],
                     Spec::maxIts, Spec::ea);
    }
    // Part B
    std::cout << "\n";
    ModifiedSecant msB(1);
    msB.findRoot((Spec::b::aBound + Spec::b::bBound) / 2.0,
                 makeFileName("mseB", 0, "dat"),
                 makeFileName("mseB", 0, "csv"),
                 Spec::b::root,
                 Spec::maxIts, Spec::ea);
}