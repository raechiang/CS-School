/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_Y_TAB_H_INCLUDED
# define YY_YY_Y_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    _boolean = 258,
    _break = 259,
    _class = 260,
    _double = 261,
    _else = 262,
    _extends = 263,
    _for = 264,
    _if = 265,
    _implements = 266,
    _int = 267,
    _interface = 268,
    _newarray = 269,
    _println = 270,
    _readln = 271,
    _return = 272,
    _string = 273,
    _void = 274,
    _while = 275,
    _add = 276,
    _sub = 277,
    _mult = 278,
    _div = 279,
    _mod = 280,
    _lessequal = 281,
    _greaterequal = 282,
    _notequal = 283,
    _less = 284,
    _greater = 285,
    _equal = 286,
    _and = 287,
    _or = 288,
    _not = 289,
    _assignop = 290,
    _semicolon = 291,
    _comma = 292,
    _period = 293,
    _leftparen = 294,
    _rightparen = 295,
    _leftbracket = 296,
    _rightbracket = 297,
    _leftbrace = 298,
    _rightbrace = 299,
    _intconstant = 300,
    _doubleconstant = 301,
    _stringconstant = 302,
    _booleanconstant = 303,
    _id = 304
  };
#endif
/* Tokens.  */
#define _boolean 258
#define _break 259
#define _class 260
#define _double 261
#define _else 262
#define _extends 263
#define _for 264
#define _if 265
#define _implements 266
#define _int 267
#define _interface 268
#define _newarray 269
#define _println 270
#define _readln 271
#define _return 272
#define _string 273
#define _void 274
#define _while 275
#define _add 276
#define _sub 277
#define _mult 278
#define _div 279
#define _mod 280
#define _lessequal 281
#define _greaterequal 282
#define _notequal 283
#define _less 284
#define _greater 285
#define _equal 286
#define _and 287
#define _or 288
#define _not 289
#define _assignop 290
#define _semicolon 291
#define _comma 292
#define _period 293
#define _leftparen 294
#define _rightparen 295
#define _leftbracket 296
#define _rightbracket 297
#define _leftbrace 298
#define _rightbrace 299
#define _intconstant 300
#define _doubleconstant 301
#define _stringconstant 302
#define _booleanconstant 303
#define _id 304

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_Y_TAB_H_INCLUDED  */
