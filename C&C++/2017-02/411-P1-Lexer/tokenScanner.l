%{
#include "tokens.h"
%}

%%
[ \t\v\f]			;
"/*"([^*]|(\*+[^*/]))*\*+\/	;
"//".*				;
"boolean"			return _boolean;
"break"				return _break;
"class"				return _class;
"double"			return _double;
"else"				return _else;
"extends"			return _extends;
"for"				return _for;
"if"				return _if;
"implements"			return _implements;
"interface"			return _interface;
"int"				return _int;
"newarray"			return _newarray;
"println"			return _println;
"readln"			return _readln;
"return"			return _return;
"string"			return _string;
"void"				return _void;
"while"				return _while;

\"(\\.|[^"])*\"			return _stringconstant;

"true"				return _booleanconstant;
"false"				return _booleanconstant;

[a-zA-Z][_a-zA-Z0-9]*		return _id;
[0-9]+"."[0-9]*[Ee]([+-]?)[0-9]+	return _doubleconstant;
[0-9]+"."[0-9]*			return _doubleconstant;
0[Xx][a-fA-F0-9]+		return _intconstant;
[0-9]+				return _intconstant;

"+"				return _add;
"-"				return _sub;
"*"				return _mult;
"/"				return _div;
"%"				return _mod;
"<="				return _lessequal;
">="				return _greaterequal;
"!="				return _notequal;
"<"				return _less;
">"				return _greater;
"=="				return _equal;
"&&"				return _and;
"||"				return _or;
"!"				return _not;
"="				return _assignop;
";"				return _semicolon;
","				return _comma;
"."				return _period;
"("				return _leftparen;
")"				return _rightparen;
"["				return _leftbracket;
"]"				return _rightbracket;
"{"				return _leftbrace;
"}"				return _rightbrace;

[\n]				return _newLine;
.				;

%%

int yywrap(void)
{
    return 1;
}
