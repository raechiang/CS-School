/*
Rachel Chiang
Project 2: 21-Queens
CS 420.01
--------------------------------------------------------------------------------
This is the genetic algorithm solver. It attempts to solve n-queens, given a
randomly-generated vector of strings population.
*/
#ifndef GENETICALGORITHMSOLVER_H
#define GENETICALGORITHMSOLVER_H

#include <string>
#include <vector>
#include "Node.h"

class GeneticAlgorithmSolver
{
    private:
        // fields
        int mutationProbability;
        int totalFitness;
        const int maxFitness;
        int searchCost;
        std::vector<Node> population;
        // functions
        // selection
        int selectParent(int other);
        // crossover
        std::string reproduce(std::string x, std::string y);
        // fitness
        int getFitness(std::string state);
        // mutation probability
        int getMutationProbability(int generationCounter);
    public:
        // constructor
        GeneticAlgorithmSolver(std::vector<std::string> population, int mutationProbability);
        // the control of the GA algorithm
        std::string geneticAlgorithm();
        // getter for searchCost
        int getSearchCost();
};

#endif