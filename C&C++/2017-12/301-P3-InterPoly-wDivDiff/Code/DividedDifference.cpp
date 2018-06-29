#include "DividedDifference.h"

#include <sstream>
#include <limits>
#include <vector>
#include "Polynomial.h"

DividedDifference::DividedDifference(std::string xString, std::string fxString, int s)
    : size(s)
{
    x = new double[size];
    a = new double*[size];

    std::string::size_type xNextInString = 0;
    std::size_t xOffset = 0;
    std::string::size_type fxNextInString = 0;
    std::size_t fxOffset = 0;

    for (int i = 0; i < size; ++i)
    {
        *(x + i) = std::stod(xString.substr(xOffset), &xNextInString);
        xOffset = xOffset + xNextInString;

        a[i] = new double[size];
    }

    for (int i = 0; i < size; ++i)
    {
        *(*(a + 0) + i) = std::stod(fxString.substr(fxOffset), &fxNextInString);
        fxOffset = fxOffset + fxNextInString;
    }

    solve();
}

DividedDifference::~DividedDifference()
{
    delete[] x;
    x = nullptr;

    for (int i = 0; i < size; ++i)
    {
        delete[] a[i];
        a[i] = nullptr;
    }
    delete[] a;
    a = nullptr;
}

void DividedDifference::solve()
{
    for (int j = 1; j < size; ++j)
    {
        for (int i = 0; i < size - j; ++i)
        {
            *(*(a + j) + i) = (( *(*(a + (j-1)) + (i+1)) ) - ( *(*(a + (j-1)) + i) )) / (( *(x + (i+j)) ) - ( *(x + i) ));
        }
    }
}

std::string DividedDifference::getTable()
{
    std::ostringstream sout;
    sout.precision(5);

    // table header
    sout << "x\t";
    for (int i = 0; i < size; ++i)
    {
        sout << "f[";
        for (int j = 0; j < i; ++j)
        {
            sout << ",";
        }
        sout << "]\t";
    }

    sout << "\n";

    // table elements
    int rowSize = 2 * size;
    std::vector<std::vector<double>> elements(rowSize);
    for (int i = 0; i < rowSize; ++i)
    {
        elements[i].resize(size);
    }

    for (int i = 0; i < rowSize; ++i)
    {
        for (int j = 0; j < size; ++j)
        {
            elements[i][j] = std::numeric_limits<double>::infinity();
        }
    }

    for (int i = 0; i < size; ++i)
    {
        int row = i * 2;
        for (int j = 0; j < size - i; ++j)
        {
            elements[row][j] = *(*(a + j) + i);
            row++;
        }
    }

    for (int i = 0; i < rowSize; ++i)
    {
        for (int j = 0; j < size; ++j)
        {
            if (j == 0)
            {
                if (i % 2 == 0)
                {
                    sout << *(x + (i / 2)) << "\t";
                }
                else
                {
                    sout << " \t";
                }
            }

            double nextValue = elements[i][j];
            if (nextValue == std::numeric_limits<double>::infinity())
            {
                sout << " \t";
            }
            else
            {
                sout << nextValue << "\t";
            }
        }
        sout << "\n";
    }

    return sout.str();
}

std::string DividedDifference::getInterpolatingPolynomial()
{
    std::ostringstream sout;

    for (int j = 0; j < size; ++j)
    {
        double nextA = *(*(a + j) + 0);
        if (nextA < 0)
        {
            // neg
            sout << "- " << abs(nextA) << " ";
        }
        else
        {
            // pos
            if (j != 0)
            {
                sout << "+ ";
            }
            sout << nextA << " ";
        }
        for (int i = 0; i < j; ++i)
        {
            sout << "(x";
            double nextX = *(x + i);
            if (nextX < 0)
            {
                sout << " + " << abs(nextX) << ") ";
            }
            else if (nextX > 0)
            {
                sout << " - " << nextX << ") ";
            }
            else
            {
                sout << ") ";
            }
        }
    }

    return sout.str();
}

/*
Uses Polynomial class to compute polynomials and make nicer strings.
*/
std::string DividedDifference::getSimplifiedPolynomial()
{
    std::ostringstream sout;
    Polynomial p;

    for (int j = 0; j < size; ++j)
    {
        Polynomial nextP(*(*(a + j) + 0));
        
        for (int i = 0; i < j; ++i)
        {
            double d[2] = { (-1 * (*(x + i))), 1 };
            Polynomial xMul(d, 2);
            nextP = (nextP * xMul);
        }

        p += nextP;
    }

    return p.str();
}
