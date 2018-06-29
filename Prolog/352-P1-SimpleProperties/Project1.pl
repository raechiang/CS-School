/**
 * Rachel Chiang
 * CS 352.01 Symbolic Programming
 * Project #1: This program learns and answers simple questions about properties
 * of things.
 *  1. A simple grammar derived from the English language must be defined. In
 *     this grammar there are two types of sentences: declarative and
 *     interrogative. Declarative sentences take the form of "The <property> of
 *     the <subject> is <description>", whereas interrogative sentences take the
 *     form of "What is the <property> of the <subject>". Though, to be precise,
 *     the program itself accepts sentences in the form of a list of atoms (and
 *     as such must follow the rules for Prolog atoms), for instance:
 *       "The color of the car is blue." --> [the,color,of,the,car,is,blue]
 *     This language accepts single or multi-word descriptions; for example,
 *     "dark blue" or "twenty inches" should be acceptable.
 *  2. Based off of the sentences provided by the user, this program will add
 *     new facts to the knowledge base.
 *  3. In this program, there is an execute/2 predicate, whose first argument
 *     must be instantiated as a list of atoms representing an acceptable
 *     sentence described by the grammar and whose second argument is non-
 *     instantiated and will be bound as a response.
 * (A sample interaction is provided at the bottom of this document in the
 * APPENDIX! See Sample 1.)
 *
 * Additional Features or Refinement:
 *  1. [Feature] When the sentence contradicts a fact already in the knowledge
 *     base, the response (arg 2) from execute/2 is "No, it is <description>"
 *     instead of simply "It's not". Page 2 of the project guidelines states
 *     that this can be included "for additional points".
 *  2. [Refinement] The execute/2 argument will return "false" only if the res-
 *     ponse (arg 2) is not a variable. Otherwise, if there is an issue with the
 *     string, it will return with a message about the input being invalid.
 *  3. [Refinement] The grammar also accepts sentences with "are" or "is" for the
 *     verb.
 *  4. [Refinement] When giving a Response that involves a Description of more
 *     than one atom, the Response will still be one single list. For an example
 *     interaction, see Sample 2 of APPENDIX.
 *  5. [Feature] The language is able to infer the Property of a Description.
 *     However, it is limited to only associate Properties and Descriptions that
 *     the user has already established. Page 1 of the project guidelines states
 *     that allowing inference gives an opportunity for extra credit. To see an
 *     example interaction, please skip to the APPENDIX for Sample 3.
 */

/*
 * fact/3 - A predicate which has an arity of 3, which will store the Property
 *   of a Subject and the Description
 */
:- dynamic fact/3.

/*
 * Part 1 - The Language: The Grammar
 *  This language accepts three types of sentences:
 *    1. Declarative of the form
 *       [the,<property>,of,the,<subject>,is,<description>]
 *    2. Interrogative of the form
 *       [what,is,the,<property>,of,the,<subject>]
 *    3. Inference of the form
 *       [the,<subject>,is,<description>]
 *  The Context Free Grammar G of this language is defined as
 *    G = ({Stmt, Decl, Interr, PropPhrase, Property, NounPhrase, Subject,
 *          Descrip, Det, What, Of, AssignVerb}, {Prolog atoms}*, P, Stmt),
 *          with the following production rules in P:
 *      Stmt -> Decl | Interr | Infer
 *      Decl -> PropPhrase Of NounPhrase AssignVerb Descrip
 *      Interr -> What AssignVerb PropPhrase Of NounPhrase
 *      Infer -> NounPhrase AssignVerb Descrip
 *      PropPhrase -> Det Property
 *      Property -> atom
 *      NounPhrase -> Det Subject
 *      Subject -> atom
 *      Descrip -> atom Descrip | atom
 *      Det -> "the"
 *      What -> "what"
 *      Of -> "of"
 *      AssignVerb -> "is" | "are"
 *    (where atom = any single Prolog atom)
 *
 *  Implementation Details
 *  (aside from the basics described in the formal grammar definition above)
 *    - Response, Ack, and Ans carry the correct response that will be returned
 *      from execute/2
 *    - stmt begins the parsing and will split off into the two sentence types:
 *      declarative, which is decl(Ack), and interrogative, which is interr(Ans)
 *    - decl will extract the Property, Subject, and Descrip (short for
 *      "Description") of the sentence via the propph(Property), nounp(Subject),
 *      and descrip(Descrip) calls. Once these return, decl will finally call
 *      tentAdd(Property,Subject,Descrip,Ack), which will be explained later.
 *      I chose to place a ! at the end because, if the parsing reached this
 *      point, that means that the sentence was parsed correctly in one way
 *      because all of the variables were resolved, so there is actually no
 *      reason to attempt any other route of the tree. It exists in the event
 *      that there is more than one word for the Description.
 *    - interr extracts only the Property and Subject via proph(Property) and
 *      nounp(Subject). Then, it will call lookup(Property,Subject,Ans), which
 *      will be explained later.
 *    - descrip has two rules because the Description of a Property for a
 *      Subject can be a single atom or more than one atom. In the case that it
 *      is more than one atom, the entire Description is stored into a list.
 */

