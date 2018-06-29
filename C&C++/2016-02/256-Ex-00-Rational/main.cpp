#include "rat.h"

using std::string;

class RatTester
{
public:


    void printResult(string output, bool pass) const
    {
        std::cout << (pass ? "PASS\t" : "FAIL\t")
              << output << std::endl;
    }

    void testExistence() const
    {
        rat r;
        printResult("Should always pass!", true);
    }

// Uncomment one at a time as you fill in features for your class


    void testConstructors() const
    {
        rat r1;
        printResult("Default Constructor", r1.num == 0 && r1.den == 1);
        rat r2(7);
        printResult("int Constructor", r2.num == 7 && r2.den == 1);
        rat r3(19, 3);
        printResult("n/d Constructor", r3.num == 19 && r3.den == 3);
        rat r4(-6, 0);
        printResult("n/0 test", r4.num == 0 && r4.den == 1);
    }

    void testGetters() const
    {
        rat r1(2, 3);
        printResult("Getters", r1.getNum() == 2 && r1.getDen() == 3);
    }

    void testReduce() const
    {
        rat r1(16, 20);
        printResult("Reduce Constructor", r1.num == 4 && r1.den == 5);
        rat r2(90, -30);
        printResult("Reduce Constructor", r2.num == -3 && r2.den == 1);
    }

    void testReciprocal() const
    {
        rat r1(18, 1);
        rat r2 = r1.reciprocal();
        printResult("Test Reciprocal", r2.num == 1 && r2.den == 18);
    }

    void testArithmetic() const
    {
        rat r1(3, 5);
        rat r2(9, 11);
        rat r3 = r1 + r2;
        printResult("Test +", r3.num == 78 && r3.den == 55);
        r3 = r1 - r2;
        printResult("Test -", r3.num == -12 && r3.den == 55);
        r3 = r1 * r2;
        printResult("Test *", r3.num == 27 && r3.den == 55);
        r3 = r1 / r2;
        printResult("Test /", r3.num == 11 && r3.den == 15);
    }

    void testRelational() const
    {
        rat r1(3, 5);
        rat r2(2, 5);
        rat r3(-6, -10);
        printResult("Test ==", r1 == r3 && !(r1 == r2));
        printResult("Test !=", r1 != r2 && !(r1 != r3));
        printResult("Test <", r2 < r1 && !(r3 < r1));
        printResult("Test >", r1 > r2 && !(r3 > r1));
        printResult("Test <=", r2 <= r1 && r3 <= r1 && !(r1 <= r2));
        printResult("Test >=", r1 >= r2 && r1 >= r3 && !(r2 >= r1));
    }

    void testStr() const
    {
        rat r1(3, 5);
        rat r2(-6, -10);
        printResult("Test str()", r1.str() == "3/5");
        printResult("Test str()", r2.str() == "3/5");
        printResult("Test str()", (r1 * r1 + r2).reciprocal().str() == "25/24");
    }
    

};


int main()
{
    RatTester rt;
    
    rt.testExistence();
    
    // Uncomment one at a time as you add features to your class
    
    rt.testConstructors();
    rt.testGetters();
    rt.testReduce();
    rt.testReciprocal();
    rt.testArithmetic();
    rt.testRelational();
    rt.testStr();
    
    return 0;
}