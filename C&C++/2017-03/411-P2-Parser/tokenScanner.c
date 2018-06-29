/*
Rachel Chiang
CS 411-01 Compilers and Interpreters
Project #1 Lexical Analyzer for Toy

This is meant to be used with Lex. Lex scans and provides this code with token
values using yylex() and prints out the translated tokens as well as its
corresponding trie structure.
*/
#include <stdio.h>
#include <stdlib.h>
#include "tokens.h"

// yy externals
extern int yylex();
extern char* yytext;

// function prototypes
void search(char*);
void fillSymbolTable(char*, int);
void printTrie();
int jumpIndex(int);
void growTrie();
void translateToken(int);

// useful constants
const int SWITCH_SYMBOLS = 52; // 52 first characters
const int switch_a = 26; // location of a in switch
// ASCII values
const int CAP_A = 65;
const int CAP_Z = 90;
const int LOW_A = 97;
const int LOW_Z = 122;

// trie structure variables
int maxTransition = 500;
int endOfTable = 0;
int *switchArray;
char *symbols;
int *nextArray;

/*
First, the trie structure is initialized. Then, it uses Lex to pick up tokens
(ntokens) or the character string itself (yytext). This method calls on the
function translateToken(int) to print the token's meaning. If the token
is a keyword or identifier, it will call search(char*) to store the character
string into the trie. Finally it will print the trie structure and end.
*/
int main()
{
    // initialize the three arrays
    switchArray = (int *) malloc((SWITCH_SYMBOLS * sizeof(int)));
    symbols = (char *) malloc(maxTransition * sizeof(char));
    nextArray = (int *) malloc(maxTransition * sizeof(int));

    for (int i = 0; i < SWITCH_SYMBOLS; ++i)
    {
        switchArray[i] = -1;
        symbols[i] = '*';
        nextArray[i] = -1;
    }
    for (int i = SWITCH_SYMBOLS; i < maxTransition; ++i)
    {
        symbols[i] = '*';
        nextArray[i] = -1;
    }

    // lexical analysis
    int ntoken;

    ntoken = yylex();
    while (ntoken)
    {
        // output tokens in a human-friendly format
        translateToken(ntoken);

        // if we received a keyword or identifier, we store it in trie
        if ((ntoken >= 0 && ntoken < 19) || (ntoken == 47)
             || (ntoken == 46))
        {
            // 19-45 are not keys or ids
            // note, 46 is a boolean const (true/false), which are reserved
            // words, so they have been stored. I'm not sure if that's how it
            // should be but that's how it is in the example in the project
            // guidelines.
            search(yytext);
        }

        // receive the next
        ntoken = yylex();
    }

    // Print the trie
    printf("\n");
    printTrie();

    // end
    free(switchArray);
    free(symbols);
    free(nextArray);
    return 0;
}

/*
This function searches the trie structure, or more specifically, it adds to the
switch array if necessary. Otherwise, it calls fillSymbolTable(char*, int) for 
the next step.
*/
void search(char* storeMe)
{
    if (endOfTable >= ((maxTransition*3)/4))
    {
        // Here we have a method of growing the trie if we have to
        // I somewhat arbitrarily picked 75%
        growTrie();
    }

    int index = 0;

    // Process the starting symbol
    // We must adjust character ascii values to match the corresponding cell
    // of the switch structure
    int symbolValue = ((int)storeMe[index]);
    if (symbolValue >= CAP_A && symbolValue <= CAP_Z)
    {
        // the character is A-Z
        symbolValue -= CAP_A;
    }
    else if (symbolValue >= LOW_A && symbolValue <= LOW_Z)
    {
        // the character is a-z
        symbolValue -= LOW_A;
        symbolValue += switch_a;
    }

    // Now we can check the switch array with the fixed char ascii value
    if (switchArray[symbolValue] == -1)
    {
        // key/id starting with letter has not been used
        // initialize switch[symbolValue] to end of table
        switchArray[symbolValue] = endOfTable;
        fillSymbolTable(storeMe, endOfTable);
    }
    else
    {
        // key/id starting with letter has been used
        // fill symbol table with remaining
        fillSymbolTable(storeMe, switchArray[symbolValue]);
    }
}

