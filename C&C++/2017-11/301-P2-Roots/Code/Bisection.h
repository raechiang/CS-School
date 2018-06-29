#ifndef BISECTION_H
#define BISECTION_H

#include "RootMethodTwoPoints.h"

class Bisection : public RootMethodTwoPoints
{
    private:
        bool haveSameSign(double x, double y);
    public:
        Bisection(int p);
        bool findRoot(double a, double b, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA);
};

#endif