stmt(stmt(SD), Response) --> decl(SD, Response).
decl(decl(PP,O,NP,AV,DS), Response) --> propph(PP, Property), of(O), nounp(NP, Subject), assignverb(AV), descrip(DS, Description),
      {tentAdd(Property, Subject, Description, Response)}, !.
propph(propph(DET,P), Property) --> det(DET), property(P, Property).
property(property(P), P) --> [P].
nounp(nounp(DET,S), Subject) --> det(DET), subject(S, Subject).
subject(subject(S), S) --> [S].
descrip(descrip(D,DS), Description) --> [D], descrip(DS, NextD),
      {append([D], [NextD], Description)}.
descrip(descrip(D), D) --> [D].
det(det(the)) --> [the].
of(of(of)) --> [of].
assignverb(assignverb(is)) --> [is].
assignverb(assignverb(are)) --> [are].

stmt(stmt(SI), Response) --> interr(SI, Response).
interr(interr(W,AV,PP,O,NP), Response) --> what(W), assignverb(AV), propph(PP, Property), of(O), nounp(NP, Subject),
      {lookup(Property,Subject,Response)}.
what(what(what)) --> [what].

stmt(stmt(SF), Response) --> infer(SF, Response).
infer(infer(NP,AV,DS), Response) --> nounp(NP, Subject), assignverb(AV), descrip(DS, Description),
      {learned(P, Description), tentAdd(P, Subject, Description, Response)}, !.

/*
 * Part 2 - Manipulating the Knowledge Base
 *  Well-formed declarative sentences will evoke tentAdd/4. The arguments are
 *    meant to correspond with the Property, Subject, Description, and Ack,
 *    which is short for "acknowledgement", which will eventually be bound to
 *    the Response in execute/2. Ack will be bound as follows:
 *      [i,know] the fact already exists in the knowledge base
 *      [no,it,is,D] the sentence contradicts a previously-known fact; there are
 *        two rules with this sort of structure because the description may be
 *        single-worded or multi-worded, so it just makes the response look a
 *        little smoother
 *      [ok] the fact does not exist, so we will dynamically add it to the know-
 *        ledge base
 *  Well-formed interrogative sentences will evoke lookup/3. The arguments are
 *    meant to correspond with the Property, Subject, and Ans, which is short
 *    for "answer", which will eventually be bound to the Response in execute/2.
 *    Ans will be bound as follows:
 *      [it,is,X] there exists the Subject associated with the Property in the
 *        knowledge base, so we will respond with it; similar to above, there
 *        are two rules with this sort of structure because the description may
 *        be single-worded or multi-worded, so the response will be a flattened
 *        list instead of a list with atoms and a list
 *      [i,do,not,know] there is no association between the Property and Subject
 *        in the knowledge base
 *  Well-formed inference sentences will evoke learned/2. It just verifies that
 *    there is indeed a fact that has a Property and Description pair.
 */

tentAdd(Property,Subject,Descrip,Ack):-fact(Property,Subject,Descrip),
                                       Ack=[i,know].
tentAdd(Property,Subject,Descrip,Ack):-fact(Property,Subject,D),
                                       D\=Descrip,
                                       isList(D),
                                       append([no,it,is],D,Ack).
tentAdd(Property,Subject,Descrip,Ack):-fact(Property,Subject,D),
                                       D\=Descrip, Ack=[no,it,is,D].
tentAdd(Property,Subject,Descrip,Ack):-assert(fact(Property,Subject,Descrip)),
                                       Ack=[ok].

lookup(Property,Subject,Ans):-fact(Property,Subject,X),
                              isList(X),
                              append([it,is],X,Ans).
lookup(Property,Subject,Ans):-fact(Property,Subject,X),
                              Ans=[it,is,X].
lookup(Property,Subject,[i,do,not,know]).

learned(Property,Descrip):-fact(Property,_,Descrip).

