/**
 * Rachel Chiang
 * CS 352.01 Symbolic Programming
 * Homework #1
 */


 /**
 * Exercise #1 isList/1
 * This predicate returns true if its argument is a list and false otherwise.
 */
isList([]).
isList([_|_]).


/**
 * Exercise #2 prepend/3
 * This predicate accepts any term as its first argument, a list as its second
 * argument, and a third argument which will be returned as the list with
 * the first argument as its head and the second argument as its tail.
 */
prepend(A,B,[A|B]):-B=[_|_].


/**
 * Exercise #3 remove/3
 * This predicate accepts any term as its first argument, a list as its second
 * argument, and a third argument which will be a list equal to the list in the
 * second argument but with the first occurrence of the first argument removed
 * (if it exists).
 */
 remove(A,[],[]).
 remove(A,[B|Tb],[B|Tc]):-A\=B, remove(A,Tb,Tc).
 remove(A,[B|Tb],Tb):-A=B.


/**
 * Exercise #4 filter/3
 * This predicate functions similarly to remove/3 above except instead of only
 * removing the first occurrence only, it will also remove any other subsequent
 * occurrences of the term.
 */
 filter(A,[],[]).
 filter(A,[B|Tb],[B|Tc]):-A\=B, filter(A,Tb,Tc).
 filter(A,[B|Tb],L):-A=B, filter(A,Tb,L).


/**
 * Exercise #5 frenchFlag/2
 * This predicate accepts a list of terms as its first argument and will return
 * a special list derived from the first list in the second argument. The
 * special list sorts all b's at the beginning of the list, the w's in the
 * middle, and the r's at the end. Any other atom is excluded.
 */
 frenchFlag([],[]).
 frenchFlag(L0,X):-buildFlag(L0,X,[],[],[]).
 buildFlag([],X,Blue,White,Red):-append(Blue,White,BW), append(BW,Red,X).

 buildFlag([H|T],X,Blue,White,Red):-H\=b, H\=w, H\=r,
       buildFlag(T,X,Blue,White,Red).

 buildFlag([H|T],X,Blue,White,Red):-H=b, buildFlag(T,X,[H|Blue],White,Red).
 buildFlag([H|T],X,Blue,White,Red):-H=w, buildFlag(T,X,Blue,[H|White],Red).
 buildFlag([H|T],X,Blue,White,Red):-H=r, buildFlag(T,X,Blue,White,[H|Red]).
