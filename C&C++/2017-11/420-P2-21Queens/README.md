# 21-Queens Solved with Steepest-Ascent Hill Climbing and Genetic Algorithms

This project uses the steepest-ascent hill climbing approach and the genetic algorithm approach to distribute queens such that none are 'attacking' one another. I would recommend reading the <a href="https://github.com/raechiang/CS-School/blob/master/C%26C%2B%2B/2017-11/420-P2-21Queens/chiang-rachel-project-report.pdf">project report</a> for a more in-depth overview.

## Getting Started

To compile on Windows, input
```
g++ -o <name> <list of .cpp files> -std=c++11
```
Or whatever compiler you use. Then run "<name>.exe"
Example input:
```
g++ -o nqueens GeneticAlgorithmSolver.cpp HillClimbingSolver.cpp Main.cpp Node.cpp RandomStateGenerator.cpp Solver.cpp -std=c++11
nqueens.exe
```
I have included an .exe.
