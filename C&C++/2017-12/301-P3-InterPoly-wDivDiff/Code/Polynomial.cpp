#include <sstream>
#include <cmath>

#include "Polynomial.h"

Polynomial::Polynomial()
    : coeff(new double[1]), size(1)
{
    coeff[0] = 0;
}

Polynomial::Polynomial(double cf[], int length)
    : coeff(new double[length]), size(length)
{
    for (int i = 0; i < size; ++i)
    {
        coeff[i] = cf[i];
    }
}

Polynomial::Polynomial(const Polynomial& p)
    : coeff(new double[p.size]), size(p.size)
{
    for (int i = 0; i < size; ++i)
    {
        this->coeff[i] = p.coeff[i];
    }
}

Polynomial::Polynomial(int firstC)
    : coeff(new double[1]), size(1)
{
    coeff[0] = (double)firstC;
}

Polynomial::Polynomial(double firstC)
    : coeff(new double[1]), size(1)
{
    coeff[0] = firstC;
}

Polynomial& Polynomial::operator=(const Polynomial& right)
{
    if (this == &right)
    {
        return *this;
    }

    if (this->size != right.size)
    {
        delete[] this->coeff;
        this->coeff = new double[right.size];
    }
    this->size = right.size;
    for (int i = 0; i < this->size; ++i)
    {
        this->coeff[i] = right.coeff[i];
    }

    return *this;
}

Polynomial::~Polynomial()
{
    delete[] coeff;
    size = 0;
}

int Polynomial::getSize() const
{
    return size;
}

int Polynomial::degree() const
{
    int degree = 0;
    for (int i = 0; i < size; ++i)
    {
        if (coeff[i] != 0)
        {
            degree = i;
        }
    }
    return degree;
}

std::string Polynomial::str() const
{
    std::ostringstream sout;
    int first = size - 1;
    bool firstFound = false;
    for (int i = size - 1; i >= 0; --i)
    {
        if (coeff[i] != 0 && !(firstFound))
        {
            first = i;
            firstFound = true;
        }
    }

    if (coeff[first] != -1 && coeff[first] != 1)
    {
        if (coeff[first] < 0)
        {
            sout << "-" << -1 * coeff[first];
        }
        else if (coeff[first] > 0)
        {
            sout << coeff[first];
        }
    }
    else if (coeff[first] == -1)
    {
        sout << "-";
    }

    if (first == 0)
    {
        return sout.str();
    }
    else if (first == 1)
    {
        sout << "x";
    }
    else
    {
        sout << "x^" << first;
    }

    for (int i = first - 1; i >= 0; --i)
    {
        if (coeff[i] == 1)
        {
            sout << " + ";
        }
        else if (coeff[i] == -1)
        {
            sout << " - ";
        }
        else
        {
            if (coeff[i] > 0)
            {
                sout << " + " << coeff[i];
            }
            else if (coeff[i] < 0)
            {
                sout << " - " << (-1 * coeff[i]);
            }
        }

        if (coeff[i] != 0)
        {
            if (i != 0)
            {
                sout << "x";
                if (i != 1)
                {
                    sout << "^" << i;
                }
            }
        }
    }

    return sout.str();
}

double Polynomial::solve(double x) const
{
    double solution = 0;
    for (int i = 0; i < size; ++i)
    {
        solution += coeff[i] * pow(x, i);
    }
    return solution;
}

double& Polynomial::operator[](int index)
{
    if (index >= size)
    {
        double* newCoeff = new double[index + 1];
        for (int i = 0; i < size; ++i)
        {
            newCoeff[i] = coeff[i];
        }
        for (int i = size; i < index + 1; ++i)
        {
            newCoeff[i] = 0;
        }
        size = index + 1;
        delete[] coeff;

        coeff = new double[size];
        for (int i = 0; i < size; ++i)
        {
            coeff[i] = newCoeff[i];
        }
        delete[] newCoeff;
    }

    return coeff[index];
}

Polynomial Polynomial::operator+(const Polynomial& right) const
{
    int nSize = right.size;
    bool isRight = true;
    if (this->size > right.size)
    {
        nSize = this->size;
        isRight = false;
    }

    double* nArray = new double[nSize];

    for (int i = 0; i < nSize; ++i)
    {
        if (isRight)
        {
            nArray[i] = right.coeff[i];
            if (i < this->size)
            {
                nArray[i] += this->coeff[i];
            }
        }
        else
        {
            nArray[i] = this->coeff[i];
            if (i < right.size)
            {
                nArray[i] += right.coeff[i];
            }
        }
    }

    Polynomial sum(nArray, nSize);
    delete[] nArray;

    return sum;
}

Polynomial Polynomial::operator-(const Polynomial& right) const
{
    double* negCopy = new double[right.size];
    for (int i = 0; i < right.size; ++i)
    {
        negCopy[i] = -1 * right.coeff[i];
    }
    Polynomial negRight(negCopy, right.size);
    delete[] negCopy;

    Polynomial difference = *this + negRight;
    return difference;
}

Polynomial Polynomial::operator*(const Polynomial& right) const
{
    int pArraySize = right.size + this->size;
    double* pArray = new double[pArraySize];

    for (int i = 0; i < pArraySize; ++i)
    {
        pArray[i] = 0;
    }

    for (int i = 0; i < this->size; ++i)
    {
        for (int j = 0; j < right.size; ++j)
        {
            pArray[i + j] += (this->coeff[i] * right.coeff[j]);
        }
    }

    Polynomial product(pArray, pArraySize);

    return product;
}

Polynomial Polynomial::operator*(double num) const
{
    Polynomial p = *this;
    for (int i = 0; i < p.size; ++i)
    {
        p.coeff[i] = 4 * p.coeff[i];
    }

    return p;
}

Polynomial Polynomial::operator+=(const Polynomial& right)
{
    Polynomial p = *this + right;
    *this = p;

    return *this;
}

Polynomial Polynomial::operator-=(const Polynomial& right)
{
    Polynomial p = *this - right;
    *this = p;

    return *this;
}

Polynomial Polynomial::operator*=(const Polynomial& right)
{
    Polynomial p = *this * right;
    *this = p;

    return *this;
}

bool Polynomial::operator==(const Polynomial& right) const
{
    if (this->degree() == right.degree())
    {
        for (int i = 0; i < right.size; ++i)
        {
            if (this->coeff[i] != right.coeff[i])
            {
                return false;
            }
        }
    }
    else
    {
        return false;
    }

    return true;
}

bool Polynomial::operator!=(const Polynomial& right) const
{
    if (*this == right)
    {
        return false;
    }
    return true;
}

std::ostream& operator<<(std::ostream& os, const Polynomial& p)
{
    os << p.str();
    return os;
}