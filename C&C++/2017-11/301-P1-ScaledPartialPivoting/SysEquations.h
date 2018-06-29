/*
Rachel Chiang
CS 301.01
2017-10-30
Project 1
Scaled Partial Pivoting for Gaussian Elimination
--------------------------------------------------------------------------------
This is the header file to SysEquations.cpp. It represents a system of equations
with an nxn matrix of coefficients along with the b vector; that is,
coefficients[x's]=bVector. coefficients is a two-dimensional array of doubles
created dynamically with pointers. Similarly, the bVector consists of double
pointers and the lVector consists of integer pointers. The lVector contains
index recordings corresponding to the pivot rows during scaled partial pivoting.
It is created and used in sppGauss() and used in solve(). The integer value size
simply holds the number of x variables there are (in other words, the number of
equations, or n).
*/
#ifndef SYSEQUATIONS_H
#define SYSEQUATIONS_H

class SysEquations
{
    private:
        double** coefficients; // representation of coefficients of the x's
        double* bVector; // representation of b
        int* lVector; // for the scaled partial pivoting gauss
        int size; // size = n, such that nxn is the dimension of the matrix
    public:
        // constructor, destructor
        SysEquations(double** c, double* b, int s);
        ~SysEquations();
        // utility
        // scaled partial pivoting for gaussian, backwards substitution
        bool sppGauss();
        double* solve();
        // solving and solution writing
        void findSolution();
};

#endif