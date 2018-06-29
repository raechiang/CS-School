#ifndef SECANT_H
#define SECANT_H

#include "RootMethodTwoPoints.h"

class Secant : public RootMethodTwoPoints
{
    private:
        void swap(double &a, double &b, double &fa, double &fb);
    public:
        Secant(int p);
        bool findRoot(double a, double b, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA);
};

#endif