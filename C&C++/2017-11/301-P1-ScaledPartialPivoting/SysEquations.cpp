/*
Rachel Chiang
CS 301.01
2017-10-30
Project 1
Scaled Partial Pivoting for Gaussian Elimination
--------------------------------------------------------------------------------
This file corresponds to SysEquations.h. It represents a system of equations
with an nxn matrix of coefficients along with the b vector; that is,
coefficients[x's]=bVector. Aside from the constructor and destructor, there are
four functions that are implemented: sppGauss(), solve(), writeMatrix(), and
findSolution().
*/
#include "SysEquations.h"

#include <iostream>
#include <cmath>

// Constructor and Destructor
/*
This is the constructor.
*/
SysEquations::SysEquations(double** c, double* b, int s)
    : size(s)
{
    coefficients = new double*[size];
    bVector = new double[size];
    lVector = new int[size];

    for (int i = 0; i < size; ++i)
    {
        coefficients[i] = new double[size];
        *(bVector + i) = *(b + i);
        *(lVector + i) = i;
    }

    for (int i = 0; i < size; ++i)
    {
        for (int j = 0; j < size; ++j)
        {
            *(*(coefficients + j) + i) = *(*(c + j) + i);
        }
    }
}
/*
This is the destructor.
*/
SysEquations::~SysEquations()
{
    for (int i = 0; i < size; ++i)
    {
        delete[] coefficients[i];
        coefficients[i] = nullptr;
    }
    delete[] coefficients;
    coefficients = nullptr;

    delete[] bVector;
    bVector = nullptr;

    delete[] lVector;
    lVector = nullptr;
}

// Scaled Partial Pivoting with Gaussian Elimination and Backwards Substitution
/*
*/
bool SysEquations::sppGauss()
{
    double* scales = new double[size];

    // calculate scale matrix
    for (int i = 0; i < size; ++i)
    {
        
        double smax = 0;
        for (int j = 0; j < size; ++j)
        {
            if ((*(*(coefficients + j) + i)) > smax)
            {
                smax = *(*(coefficients + j) + i);
            }
        }
        scales[i] = smax;
    }

    for (int k = 0; k < size - 1; ++k) // work on col k
    {
        // want to find biggest ratio for row selection
        double rmax = 0;
        int j = 0;
        for (int i = k; i < size; ++i)
        {
            if (scales[lVector[i]] == 0)
            {
                return false;
            }

            double r = abs(
                (*(*(coefficients + k) + lVector[i])) / (scales[lVector[i]])
            );
            if (r > rmax)
            {
                rmax = r; // biggest ratio
                j = i;
            }
        }
        // swap l[k] and l[j] values
        int temp = lVector[j];
        lVector[j] = lVector[k];
        lVector[k] = temp;

        // the actual gauss part
        for (int i = k + 1; i < size; ++i)
        {
            // get the common mult
            if ((*(*(coefficients + k) + lVector[k])) == 0)
            {
                return false;
            }

            double xmult =
                (*(*(coefficients + k) + lVector[i]))
                    / (*(*(coefficients + k) + lVector[k]));
            (*(*(coefficients + k) + lVector[i])) = xmult;
            
            // adjust subsequent coefficients
            for (int j = k + 1; j < size; ++j)
            {
                (*(*(coefficients + j) + lVector[i])) -=
                    (xmult * ((*(*(coefficients + j) + lVector[k]))));
            }
        }
    }

    delete[] scales;
    scales = nullptr;

    return true;
}
/*
This affects the changes to the bVector and then conducts backwards substitution
to solve for the variables x_i.
*/
double* SysEquations::solve()
{
    double* x = new double[size];
    
    // adjust b
    for (int k = 0; k < size - 1; ++k)
    {
        for (int i = k + 1; i < size; ++i)
        {
            *(bVector + lVector[i]) -=
                (*(bVector + lVector[k]))
                * (*(*(coefficients + k) + lVector[i]));
        }
    }

    // find x_i values
    int n = size - 1;
    x[n] = bVector[lVector[n]]
        / (*(*(coefficients + n) + lVector[n]));
    for (int i = n - 1; i >= 0; --i)
    {
        double sum = bVector[lVector[i]];
        for (int j = i + 1; j < size; ++j)
        {
            sum -= ((*(*(coefficients + j) + lVector[i])) * x[j]);
        }
        x[i] = sum / (*(*(coefficients + i) + lVector[i]));
    }

    return x;
}

// For Solving and Writing the Solution
/*
This will find the solution by one function call if it exists.
*/
void SysEquations::findSolution()
{
    if (sppGauss())
    {
        std::cout << "Solution:" << std::endl;
        double* solution = solve();
        for (int i = 0; i < size; ++i)
        {
            printf(" x%.3d = %10.3f\n", i, (*(solution + i)));
        }
        delete[] solution;
        solution = nullptr;
    }
    else
    {
        std::cout << "There is no solution." << std::endl;
    }
}