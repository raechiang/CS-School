/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#ifndef NODE_H
#define NODE_H

#include <cstdint> // int64_t

class Node
{
    private:
        // Fields
        int64_t action;
        double utilityValue;
    public:
        // Constructor
        Node(int64_t a, double u);
        // Operator Overload
        bool operator>(const Node& other) const;
        // Getters
        int64_t getAction();
        double getUtilityValue();
};

#endif