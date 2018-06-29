#ifndef NEWTONRAPHSON_H
#define NEWTONRAPHSON_H

#include "RootMethodOnePoint.h"

class NewtonRaphson : public RootMethodOnePoint
{
    public:
        NewtonRaphson(int p);
        bool findRoot(double x, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA);
};

#endif