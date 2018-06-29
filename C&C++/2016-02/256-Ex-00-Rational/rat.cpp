// Rachel Chiang
// CS 256-EX5
// 02-21-2016

#include "rat.h"

rat::rat()
	: num(0), den(1)
{
}

rat::rat(int n)
	: num(n), den(1)
{
}

rat::rat(int n, int d)
	: num(n), den(d)
{
	if (d == 0)
	{
		num = 0;
		den = 1;
	}
	reduce();
}

int rat::getNum() const
{
	return num;
}

int rat::getDen() const
{
	return den;
}

void rat::reduce()
{
	int g = gcd(num, den);
	num /= g;
	den /= g;
	if (den < 0)
	{
		num = -num;
		den = -den;
	}
}

int rat::gcd(int x, int y)
{
    if (y == 0) 
        return x;
    return gcd(y, x % y);
}

int rat::lcm(int x, int y)
{
    return (x * y) / gcd(x, y);
}

rat rat::reciprocal() const
{
	return rat(den, num);
}

rat rat::operator+(const rat& right) const
{
	rat sum((right.den * this->num) + (this->den * right.num), this->den * right.den);
	sum.reduce();
	return sum;
}

rat rat::operator-(const rat& right) const
{
	rat r1(this->num, this->den);
	rat r2((-1) * right.num, right.den);
	return (r1 + r2);
}

rat rat::operator*(const rat& right) const
{
	rat product(this->num * right.num, this->den * right.den);
	product.reduce();
	return product;
}

rat rat::operator/(const rat& right) const
{
	rat newRight(right.den, right.num);
	rat left(this->num, this->den);
	return (left * newRight);
}

bool rat::operator==(const rat& right) const
{
	if (right.den * this->num == this->den * right.num)
	{
		return true;
	}
	return false;
}

bool rat::operator!=(const rat& right) const
{
	rat left(this->num, this->den);
	if (left == right)
	{
		return false;
	}
	return true;
}

bool rat::operator>(const rat& right) const
{
	rat left(this->num, this->den);
	if (left == right || left < right)
	{
		return false;
	}
	return true;
}

bool rat::operator<(const rat& right) const
{
	if (right.den * this->num < this->den * right.num)
	{
		return true;
	}
	return false;
}

bool rat::operator>=(const rat& right) const
{
	rat left(this->num, this->den);
	if (left > right || left == right)
	{
		return true;
	}
	return false;
}

bool rat::operator<=(const rat& right) const
{
	rat left(this->num, this->den);
	if (left < right || left == right)
	{
		return true;
	}
	return false;
}

std::string rat::str() const
{
	std::ostringstream sout;
	sout << num;
	sout << "/";
	sout << den;
	return sout.str();
}