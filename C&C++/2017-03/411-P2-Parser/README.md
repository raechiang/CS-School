# Parser

This is a parser I built for a fake language, which we called "Toy".

## Getting Started
I used the same Xubuntu as for the first project.
- http://askubuntu.com/questions/164293/how-to-install-flex
- http://ccm.net/faq/30635-how-to-install-flex-and-bison-under-ubuntu
- https://geeksww.com/tutorials/operating_systems/linux/installation/installing_flex_fast_lexical_analyzer_ubuntu_linux.php

I did not use any other external or third-party libraries or software; I only used notepad/nano, bison (yacc), and lex, no other third-party software.

Here is an example of how to build the synAnalyzer and run it:
```
yacc -d -v parser.y
lex tokenScanner.l
gcc lex.yy.c y.tab.c -o synAnalyzer
./synAnalyzer <Square.toy
```
Unfortunately, I don't have a makefile, sorry.

## Example
Input:
```
class Square extends Shape implements Geometry
{
   double side;

   Square Square(double s)
   {
      side = s; //rejected
   }

   double getArea()
   {
      double area;
      area = side * side;
      return area;
   }
}
```
Output:
```
class [shift]
id [shift]
extends [shift]
id [shift]
[reduce 08.1.1.2]implements [shift]
id [shift]
{[reduce 08.1.2.2.2][reduce 08.1.2.2][shift]
[reduce 08.1.3.1]double [shift]
[reduce 05.2]id [shift]
; [reduce 04.1][shift]
[reduce 03.1][reduce 09.1][reduce 08.1.3.2]id [shift]
[reduce 05.6]id [shift]
([shift]
double [shift]
[reduce 05.2]id [shift]
[reduce 04.1])[reduce 07.1.2][reduce 07.1][shift]
{[shift]
[reduce 12.1.1.1]id [reduce 12.1.2.1][shift]
= [reduce 21.1][shift]
id [shift]
; [reduce 21.1][reduce 20.03][reduce 20.01][shift]
[reduce 13.1][reduce 12.1.2.2]}[shift]
[reduce 12.1][reduce 06.1][reduce 09.2][reduce 08.1.3.2]double [shift]
[reduce 05.2]id [shift]
([shift]
)[reduce 07.2][shift]
{[shift]
[reduce 12.1.1.1]double [shift]
[reduce 05.2]id [shift]
[reduce 04.1]; [shift]
[reduce 03.1][reduce 12.1.1.2]id [reduce 12.1.2.1][shift]
= [reduce 21.1][shift]
id [shift]
mult [reduce 21.1][reduce 20.03][shift]
id [shift]
; [reduce 21.1][reduce 20.03][reduce 20.08][reduce 20.01][shift]
[reduce 13.1][reduce 12.1.2.2]return [shift]
id [shift]
; [reduce 21.1][reduce 20.03][reduce 25.2][shift]
[reduce 18.1][reduce 13.6][reduce 12.1.2.2]}[shift]
[reduce 12.1][reduce 06.1][reduce 09.2][reduce 08.1.3.2]}[shift]
[reduce 08.1][reduce 02.3][reduce 01.1][shift]
[accept]
```
