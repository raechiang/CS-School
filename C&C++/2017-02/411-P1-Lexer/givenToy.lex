int fact (int x) {
// recursive factorial function
    if (x>1) return x * fact(x-1);
    else return 1;
}
void main () {
/* Winter Quarter 2015
CS 411 project #1
A lexical analyzer */
    int x;
    int total;
    println ("factorial of 10 is ", fact (10), " from the recursive function");
    total = 1; x = 1;
    for ( ; x<=10; ) { total = total * x; x = x + 1; }
    println ("iterative result of 10! is ", total);
}
class cs411 {
    int Funny;
    double funny;
    boolean flag;
    string s;
    int [] a;
    flag = true;
    Funny = 0X89aB; funny = 123456E+7;
    s = "hello world";
    while (x = (Funny/10) <0) println (s, " have fun !");
    a = newarray (20, int);
}