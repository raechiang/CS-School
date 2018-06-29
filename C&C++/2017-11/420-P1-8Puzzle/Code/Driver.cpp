/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

In this project, A* is implemented to solve the 8-puzzle problem, using two
heuristic functions:
 h1 = the number of misplaced tiles
 h2 = the sum of the distances of the tiles from their goal positions

In terms of user interaction, there are four "modes" that one can choose from.
Selection is made by entering an option's corresponding integer value. The
options are as follows:
 (1) The program will generate one puzzle and solve it. The depth will be in
     the interval [1,24].
 (2) The user will input one puzzle, with integer values separated by spaces or
     by new lines (the enter button) or as one whole integer.
 (3) The program will randomly generate many puzzles with depths typically in
     the interval [1,24]. The number of puzzles generated can be found (and
     adjusted) by DESIRED_CTRLITS. It will output three files: two .csv files and
     one .txt file. It will create or replace the file. This option exists for
     testing purposes, and on my computer, with DESIRED_CTRLITS set to 1000, the
     process should take about 30 seconds (give or take, depending on whether any
     particularly difficult puzzle has been generated), so running it by accident
     should be fine.
 (4) The program will randomly generate some HARD puzzles with depth typically
     lying in the interval [17,26]. Like (3), the number of puzzles generated 
     can be found (and adjusted) by DESIRED_LONGITS. It will output three files:
     two .csv files and one .txt file, creating or replacing them as needed.
     This option also exists for testing purposes. Due to the large size of the
     solution lengths of these puzzles, running it by accident would not be a
     good idea! On my computer, it will take anywhere from one to five minutes,
     give or take. This option exists because option (3) does not provide enough
     sample cases for longer solution depths.
 (5) Ends the loop.
Note: Failure to input a proper option will force it to default to option 1.

Quick Run-Down
 Constants - This holds a few constants that appear in multiple classes.
 Node - A simple node with the information: state, previousState, h, and g.
 PuzzleManipulator - This deals with changing or printing a puzzle.
 PuzzleSolver - This controls the actual searching and solving.
 RandomPuzzleGenerator - This generates a puzzle randomly.
 Sequence - This creates the solution seaquence as a stack.
 Tester - This deals with user input, program control, and output.
*/
#include "Tester.h"

int main()
{
    Tester tester;
    tester.run();

    return 0;
}