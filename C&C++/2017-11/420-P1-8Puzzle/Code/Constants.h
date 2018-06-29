/*
Rachel Chiang
Project 1: 8-Puzzle
CS 420.01

Puzzle constants that are shared across multiple classes.
*/
#pragma once

namespace Constants {
    enum Direction {
        UP = 0,
        DOWN = 1,
        LEFT = 2,
        RIGHT = 3
    };
    const static int GOAL_STATE = 12345678;
};