/*
 * Part 3 - The Execute Predicate
 *   execute/2 is a predicate that accepts two arguments: the first must be
 *     instantiated as a list of atoms representing an acceptable sentence
 *     described by the grammar and whose second argument is a non-instantiated
 *     variable which will be bound as the response. The responses are bound as
 *     follows:
 *       A. If the passed sentence was a declarative:
 *          [ok]: the statement was not yet known and has been added.
 *          [i,know]: the information parsed from the statement has already been
 *            added to the knowledge base.
 *          [no,it,is,<description>]: the statement contradicts a fact in the
 *            knowledge base for the corresponding subject and property type. In
 *            other words, a description for a given property and subject cannot
 *            be asserted twice. (Example: Inputting both "The color of the car
 *            is blue" AND "The color of the car is green" will respond "No, it
 *            is blue")
 *       B. If the passed sentence was an interrogative:
 *          [i,do,not,know]: the answer does not exist in the knowledge base.
 *          [it,is,<description>]: the answer exists in the knowledge base and
 *            is stated.
 *       C. If the execute/2 arguments violated any of the two rules:
 *          [input,is,invalid,so,please,try,again]: the first argument was not
 *            a list of atoms acceptable by the language.
 *          false: the second argument was not a variable.
 *          See Sample 4 of the APPENDIX for improper execute calls.
 */
execute(Stmt,Response):-var(Stmt),
                        var(Response),
                        Stmt=[],
                        Response=[input,is,invalid,so,please,try,again].
execute(Stmt,Response):-Stmt = [_|_],
                        var(Response),
                        stmt(_,Response,Stmt,[]).
execute(Stmt,[input,is,invalid,so,please,try,again]).

/*
 * (Other)
 *   isList/1 - a simple predicate that checks if the argument is a list
 */
isList([]).
isList([_|_]).

/*
 * APPENDIX
 *
 *   Sample 1. Ordinary Interaction
 *      ?- execute([what,is,the,color,of,the,car],R).
 *      R = [i, do, not, know] .
 *      ?- execute([the,color,of,the,car,is,blue],R).
 *      R = [ok] .
 *      ?- execute([the,color,of,the,car,is,green],R).
 *      R = [no, it, is, blue] .
 *      ?- execute([the,color,of,the,car,is,blue],R).
 *      R = [i, know] .
 *      ?- execute([what,is,the,color,of,the,car],R).
 *      R = [it, is, blue] .
 *
 *   Sample 2. Flattened List Response
 *      Without "flattening" the list, a multi-worded Description may produce
 *      a Response like so [it,is,[4,meters]], it produces one list:
 *      ?- exeucte([the,length,of,the,board,is,4,meters],R).
 *      R = [ok] .
 *      ?- execute([what,is,the,length,of,the,board],R).
 *      R = [it, is, 4, meters] .
 *
 *   Sample 3. Inference
 *     Sample 3A. Ordinary interaction from the beginning, when the knowledge
 *     base is empty
 *         ?- execute([the,sky,is,blue],R).
 *         R = [input, is, invalid, so, please, try, again] .
 *       It doesn't know blue is a color.
 *         ?- execute([the,color,of,the,bike,is,blue],R).
 *         R = [ok] .
 *         ?- execute([what,is,the,color,of,the,bike],R).
 *         R = [it, is, blue] .
 *       We have added a fact to the knowledge base and have associated the
 *       Description, blue, with the Property, color.
 *         ?- execute([the,sky,is,blue],R).
 *         R = [ok] .
 *         ?- execute([what,is,the,color,of,the,sky],R).
 *         R = [it, is, blue] .
 *       So now the program will assume that "blue" is a color.
 *         ?- execute([the,bike,is,blue],R).
 *         R = [i, know] .
 *     Sample 3B. Not-Very-Smart Inference Potential
 *       Admittedly, the inference is not very sophisticated. Recall that
 *       predicate order is important!
 *         ?- execute([the,color,of,the,sky,is,blue],R).
 *         R = [ok] .
 *         ?- execute([the,mood,of,the,boy,is,blue],R).
 *         R = [ok] .
 *       Two Properties now have the Description "blue".
 *         ?- listing(fact).
 *         :- dynamic fact/3.
 *         fact(color, sky, blue).
 *         fact(mood, boy, blue).
 *         true.
 *       Note the order that it exists in the knowledge base.
 *         ?- execute([the,flower,is,blue],R).
 *         R = [ok] .
 *         ?- listing(fact).
 *         :- dynamic fact/3.
 *         fact(color, sky, blue).
 *         fact(mood, boy, blue).
 *         fact(color, flower, blue).
 *       So the inference assumes "blue" is talking about a "color".
 *  Sample 4. Bad execute/2 Arguments
 *      ?- execute([],R).
 *      R = [input, is, invalid, so, please, try, again].
 *      ?- execute([bad],R).
 *      R = [input, is, invalid, so, please, try, again].
 *      ?- execute(S,R).
 *      S = [],
 *      R = [input, is, invalid, so, please, try, again] .
 *      ?- execute(S,r).
 *      false.
 */
