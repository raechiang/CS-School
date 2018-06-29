#ifndef ROOTMETHOD_H
#define ROOTMETHOD_H

#include <iostream>
#include <fstream>

class RootMethod
{
    private:
        int part;
    protected:
        void outputHeader(std::ofstream &datFos, std::ofstream &csvFos, std::string method, std::string initial, double trueRoot);
        double getf(double x);
        void output(std::ofstream &datFos, std::ofstream &csvFos, int n, double x, double fx, double percRE, double percTE);
        void outputEndIts(std::ofstream &datFos, std::ofstream &csvFos);
        void outputSolution(std::ofstream &datFos, std::ofstream &csvFos, double x);
        void outputApproxRoot(std::ofstream &datFos, std::ofstream &csvFos, double x);
        double getRelErr(double prevApprox, double currApprox);
        double getTrueErr(double trueRoot, double currApprox);
        double getdfdx(double x);
        double getDelta();
    public:
        RootMethod(int p);
};

#endif