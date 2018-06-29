#include "RootMethod.h"

#include "Spec.h"
#include <string>
#include <cmath>

RootMethod::RootMethod(int p)
    : part(p)
{
    if (p < Spec::PART_A || p > Spec::PART_B)
    {
        part = 0;
    }
}

double RootMethod::getf(double x)
{
    if (part == Spec::PART_A) // part a
    {
        return Spec::a::f(x);
    }
    else // part b
    {
        return Spec::b::f(x);
    }
}

double RootMethod::getdfdx(double x)
{
    if (part == Spec::PART_A)
    {
        return Spec::a::dfdx(x);
    }
    else
    {
        return Spec::b::dfdx(x);
    }
}

double RootMethod::getDelta()
{
    return Spec::delta;
}

void RootMethod::outputHeader(std::ofstream &datFos, std::ofstream &csvFos, std::string method, std::string initial, double trueRoot)
{
    std::string fString = "";
    std::string p = "";
    if (part == Spec::PART_A)
    {
        p = "A";
        fString = Spec::a::fToString();
    }
    else
    {
        p = "B";
        fString = Spec::b::fToString();
    }
    std::cout << method << " Method on Part " << p << ": ";
    std::cout << fString << "\n";
    std::cout << "True Root: " << trueRoot << "\n";
    std::cout << "Initial: " << initial << "\n";
    printf("%s\t%s\t\t%s\t\t%s\t\t%s\n", "n", "x", "fx", "%rErr", "%tErr");

    datFos << "# " << method << "\n";
    datFos << "# Errors for finding roots in " << fString << "\n";
    datFos << "# True Root: " << trueRoot << "\n";
    datFos << "# Initial: " << initial << "\n";
    datFos << "# Data for iteration number, % relative error, and % true error\n";
    datFos << "# n\t%rErr\t%tErr\n";

    csvFos << method << "\n";
    csvFos << "Table for roots in " << fString << "\n";
    csvFos << "True Root: " << trueRoot << "\n";
    csvFos << "Initial: ," << initial << "\n";
    csvFos << "n,x,fx,%rErr,%tErr\n";
}

void RootMethod::output(std::ofstream &datFos, std::ofstream &csvFos, int n, double x, double fx, double percRE, double percTE)
{
    printf("%d\t%f\t%f\t", n, x, fx);
    datFos << n << "\t";
    csvFos << n << "," << x << "," << fx << ",";
    if (percRE < 0)
    {
        printf("-\t\t");
        datFos << "-\t";
        csvFos << "-,";
    }
    else
    {
        printf("%f\t", percRE);
        datFos << percRE << "\t";
        csvFos << percRE << ",";
    }

    if (percTE < 0)
    {
        printf("-\n");
        datFos << "-\n";
        csvFos << "-\n";
    }
    else
    {
        printf("%f\n", percTE);
        datFos << percTE << "\n";
        csvFos << percTE << "\n";
    }
}

double RootMethod::getRelErr(double prevApprox, double currApprox)
{
    if (currApprox == 0)
    {
        return -1;
    }
    else
    {
        return abs( (currApprox - prevApprox) / currApprox );
    }
}

double RootMethod::getTrueErr(double trueRoot, double currApprox)
{
    if (trueRoot == 0)
    {
        return -1;
    }
    else
    {
        return abs( (trueRoot - currApprox) / trueRoot );
    }
}

void RootMethod::outputSolution(std::ofstream &datFos, std::ofstream &csvFos, double x)
{
    std::cout << "Root: " << x << std::endl;
    datFos << "# Root: " << x << "\n";
    csvFos << "Root: " << x << "\n";
}

void RootMethod::outputApproxRoot(std::ofstream &datFos, std::ofstream &csvFos, double x)
{
    std::cout << "%rErr < " << (Spec::ea * 100.0) << "%\n";
    std::cout << "Approximated Root: " << x << std::endl;
    datFos << "# Approximated Root: " << x << "\n";
    csvFos << "Approximated Root: " << x << "\n";
}

void RootMethod::outputEndIts(std::ofstream &datFos, std::ofstream &csvFos)
{
    std::cout << "Could not approximate root." << std::endl;
    datFos << "# Root could not be approximated.\n";
    csvFos << "Root could not be approximated.\n";
}