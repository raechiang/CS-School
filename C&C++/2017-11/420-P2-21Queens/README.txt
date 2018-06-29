To compile on Windows, input "g++ -o <name> <list of .cpp files> -std=c++11",
or whatever compiler you use. Then run "<name>.exe"

Example input:
 g++ -o nqueens GeneticAlgorithmSolver.cpp HillClimbingSolver.cpp Main.cpp Node.cpp RandomStateGenerator.cpp Solver.cpp -std=c++11
 nqueens.exe

I also included an .exe.