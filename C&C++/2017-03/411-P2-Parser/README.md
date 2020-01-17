# Parser

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
