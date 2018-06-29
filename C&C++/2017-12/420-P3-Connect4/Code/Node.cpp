/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#include "Node.h"

Node::Node(int64_t a, double u)
    :action(a), utilityValue(u)
{
}

bool Node::operator>(const Node& other) const
{
    return ((this->utilityValue) > (other.utilityValue));
}

int64_t Node::getAction()
{
    return action;
}

double Node::getUtilityValue()
{
    return utilityValue;
}