Rachel Chiang
CS 411-01
Project 1

I used some version of Xubuntu that I installed in 2016 and Lex. You can use
these for help on installation (or use Google):
 http://askubuntu.com/questions/164293/how-to-install-flex
 http://ccm.net/faq/30635-how-to-install-flex-and-bison-under-ubuntu
 https://geeksww.com/tutorials/operating_systems/linux/installation/installing_flex_fast_lexical_analyzer_ubuntu_linux.php
I did not use any other external or third-party libraries or software.

Otherwise, the procedure is pretty straightforward, especially if you watched
the video that was shown in class.
  1. I don't know if this step is necessary, but to create the lex.yy.c code
     from my tokenScanner.l, type
        flex tokenScanner.l
  2. To compile, I just used gcc, like so
        gcc tokenScanner.c lex.yy.c -o lexer
  3. Finally, to run, replace INPUTFILE with any input file
        ./lexer <INPUTFILE
     (For instance, "./lexer <simpleToy.lex")

That's it. Thanks.