/*
This method fills the symbol table with the remaining characters of the keyword
or identifier. The startIndex actually pertains to the index of the symbol table
that should be used to search and fill the symbol table.
*/
void fillSymbolTable(char* storeMe, int startIndex)
{
    // This index is for traversing the keyword/identifier
    int index = 1;

    while (storeMe[index] != '\0')
    {
        if (symbols[startIndex] == storeMe[index]
            || symbols[startIndex] == '*')
        {
            // the substring exists in table or the entry has yet to be
            // initialized
            symbols[startIndex] = storeMe[index];
            ++startIndex;
            // only now we are ready to process next, so advance the key/id
            // pointer to the next
            ++index;
        }
        else
        {
            // the substring was not found in the table, or it cannot fit
            // so we have to jump
            startIndex = jumpIndex(startIndex);
        }
    }

    // finished processing so we must add a terminating symbol
    // which will be $ in this case.
    while (symbols[startIndex] != '*')
    {
        startIndex = jumpIndex(startIndex);
    }

    symbols[startIndex] = '$';
    endOfTable = startIndex + 1;
}

/*
This function returns an integer of the next index to use after jumping
*/
int jumpIndex(int startIndex)
{
    if (nextArray[startIndex] != -1)
    {
        // if the next is not empty, we jump
        startIndex = nextArray[startIndex];
    }
    else
    {
        // if the nextArray cell has yet to be used, create a new jump
        nextArray[startIndex] = endOfTable;
        startIndex = endOfTable;
    }
    return startIndex;
}

/*
This simply prints the trie structure, which was only kind of simple. First it
prints the switch array, then the symbol array, and finally the next array. I
chose to print in columns of 13 for the switch array because I like having
everything aligned. I printed in columns of 10 for the other two arrays though,
for no particular reason. It could have been any number, but I did want the
column "width" to be wider since the symbols array grows, and maybe the indices
would eventually be wider than 6 spaces.
*/
void printTrie()
{
    // The iteration is used for the print loops. I'll explain more
    int iteration = 0;

    // SWITCH
    printf("switch:\n");
    int switchIdx = 0;
    while (switchIdx < SWITCH_SYMBOLS)
    {
        if (iteration == 0)
        {
            // switch iteration 0 is for printing the characters that correspond
            // with the switch index.
            if (switchIdx < 26)
            {
                // uppercase letters
                for (int i = switchIdx; i < switchIdx + 13; ++i)
                {
                    printf("%6c", (char)(CAP_A + i));
                }
            }
            else
            {
                // lowercase letters
                for (int i = switchIdx; i < switchIdx + 13; ++i)
                {
                    printf("%6c", (char)(LOW_A + i - 26));
                }
            }
            // flip the iteration to 1
            iteration = 1;
            printf("\n");
        }
        if (iteration == 1)
        {
            // switch iteration 1 is for printing the matching indices of the
            // symbols array
            for (int i = switchIdx; i < switchIdx + 13; ++i)
            {
                // simply integers
                printf("%6d", switchArray[i]);
            }
            // flip the iteration back to 0 to go again
            iteration = 0;
            // and move to the next batch to print
            switchIdx += 13;
            printf("\n\n");
        }
    }

    // SYMBOL and NEXT
    printf("\nindex:\nsymbol:\nnext:\n");

    // Similar to above
    int symIdx = 0;
    iteration = 0;
    // I used an int as a kind of end-trigger because I was taught that breaks
    // are bad. Usually I use boolean variables though. I will explain more
    int end = 0;

    while (end == 0)
    {
        if (iteration == 0)
        {
            // symbol/next iteration 0 means that we're printing the index which
            // populates the first row of each triplet (index/symbol/next)
            for (int i = symIdx; i < symIdx + 10; ++i)
            {
                if (symbols[i] != '*')
                {
                    // I only really care to print things that are actually used
                    printf("%8d", i);
                }
            }
            // flip the iteration to 1
            iteration = 1;
            printf("\n");
        }
        if (iteration == 1)
        {
            // symbol/next iteration 1 is for printing the contents of the
            // symbol array
            for (int i = symIdx; i < symIdx + 10; ++i)
            {
                if (symbols[i] != '*') // don't care for empty cells (*)
                {
                    printf("%8c", symbols[i]);
                }
            }
            // flip the iteration to 2
            iteration = 2;
            printf("\n");
        }
        if (iteration == 2)
        {
            // symbol/next iteration 2 is for printing the contents of the next
            // array
            for (int i = symIdx; i < symIdx + 10; ++i)
            {
                if (symbols[i] != '*') // not printing empty cells (*)
                {
                    if (nextArray[i] != -1)
                    {
                        printf("%8d", nextArray[i]);
                    }
                    else // also aren't printing next if it's empty
                    {
                        printf("        ");
                    }
                }
                else
                {
                    // We only end after printing the three rows and we should 
                    // only reach *'s if we're at the end of the table
                    end = 1;
                }
            }
            // flip the iteration back to 0
            iteration = 0;
            // move up to the next row
            symIdx += 10;

            printf("\n\n");
        }
    }
}

