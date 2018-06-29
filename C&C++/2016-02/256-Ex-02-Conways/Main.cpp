#include <iostream>
#include <string>
#include "Conway.h"

int main(int argc, char *argv[])
{
	if (!(argv[1]) || !(argv[2]))
	{
		std::cout << "Improper command line arguments." << std::endl;
		std::cout << "Please use an existing file for the first and a positive integer for the second.\n";
	}
	else
	{
		std::string fileName = argv[1];
		std::string iterations = argv[2];
		int n = std::stoi(iterations);

		if (n > 0)
		{
			Conway con(fileName);
			con.play(n);
		}
		else
		{
			std::cout << "Invalid integer." << std::endl;
			std::cout << "Please use a positive integer for the second command line argument." << std::endl;
		}
	}

	return 0;
}