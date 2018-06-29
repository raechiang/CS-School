#include "Secant.h"

#include <cmath>
#include <iostream>
#include <sstream>

Secant::Secant(int p)
    : RootMethodTwoPoints(p)
{}

bool Secant::findRoot(double a, double b, std::string datName, std::string csvName, double trueRoot, int maxIts, double minEA)
{
    std::ofstream datFos;
    datFos.open(datName);
    std::ofstream csvFos;
    csvFos.open(csvName);
    std::ostringstream sout;
    sout << "a = " << a << ", b = " << b;
    outputHeader(datFos, csvFos, "Secant", sout.str(), trueRoot);

    double fa = getf(a);
    double fb = getf(b);
    if (abs(fa) > abs(fb))
    {
        swap(a, b, fa, fb);
    }

    output(datFos, csvFos, 0, a, fa, -1, -1);
    output(datFos, csvFos, 1, b, fb, -1, -1);

    double prevC = b;

    for (int n = 2; n < maxIts; ++n)
    {
        if (abs(fa) > abs(fb))
        {
            swap(a, b, fa, fb);
        }
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
        a = b;
        fa = fb;
        
        double fc = getf(c);
        if (n != 2)
        {
            double relErr = getRelErr(prevC, c);

            output(datFos, csvFos, n, c, fc, (relErr * 100.0), (getTrueErr(trueRoot, c)));

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
        b = c;
        fb = fc;

        if (fb == 0)
        {
            outputSolution(datFos, csvFos, c);
            datFos.close();
            csvFos.close();
            return true;
        }

        prevC = c;
    }

    outputEndIts(datFos, csvFos);

    datFos.close();
    csvFos.close();
    return false;
}

void Secant::swap(double &a, double &b, double &fa, double &fb)
{
    double aTemp = a;
    double faTemp = fa;
    a = b;
    fa = fb;
    b = aTemp;
    fb = faTemp;
}