#include "FalsePosition.h"

#include <iostream>
#include <cmath>
#include <fstream>
#include <string>
#include <sstream>

FalsePosition::FalsePosition(int p)
    : RootMethodTwoPoints(p)
{
}

bool FalsePosition::findRoot(double a, double b, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA)
{
    std::ofstream datFos;
    datFos.open(datName);
    std::ofstream csvFos;
    csvFos.open(csvName);
    std::ostringstream sout;
    sout << "a = " << a << ", b = " << b;
    outputHeader(datFos, csvFos, "False-Position", sout.str(), trueRoot);

    double fa = getf(a);
    double fb = getf(b);

    if (haveSameSign(fa, fb))
    {
        std::cout << "f(a) and f(b) have the same sign. False-Position requires initial values to bracket a root." << std::endl;
        std::cout << "f(a) = " << fa << ", f(b) = " << fb << std::endl;

        datFos << "# f(a) and f(b) have the same sign. a and b must bracket a root.\n";
        datFos << "# f(a) = " << fa << "\n";
        datFos << "# f(b) = " << fb << "\n";

        csvFos << "f(a) and f(b) have the same sign. False-Position requires initial values to bracket a root.\n";
        csvFos << "f(a) = " << fa << "\n";
        csvFos << "f(b) = " << fb << "\n";

        datFos.close();
        csvFos.close();
        return false;
    }

    double prevC = b;

    for (int n = 0; n < maxIts; ++n)
    {
        if (fa == fb)
        {
            std::cout << "f(a) and f(b) are the same. Cannot divide by zero." << std::endl;
            std::cout << "f(a) = " << fa << ", f(b) = " << fb << std::endl;

            datFos << "# f(a) and f(b) are the same. Cannot divide by zero.\n";
            datFos << "# f(a) = " << fa << "\n";
            datFos << "# f(b) = " << fb << "\n";

            csvFos << "f(a) and f(b) are the same. Cannot divide by zero.\n";
            csvFos << "f(a) = " << fa << "\n";
            csvFos << "f(b) = " << fb << "\n";

            datFos.close();
            csvFos.close();
            return false;
        }

        double c = ((b - a) / (fb - fa));
        c = c * fb;
        c = b - c;
        double fc = getf(c);

        if (n != 0)
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

        if (haveSameSign(fa, fc))
        {
            a = c;
            fa = fc;
        }
        else
        {
            b = c;
            fb = fc;
        }
        prevC = c;
    }

    outputEndIts(datFos, csvFos);

    datFos.close();
    csvFos.close();
    return false;
}

bool FalsePosition::haveSameSign(double x, double y)
{
    if (x < 0 && y < 0) // both negative
    {
        return true;
    }

    if (x > 0 && y > 0) // both positive
    {
        return true;
    }

    if (x == y)
    {
        return true;
    }

    return false; // distinct
}