#ifndef FALSEPOSITION_H
#define FALSEPOSITION_H

#include "RootMethodTwoPoints.h"

class FalsePosition : public RootMethodTwoPoints
{
    private:
        int part;
        bool haveSameSign(double x, double y);
    public:
        FalsePosition(int p);
        bool findRoot(double a, double b, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA);
};

#endif