/*
This method grows the trie structure, or more precisely, the symbol and next
arrays. It makes a copy of the two and then deletes the old one and points to
the new arrays.
*/
void growTrie()
{
    // new arrays
    char *newSymbols;
    newSymbols = (char *) malloc((2*maxTransition)*(sizeof(char)));
    int *newNext;
    newNext = (int *) malloc((2*maxTransition)*(sizeof(int)));

    // copy the old, fill the rest of the new, and kill the old
    for (int i = 0; i < 2 * maxTransition; ++i)
    {
        if (i < maxTransition)
        {
            newSymbols[i] = symbols[i];
            newNext[i] = nextArray[i];
        }
        else
        {
            newSymbols[i] = '*';
            newNext[i] = -1;
            free(symbols);
            free(nextArray);
            symbols = NULL;
            nextArray = NULL;
        }
    }
    // re-point and double the size
    symbols = newSymbols;
    nextArray = newNext;
    maxTransition *= 2;
}

/*
This was long and tedious and there maybe, hopefully, is a better way to do
this. All it does is print the string that matches the token value so that 
ordinary humans can read the tokens.
*/
void translateToken(int token)
{
    switch (token)
    {
        case _newLine:
            // I added a new line token because I think it's annoying to
            // read without new lines.
            printf("\n");
            break;
        case _boolean:
            printf("boolean ");
            break;
        case _break:
            printf("break ");
            break;
        case _class:
            printf("class ");
            break;
        case _double:
            printf("double ");
            break;
        case _else:
            printf("else ");
            break;
        case _extends:
            printf("extends ");
            break;
        case _for:
            printf("for ");
            break;
        case _if:
            printf("if ");
            break;
        case _implements:
            printf("implements ");
            break;
        case _int:
            printf("int ");
            break;
        case _interface:
            printf("interface ");
            break;
        case _newarray:
            printf("newarray ");
            break;
        case _println:
            printf("println ");
            break;
        case _readln:
            printf("readln ");
            break;
        case _return:
            printf("return ");
            break;
        case _string:
            printf("string ");
            break;
        case _void:
            printf("void ");
            break;
        case _while:
            printf("while ");
            break;
        case _add:
            printf("add ");
            break;
        case _sub:
            printf("sub ");
            break;
        case _mult:
            printf("mult ");
            break;
        case _div:
            printf("div ");
            break;
        case _mod:
            printf("mod ");
            break;
        case _lessequal:
            printf("lessequal ");
            break;
        case _greaterequal:
            printf("greaterequal ");
            break;
        case _notequal:
            printf("notequal ");
            break;
        case _less:
            printf("lessthan ");
            break;
        case _greater:
            printf("greaterthan ");
            break;
        case _equal:
            printf("equals ");
            break;
        case _and:
            printf("and ");
            break;
        case _or:
            printf("or ");
            break;
        case _not:
            printf("not ");
            break;
        case _assignop:
            printf("assignop ");
            break;
        case _semicolon:
            printf("semicolon ");
            break;
        case _comma:
            printf("comma ");
            break;
        case _period:
            printf("period ");
            break;
        case _leftparen:
            printf("leftparen ");
            break;
        case _rightparen:
            printf("rightparen ");
            break;
        case _leftbracket:
            printf("leftbracket ");
            break;
        case _rightbracket:
            printf("rightbracket ");
            break;
        case _leftbrace:
            printf("leftbrace ");
            break;
        case _rightbrace:
            printf("rightbrace ");
            break;
        case _intconstant:
            printf("intconstant ");
            break;
        case _doubleconstant:
            printf("doubleconstant ");
            break;
        case _stringconstant:
            printf("stringconstant ");
            break;
        case _booleanconstant:
            printf("booleanconstant ");
            break;
        case _id:
            printf("id ");
            break;
        default:
            break;
    }
}
