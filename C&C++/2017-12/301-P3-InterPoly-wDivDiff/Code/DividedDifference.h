#ifndef DIVIDEDDIFFERENCE_H
#define DIVIDEDDIFFERENCE_H

#include <string>

class DividedDifference
{
    private:
        int size;
        double* x;
        double** a;
        void solve();
    public:
        DividedDifference(std::string xString, std::string fxString, int s);
        ~DividedDifference();
        std::string getTable();
        std::string getInterpolatingPolynomial();
        std::string getSimplifiedPolynomial();
};

#endif