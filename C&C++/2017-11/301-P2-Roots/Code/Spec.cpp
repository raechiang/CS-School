#include "Spec.h"

#include <math.h>
#include <sstream>


double Spec::a::f(double x)
{
    return (-5 + x * (17.7 + x * (-11.7 + x * (2))));
}

double Spec::a::dfdx(double x)
{
    return (17.7 + x * (-23.4 + x * (6)));
}

double Spec::b::f(double x)
{
    return (x * (1 - cosh(50.0 / x)) + 10);
}

double Spec::b::dfdx(double x)
{
    double u = 50.0 / x;
    return (1 - cosh(u) + (u * sinh(u)));
}

std::string Spec::a::fToString()
{
    std::ostringstream sout;
    sout << "f(x) = 2x^3 - 11.7x^2 + 17.7x - 5";
    return sout.str();
}

std::string Spec::b::fToString()
{
    std::ostringstream sout;
    sout << "f(x) = x + 10 - xcosh(50/x)";
    return sout.str();
}