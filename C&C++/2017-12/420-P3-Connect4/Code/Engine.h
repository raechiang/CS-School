/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/

#ifndef ENGINE_H
#define ENGINE_H

#include <cstdint> // int64_t
#include "UserInterface.h"
#include "AgentComputer.h"
#include "Board.h"

class Engine
{
    private:
        // Fields
        UserInterface ui;
        // The computer. It doesn't stand for artificial "intelligence."
        AgentComputer ai;
        Board board;
        // Functions
        void update();
        int64_t queryHuman();
    public:
        // Constructor
        Engine();
        // Escape while you can.
        void run();
};

#endif