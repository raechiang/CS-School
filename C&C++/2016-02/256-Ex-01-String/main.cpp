#include "String.h"
#include <sstream>
#include <cstring>

using std::string;

class StringTester
{
public:


    void printResult(string output, bool pass) const
    {
        std::cout << (pass ? "PASS\t" : "FAIL\t")
              << output << std::endl;
    }

    void testExistence() const
    {
        String s;
        printResult("Should always pass!", true);
    }

    void testConstructors() const
    {
        String s1;
        printResult("Default Constructor", s1.size == 0 && s1.data != nullptr);
        String s4('a');
        printResult("char Constructor", s4.size == 1 && s4.data[0] == 'a');
        char s[6] = {'a', 'b', 'c', 'd', 'e', '\0'};
        int len = 5;
        String s2(s);
        printResult("C-string Constructor", s2.size == len && strncmp(s, s2.data, len) == 0);
        s[3] = 'X';
        printResult("C-string Deep Copy Test", strcmp(s, s2.data) != 0);
        String s3(s2);
        printResult("Copy Constructor", s3.size == s2.size && strncmp(s2.data, s3.data, 5) == 0);
        s2.data[1] = '1';
        printResult("Copy Constructor Deep Copy Test", s3.size == s2.size && strncmp(s2.data, s3.data, 5) != 0);
    }

    void testAssignment() const
    {
        char s[6] = {'a', 'b', 'c', 'd', 'e', '\0'};
        int len = 5;
        String s1(s);
        String s2 = s1;
        printResult("Assignment Operator", s1.size == s2.size && strncmp(s1.data, s2.data, 5) == 0);
        s2.data[3] = 'H';
        printResult("Assignment Operator Deep Copy Test", s1.size == s2.size && strncmp(s1.data, s2.data, 5) != 0);
        s1 = s1 = s1 = s1 = s1 = s1;
        printResult("Self Assignment Test", s1.size == 5 && strncmp(s, s1.data, 5) == 0);
    }

    void testUtility() const
    {
        char s[6] = {'a', 'b', 'c', 'd', 'e', '\0'};
        int len = 5;
        String s1(s);
        printResult("length()", s1.length() == s1.size);
        printResult("charAt()", s1.charAt(0) == 'a' && s1.charAt(1) == 'b' && s1.charAt(4) == 'e');
        printResult("indexOf()", s1.indexOf('a') == 0 && s1.indexOf('d') == 3 && s1.indexOf(' ') == -1);
        printResult("operator[] read", s1[0] == 'a' && s1[1] == 'b' && s1[2] == 'c' && s1[3] == 'd');
        s1[0] = 'X';
        printResult("operator[] write", s1[0] == 'X' && s1[1] == 'b' && s1[2] == 'c' && s1[3] == 'd');
    }

    void testConcat() const
    {
        char s[6] = {'a', 'b', 'c', 'd', 'e', '\0'};
        int len = 5;
        String s1(s);
        String s2 = s1;
        String s3 = s1 + s2;
        const char *res = "abcdeabcde";
        printResult("String Concatenation", s3.size == s1.size + s2.size && strncmp(s3.data, res, s3.size) == 0);
        s3 = (s3 + 'f') + 'g';
        const char *res2 = "abcdeabcdefg";
        printResult("char Concatenation", s3.size == s1.size + s2.size + 2 && strncmp(s3.data, res2, s3.size) == 0);
    }

    void testRelational() const
    {
        const char *c1 = "abcde";
        const char *c2 = "bbcde";
        String s1(c1);
        String s2(c2);
        String s3('a');
        String s4(s2);
        printResult("Test ==", s2 == s4 && !(s1 == s2) && !(s3 == s4));
        printResult("Test !=", !(s2 != s4) && s1 != s2 && s3 != s4);
        printResult("Test <", s1 < s2 && !(s2 < s1) && s3 < s4 && !(s4 < s3) && !(s2 < s4));
        printResult("Test >", s2 > s1 && s4 > s3 && !(s1 > s2) && !(s2 > s4));
        printResult("Test <=", s1 <= s2 && s2 <= s4 && s3 <= s2 && !(s2 <= s1));
        printResult("Test >=", !(s1 >= s2) && s2 >= s4 && s2 >= s3 && s2 >= s1);
    }

    void testSubstring() const
    {
        const char *c = "abcdefghijk";
        String s(c);
        const char *ss1 = "cdef";
        String s2 = s.substring(2, 6);
        printResult("substring cdef", s2.size == 4 && strncmp(s2.data, ss1, 4) == 0);
        s2 = s.substring(-4, 6);
        printResult("substring negative start", s2.size == 0);
        s2 = s.substring(72, 3);
        printResult("substring high start", s2.size == 0); 
        s2 = s.substring(4, 3);
        printResult("substring end < start", s2.size == 0);
        s2 = s.substring(4, 4);
        printResult("substring end == start", s2.size == 0);
        s2 = s.substring(4, 50);
        const char *ss2 = "efghijk";
        printResult("substring high end", s2.size == 7 && strncmp(s2.data, ss2, 7) == 0);
    }

    void testIO() const
    {
        std::ostringstream sout;
        const char *c = "abcdefghijk";
        String s1(c);
        sout << s1;
        std::string st = sout.str();
        const char *c2 = st.c_str();
        String s2(c2);
        printResult("Test <<", s1 == s2);
        std::istringstream sin(st);
        String s3;
        sin >> s3;
        printResult("Test >>", s1 == s3);
    }

};


int main()
{
    StringTester st;
    
    st.testExistence();
    
    // Uncomment one at a time as you add features to your class
    
    st.testConstructors();
    st.testAssignment();
    st.testUtility();
    st.testConcat();
    st.testRelational();
    st.testSubstring();
    st.testIO();

    return 0;
}