// Rachel Chiang
// Exercise 9
// 2016-03-16

#include "Chromosome.h"

Chromosome randomChromosome(int size)
{
    std::ostringstream sout;
    for (int i = 0; i < size; i++)
    {
        sout << (char) ((rand() % 26) + 97);
    }
    return Chromosome(sout.str());
}

// Needed a means to compare the Chromosomes
bool fitCompare(Chromosome first, Chromosome sec)
{
	return (first.getFit() < sec.getFit());
}

Chromosome runGA(std::string target, int popSize, double mr, double cr)
{
	// initialize population
	std::vector<Chromosome> population;
	for (int i = 0; i < popSize; ++i)
	{
		population.push_back(randomChromosome(target.size()));
	}
	int iteration = 1;
	mr *= 100;
	cr *= 100;
	// loop until it reaches the target,
	// which will make it return to main
	while (true)
	{
		std::vector<Chromosome> newPopulation;
		for (int i = 0; i < 2 * popSize; ++i)
		{
			// Directions were vague for 2.a.i
			// "Choose a chromosome from our population."
			int chooseChrom = rand() % popSize;
			int rate = rand() % 100;
			bool noNewChromGen = true;
			
			// mutate
			if (rate <= mr && mr != 0)
			{
				newPopulation.push_back(population[chooseChrom].mutate());
				noNewChromGen = false;
			}
			rate = rand() % 100;

			// crossover
			if (rate <= cr && cr != 0)
			{
				int otherChrom = rand() % popSize;
				newPopulation.push_back(population[chooseChrom].crossover(population[otherChrom]));
				noNewChromGen = false;
			}

			// copy
			if (noNewChromGen)
			{
				newPopulation.push_back(population[chooseChrom]);
			}
		}

		// sorts and copies the newPopulation's better fit ones
		// into the original population vector
		for (int i = 0; i < newPopulation.size(); ++i)
		{
			newPopulation[i].detFit(target);
		}
		std::sort(newPopulation.begin(), newPopulation.end(), fitCompare);
		for (int i = 0; i < popSize; ++i)
		{
			population[i] = newPopulation[i];
		}
		
		// response for each interation
		int bestFit = population[0].fitness(target);
		Chromosome best(population[0].getData());
		for (int i = 0; i < popSize; ++i)
		{
			if (population[i].fitness(target) < bestFit)
			{
				bestFit = population[i].fitness(target);
				Chromosome best(population[i].getData());
			}

			// finishes if the target was acquired
			if (population[i].fitness(target) == 0)
			{
				return population[i];
			}
		}
		std::cout << "Iteration " << iteration << std::endl;
		std::cout << "  Best: " << best.getData() << std::endl;
		++iteration;
	}
}

int main(int argc, char **argv)
{
    srand(time(0));
    std::string target = argv[1];
    int popSize = atoi(argv[2]);
    double mr = atof(argv[3]);
    double cr = atof(argv[4]);
    Chromosome answer = runGA(target, popSize, mr, cr);

    std::cout << "Solution found: " << answer << std::endl;
}