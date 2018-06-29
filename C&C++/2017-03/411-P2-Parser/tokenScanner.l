%{
#include <stdlib.h>
#include "y.tab.h"
extern int yylval;
%}

%%
[ \t\v\f]			;
"/*"([^*]|(\*+[^*/]))*\*+\/	;
"//".*				;
"boolean"			{printf("boolean "); return _boolean;}
"break"				{printf("break "); return _break;}
"class"				{printf("class "); return _class;}
"double"			{printf("double "); return _double;}
"else"				{printf("else "); return _else;}
"extends"			{printf("extends "); return _extends;}
"for"				{printf("for "); return _for;}
"if"				{printf("if "); return _if;}
"implements"			{printf("implements "); return _implements;}
"interface"			{printf("interface "); return _interface;}
"int"				{printf("int "); return _int;}
"newarray"			{printf("newarray "); return _newarray;}
"println"			{printf("println "); return _println;}
"readln"			{printf("readln "); return _readln;}
"return"			{printf("return "); return _return;}
"string"			{printf("string "); return _string;}
"void"				{printf("void "); return _void;}
"while"				{printf("while "); return _while;}

\"(\\.|[^"])*\"			{printf("stringconst "); return _stringconstant;}

"true"				{printf("booleanconstT "); return _booleanconstant;}
"false"				{printf("booleanconstF "); return _booleanconstant;}

[a-zA-Z][_a-zA-Z0-9]*		{printf("id "); return _id;}
[0-9]+"."[0-9]*[Ee]([+-]?)[0-9]+	{printf("doubleconst "); return _doubleconstant;}
[0-9]+"."[0-9]*			{printf("doubleconst "); return _doubleconstant;}
0[Xx][a-fA-F0-9]+		{printf("intconst "); return _intconstant;}
[0-9]+				{printf("intconst "); return _intconstant;}

"+"				{printf("add "); return _add;}
"-"				{printf("sub "); return _sub;}
"*"				{printf("mult "); return _mult;}
"/"				{printf("div "); return _div;}
"%"				{printf("mod "); return _mod;}
"<="				{printf("<= "); return _lessequal;}
">="				{printf(">= "); return _greaterequal;}
"!="				{printf("!= "); return _notequal;}
"<"				{printf("< "); return _less;}
">"				{printf("> "); return _greater;}
"=="				{printf("== "); return _equal;}
"&&"				{printf("&& "); return _and;}
"||"				{printf("|| "); return _or;}
"!"				{printf("!"); return _not;}
"="				{printf("= "); return _assignop;}
";"				{printf("; "); return _semicolon;}
","				{printf(", "); return _comma;}
"."				{printf(". "); return _period;}
"("				{printf("("); return _leftparen;}
")"				{printf(")"); return _rightparen;}
"["				{printf("["); return _leftbracket;}
"]"				{printf("]"); return _rightbracket;}
"{"				{printf("{"); return _leftbrace;}
"}"				{printf("}"); return _rightbrace;}

[\n]				;//printf("\n");/*return _newLine;*/
.				;

%%

int yywrap(void)
{
    return 1;
}
