#ifndef MODIFIEDSECANT_H
#define MODIFIEDSECANT_H

#include "RootMethodOnePoint.h"

class ModifiedSecant : public RootMethodOnePoint
{
    public:
        ModifiedSecant(int p);
        bool findRoot(double x, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA);
};

#endif