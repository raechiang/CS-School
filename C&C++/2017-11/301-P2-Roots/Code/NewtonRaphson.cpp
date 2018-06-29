#include "NewtonRaphson.h"

#include <iostream>
#include <cmath>
#include <sstream>
#include <string>

NewtonRaphson::NewtonRaphson(int p)
    : RootMethodOnePoint(p)
{
}

bool NewtonRaphson::findRoot(double x, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA)
{
    std::ofstream datFos;
    datFos.open(datName);
    std::ofstream csvFos;
    csvFos.open(csvName);
    std::ostringstream sout;
    sout << "x = " << x;
    outputHeader(datFos, csvFos, "Newton-Raphson", sout.str(), trueRoot);

    double fx = getf(x);
    
    output(datFos, csvFos, 0, x, fx, -1, -1);

    double prevX = x;
    for (int n = 1; n < maxIts; ++n)
    {
        prevX = x;
        double fp = getdfdx(x);
        if (fp == 0)
        {
            printf("f'(%f) = 0. Cannot divide by zero.\n", x);
            datFos << "# f'(x) = 0. Cannot divide by zero.\n";
            csvFos << "f'(x) = 0. Cannot divide by zero.\n";

            datFos.close();
            csvFos.close();
            return false;
        }
        double d = (fx / fp);
        x = (x - d);
        fx = getf(x);

        double relErr = getRelErr(prevX, x);

        output(datFos, csvFos, n, x, fx, (relErr * 100.0), (getTrueErr(trueRoot, x) * 100.0));
        if (relErr < minEA)
        {
            outputApproxRoot(datFos, csvFos, x);
            datFos.close();
            csvFos.close();
            return true;
        }
        
        if (fx == 0)
        {
            outputSolution(datFos, csvFos, x);
            datFos.close();
            csvFos.close();
            return true;
        }
    }

    outputEndIts(datFos, csvFos);

    datFos.close();
    csvFos.close();
    return false;
}
