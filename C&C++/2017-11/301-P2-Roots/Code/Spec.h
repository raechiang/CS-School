#pragma once

#include <iostream>

namespace Spec
{
    enum {
        PART_A = 0,
        PART_B = 1
    };

    // Part A
    namespace a {
        double f(double x);
        double dfdx(double x);
        std::string fToString();
        const static double aBound = 0;
        const static double bBound = 4;
        const static int nRootsInI = 3;
        const static double roots[nRootsInI] = {
            0.365098, 1.92174, 3.56316 };
    }

    // Part B
    namespace b {
        double f(double x);
        double dfdx(double x);
        std::string fToString();
        const static double aBound = 120;
        const static double bBound = 130;
        const static int nRootsInI = 1;
        const static double root = 126.632436;
    }
    const static int maxIts = 100;
    //const static double ea = 0.01;
    const static double ea = 0.0001;
    const static double delta = 0.01;
}