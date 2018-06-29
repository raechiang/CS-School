#include "ModifiedSecant.h"

#include <iostream>
#include <cmath>
#include <sstream>
#include <string>

ModifiedSecant::ModifiedSecant(int p)
    : RootMethodOnePoint(p)
{}

bool ModifiedSecant::findRoot(double x, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA)
{
    std::ofstream datFos;
    datFos.open(datName);
    std::ofstream csvFos;
    csvFos.open(csvName);
    std::ostringstream sout;
    sout << "x = " << x;
    outputHeader(datFos, csvFos, "Modified Secant", sout.str(), trueRoot);

    double fx = getf(x);
    double xdelta = x * getDelta();
    double fxdeltax = getf(x + xdelta);

    output(datFos, csvFos, 0, x, fx, -1, -1);

    double prevC = x;
    for (int n = 1; n < maxIts; ++n)
    {
        if (fxdeltax == fx)
        {
            std::cout << "f(xdeltax) and f(x) are the same. Cannot divide by zero." << std::endl;

            datFos << "# f(xdeltax) and f(x) are the same. Cannot divide by zero.\n";

            csvFos << "f(xdeltax) and f(x) are the same. Cannot divide by zero.\n";

            datFos.close();
            csvFos.close();
            return false;
        }
        double c = (xdelta) / (fxdeltax - fx);
        c = fx * c;
        c = x - c;
        double fc = getf(c);

        if (n != 1)
        {
            double relErr = getRelErr(prevC, c);

            output(datFos, csvFos, n, c, fc, (relErr * 100.0), (getTrueErr(trueRoot, c) * 100.0));

            if (relErr < minEA)
            {
                outputApproxRoot(datFos, csvFos, c);
                datFos.close();
                csvFos.close();
                return true;
            }
        }
        else
        {
            output(datFos, csvFos, n, c, fc, -1, -1);
        }

        if (fc == 0)
        {
            outputSolution(datFos, csvFos, c);
            datFos.close();
            csvFos.close();
            return true;
        }

        x = c;
        fx = fc;
        xdelta = x * getDelta();
        fxdeltax = getf(x + xdelta);
        prevC = x;
    }

    outputEndIts(datFos, csvFos);

    datFos.close();
    csvFos.close();
    return false;
}