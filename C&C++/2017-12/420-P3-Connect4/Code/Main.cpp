/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
In this project, the minimax algorithm with alpha beta pruning is implemented to
attempt to play 4-in-a-Line on an 8x8 board. It is a two-player game and the
participants compete to create a line of four consecutive pieces belonging to
oneself. The lines must be oriented in a horizontal or vertical manner. In
addition to minimax and alpha-beta pruning, the DFS is implemented as iterative-
deepening and the utility value is estimated according to the state conditions
using the evaluation function.
*/
#include "Engine.h"

int main()
{
    Engine engine;

    engine.run();

    return 0;
}