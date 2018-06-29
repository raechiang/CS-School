#ifndef ROOTMETHODTWOPOINTS_H
#define ROOTMETHODTWOPOINTS_H

#include "RootMethod.h"
#include <iostream>

class RootMethodTwoPoints : public RootMethod
{
    public:
        RootMethodTwoPoints(int p) : RootMethod(p) {};
        virtual bool findRoot(double a, double b, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA) = 0;
};

#endif