#ifndef ROOTMETHODONEPOINT_H
#define ROOTMETHODONEPOINT_H

#include "RootMethod.h"

class RootMethodOnePoint : public RootMethod
{
    public:
        RootMethodOnePoint(int p) : RootMethod(p) {};
        virtual bool findRoot(double x, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA) = 0;
};

#endif