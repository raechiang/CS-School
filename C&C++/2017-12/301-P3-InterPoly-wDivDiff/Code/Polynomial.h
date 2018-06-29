/*
A Polynomial class.
*/
#ifndef POLYNOMIAL_H
#define POLYNOMIAL_H

#include <iostream>
#include <string>

class Polynomial
{
    private:
        double* coeff;
        int size;
    public:
        // constructors/destructors
        Polynomial();
        Polynomial(double cf[], int length);
        Polynomial(const Polynomial& p);
        Polynomial(int firstC);
        Polynomial(double firstC);
        ~Polynomial();

        // utility
        int getSize() const;
        int degree() const;
        std::string str() const;
        double solve(double x) const;

        // operator overloads
        // assignments
        Polynomial& operator=(const Polynomial& right);
        double& operator[](int index);
        //arithmetic operators
        Polynomial operator+(const Polynomial& right) const;
        Polynomial operator-(const Polynomial& right) const;
        Polynomial operator*(const Polynomial& right) const;
        Polynomial operator*(double num) const;
        Polynomial operator+=(const Polynomial& right);
        Polynomial operator-=(const Polynomial& right);
        Polynomial operator*=(const Polynomial& right);
        // relational operators
        bool operator==(const Polynomial& right) const;
        bool operator!=(const Polynomial& right) const;
};

std::ostream& operator<<(std::ostream& os, const Polynomial& p);

#endif