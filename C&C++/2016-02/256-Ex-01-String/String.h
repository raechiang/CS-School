#ifndef STRING_H
#define STRING_H

#include <iostream>
#include <string>

class StringTester;

class String
{
    friend class StringTester;
	friend std::ostream& operator<<(std::ostream& os, const String& s);
	friend std::istream& operator>>(std::istream& is, String& s);
private:
    // declare private members here
	char* data;
	int size;
	int allocated;
public:
    // constructors, destructors
	String();
	String(char first);
	String(const char* given);
	String(const String& other);
	~String();

	// operator overloads
	String& operator=(const String& right);
	char operator[](int index) const;
	char& operator[](int index);
	String operator+(const String& right) const;
	String operator+(char c) const;
	// relational overloads
	bool operator==(const String& right) const;
	bool operator!=(const String& right) const;
	bool operator<(const String& right) const;
	bool operator>(const String& right) const;
	bool operator<=(const String& right) const;
	bool operator>=(const String& right) const;

	// utility
	int length() const;
	char charAt(int index) const;
	int indexOf(char findMe) const;

	String substring(int start, int end) const;
};

std::ostream& operator<<(std::ostream& os, const String& s);

std::istream& operator>>(std::istream& is, String& s);

#endif