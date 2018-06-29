/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This is the genetic algorithm solver. It attempts to solve n-queens, given a
randomly-generated vector of strings population.
*/
#include "GeneticAlgorithmSolver.h"

#include <iostream>
#include <cstdlib>
#include <sstream>

#include "Constants.h"
#include "Solver.h"

// Constructor
/*
The population is saved as a vector of Nodes that contain the state as a string
and its corresponding fitness. I admit that there should actually be two
constructors: one for a GASolver with an externally-desired mutation probability
and one for a GASolver with the default mutation probability (the linearly-
changing one).
*/
GeneticAlgorithmSolver::GeneticAlgorithmSolver(
    std::vector<std::string> population,
    int mutationProbability)
    : maxFitness(((Constants::N - 1)*((Constants::N - 1) + 1))/2),
    searchCost(0)
{
    this->mutationProbability = mutationProbability;
    totalFitness = 0;
    
    for (int i = 0; i < population.size(); ++i)
    {
        int stateFitness = getFitness(population[i]);
        this->population.push_back(Node(population[i], stateFitness));
        totalFitness += stateFitness;
    }
}

/*
The genetic algorithm. Given a population of complete states, the algorithm will
"mate" two of the states, with a tendency to select more "fit" pairs. The child
will be added to the new population. This repeats k times, where k is the size
of the population. There is a chance that the child may mutate, which is
determined in getMutationProbability(int). A solution will be returned if it is
found, but if not, then the algorithm loops however many times COUNTER is set
to.
*/
std::string GeneticAlgorithmSolver::geneticAlgorithm()
{
    int counter = Constants::COUNTER;
    int bestFitness = population[0].getFitness();
    std::string bestState = population[0].getState();
    while (counter != 0)
    {
        std::vector<Node> newPopulation;
        int newTotalFitness = 0;
        for (int i = 0; i < population.size(); ++i)
        {
            int x = selectParent(-1);
            int y = selectParent(x);
            std::string child = reproduce(population[x].getState(), population[y].getState());

            if ((rand() % 100) < getMutationProbability(counter))
            {
                int mutateIndex = (rand() % child.length());
                child[mutateIndex] = ((char)((rand() % Constants::N) + Constants::OFFSET_A));
            }
            int childFitness = getFitness(child);
            if (childFitness == maxFitness)
            {
                return child;
            }
            if (childFitness >= bestFitness)
            {
                bestFitness = childFitness;
                bestState = child;
            }
            newTotalFitness += childFitness;
            newPopulation.push_back(Node(child, childFitness));
        }

        population = newPopulation;
        totalFitness = newTotalFitness;

        --counter;
        ++searchCost;
    }

    return bestState;
}

/*
A parent is selected randomly from the population. It has a chance to be used
depending on its fitness to the total fitness of the current population. This
function will return the corresponding index of the parent in the population.
*/
int GeneticAlgorithmSolver::selectParent(int other)
{
    while (true)
    {
        int pick = rand() % population.size();
        float successRate = population[pick].getFitness() / ((float)totalFitness);
        float pickRate = (rand() % 100) / 100.0;

        if (pickRate <= successRate)
        {
            // makes sure to get two different parents
            if (other != -1)
            {
                if (pick != other)
                {
                    return pick;
                }
            }
            else
            {
                return pick;
            }
        }
    }
}

/*
This is the crossover stage. It randomly picks one point of crossover, an index
to cut the parents into two pieces each and join the strings at that point.
*/
std::string GeneticAlgorithmSolver::reproduce(std::string x, std::string y)
{
    std::ostringstream sout;
    int size = x.length() - 1; // don't want end
    int crossover = (rand() % size) + 1; // don't want beginning
    sout << x.substr(0, crossover);
    sout << y.substr(crossover, y.length());

    return sout.str();
}

/*
This gets the fitness, which is equal to the number of nonattacking pairs of
queens.
*/
int GeneticAlgorithmSolver::getFitness(std::string state)
{
    return (maxFitness - Solver::countAttackingPairs(state));
}

/*
This function will return either a static mutation probability, if it was set
manually or a dynamic mutation probability. This mutation probability will
decrease over time. In other words, as more generations of the population are
produced, the less likely it is for a child to be mutated.
*/
int GeneticAlgorithmSolver::getMutationProbability(int generationCounter)
{
    if (mutationProbability == -1)
    {
        return (generationCounter / (Constants::COUNTER / 100));
    }
    else
    {
        return mutationProbability;
    }
}

/*
A simple getter.
*/
int GeneticAlgorithmSolver::getSearchCost()
{
    return searchCost;
}