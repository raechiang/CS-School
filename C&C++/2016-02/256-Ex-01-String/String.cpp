#include "String.h"

// this header has some useful functions
// for working with C-strings
#include <cstring>

String::String()
	: data(new char[10]), size(0), allocated(10)
{
}

String::String(char first)
	: data(new char[10]), size(1), allocated(10)
{
	data[0] = first;
}

String::String(const char* given)
{
	size = strlen(given);
	data = new char[size];
	allocated = size;
	for (int i = 0; i < size; ++i)
	{
		data[i] = given[i];
	}
}

String::String(const String& other)
	: data(new char[other.size]), size(other.size), allocated(other.size)
{
	for (int i = 0; i < size; ++i)
	{
		data[i] = other.data[i];
	}
}

String::~String()
{
	if (data != nullptr)
	{
		delete[] data;
		data = nullptr;
	}

	size = 0;
	allocated = 0;
}

String& String::operator=(const String& right)
{
	if (this == &right)
	{
		return *this;
	}

	if (this->allocated < right.size || this->allocated > right.size)
	{
		delete[] this->data;
		this->data = new char[right.size];
		this->size = right.size;
		this->allocated = right.size;
	}
	
	for (int i = 0; i < this->size; ++i)
	{
		this->data[i] = right.data[i];
	}

	return *this;
}

int String::length() const
{
	return size;
}

char String::charAt(int index) const
{
	if (index < size)
	{
		return data[index];
	}
	// if the index provided is not in the String
	// it will return -
	return '-';
}

int String::indexOf(char findMe) const
{
	for (int i = 0; i < size; ++i)
	{
		if (data[i] == findMe)
		{
			return i;
		}
	}
	return -1;
}

char String::operator[](int index) const
{
	if (index < size)
	{
		return data[index];
	}
	// If index is not in size, returns -
	return '-';
}

char& String::operator[](int index)
{
	return data[index];
}

String String::operator+(const String& right) const
{
	char* newString = new char[this->size + right.size + 1];
	for (int i = 0; i < this->size; ++i)
	{
		newString[i] = this->data[i];
	}
	int j = 0;
	for (int i = this->size; i < this->size + right.size; ++i)
	{
		newString[i] = right.data[j];
		++j;
	}
	newString[this->size + right.size] = '\0';

	String s(newString);

	delete[] newString;
	newString = nullptr;

	return s;
}

String String::operator+(char c) const
{
	String s(c);
	String s2(*this);
	s2 = s2 + s;
	return s2;
}

bool String::operator==(const String& right) const
{
	if (this->size == right.size)
	{
		for (int i = 0; i < right.size; ++i)
		{
			if (this->data[i] != right.data[i])
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

bool String::operator!=(const String& right) const
{
	if (*this == right)
	{
		return false;
	}
	return true;
}

bool String::operator<(const String& right) const
{
	int smaller = right.size;
	if (this->size < right.size)
	{
		smaller = this->size;
	}

	for (int i = 0; i < smaller; ++i)
	{
		if ((int) this->data[i] < (int) right.data[i])
		{
			return true;
		}
		else if ((int) this->data[i] > (int) right.data[i])
		{
			return false;
		}
	}
	return false;
}

bool String::operator>(const String& right) const
{
	if (*this == right || *this < right)
	{
		return false;
	}
	return true;
}

bool String::operator<=(const String& right) const
{
	if (*this == right || *this < right)
	{
		return true;
	}
	return false;
}

bool String::operator>=(const String& right) const
{
	if (*this == right || *this > right)
	{
		return true;
	}
	return false;
}

String String::substring(int start, int end) const
{
	if (start < 0 || start > size || end <= start)
	{
		String s;
		return s;
	}

	if (end > size)
	{
		end = size;
	}
	char *arr = new char[end - start + 1];
	int index = 0;
	for (int i = start; i < end; ++i)
	{
		arr[index] = data[i];
		++index;
	}
	arr[index] = '\0';
	String s(arr);

	delete[] arr;

	return s;
}

std::ostream& operator<<(std::ostream& os, const String& s)
{
	for (int i = 0; i < s.size; ++i)
	{
		os << s.data[i];
	}
	return os;
}

std::istream& operator>>(std::istream& is, String& s)
{
	std::string str;
	is >> str;
	int counter = 0;
	while (str[counter] != '\0')
	{
		++counter;
	}
	counter += 1;
	char* cstr = new char[counter + 1];
	for (int i = 0; i < counter; ++i)
	{
		cstr[i] = str[i];
	}
	cstr[counter] = '\0';
	String s2(cstr);
	s = s + s2;
	delete[] cstr;
	return is;
}