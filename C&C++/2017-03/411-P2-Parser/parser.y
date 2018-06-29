// Declarations
%{
#include <stdlib.h>
#include <stdio.h>
%}

%start Program

%token _boolean
%token _break
%token _class
%token _double
%token _else
%token _extends
%token _for
%token _if
%token _implements
%token _int
%token _interface
%token _newarray
%token _println
%token _readln
%token _return
%token _string
%token _void
%token _while
%token _add
%token _sub
%token _mult
%token _div
%token _mod
%token _lessequal
%token _greaterequal
%token _notequal
%token _less
%token _greater
%token _equal
%token _and
%token _or
%token _not
%token _assignop
%token _semicolon
%token _comma
%token _period
%token _leftparen
%token _rightparen
%token _leftbracket
%token _rightbracket
%token _leftbrace
%token _rightbrace
%token _intconstant
%token _doubleconstant
%token _stringconstant
%token _booleanconstant
%token _id

%nonassoc "then"
%nonassoc _else

%right _id
%nonassoc "emptyStmt"
%right _assignop
%left _or
%left _and
%nonassoc _equal _notequal
%nonassoc _lessequal _greaterequal _less _greater
%left _add _sub
%left _mult _div _mod
%nonassoc _not "uminus"
%nonassoc _leftbracket _period

%%

// Rules
Program: // 1
      Decl
               {printf("[reduce 01.1]");}
      | Program Decl
               {printf("[reduce 01.2]");}
;

Decl: // 2
      VariableDecl
               {printf("[reduce 02.1]");}
      | FunctionDecl
               {printf("[reduce 02.2]");}
      | ClassDecl
               {printf("[reduce 02.3]");}
      | InterfaceDecl
               {printf("[reduce 02.4]");}
;

VariableDecl: // 3
      Variable _semicolon
               {printf("[reduce 03.1]");}
;

Variable: // 4
      Type _id
               {printf("[reduce 04.1]");}
;

Type: // 5
      _int
               {printf("[reduce 05.1]");}
      | _double
               {printf("[reduce 05.2]");}
      | _boolean
               {printf("[reduce 05.3]");}
      | _string
               {printf("[reduce 05.4]");}
      | Type _leftbracket _rightbracket
               {printf("[reduce 05.5]");}
      | _id
               {printf("[reduce 05.6]");}
;

FunctionDecl: // 6
      Type _id _leftparen Formals _rightparen StmtBlock
               {printf("[reduce 06.1]");}
      | _void _id _leftparen Formals _rightparen StmtBlock
               {printf("[reduce 06.2]");}
;

Formals: // 7
      VarList
               {printf("[reduce 07.1]");}
      | /* empty */
               {printf("[reduce 07.2]");}
;

VarList: // for the Variable+, with , only if values follow
// Variable+ from Formals
      Variable _comma VarList
               {printf("[reduce 07.1.1]");}
      | Variable
               {printf("[reduce 07.1.2]");}
;

ClassDecl: // 8
      _class _id Extension Implementation _leftbrace OptionalField _rightbrace
               {printf("[reduce 08.1]");}
;

Extension: // 0 or 1 of "extends id"
// <extends id> from ClassDecl
      /* empty */
               {printf("[reduce 08.1.1.1]");}
      | _extends _id
               {printf("[reduce 08.1.1.2]");}
;

Implementation: // 0 or 1 of "implements id+,"
// <implements id+,> from ClassDecl
      /* empty */
               {printf("[reduce 08.1.2.1]");}
      | _implements IDList
               {printf("[reduce 08.1.2.2]");}
;

IDList: // for id+, with , only if values follow
// id+, from Implementation from ClassDecl
      _id _comma IDList
               {printf("[reduce 08.1.2.2.1]");}
      | _id
               {printf("[reduce 08.1.2.2.2]");}
;

OptionalField: // 0+ Fields
// Field* from ClassDecl
      /* empty */
               {printf("[reduce 08.1.3.1]");}
      | OptionalField Field
               {printf("[reduce 08.1.3.2]");}
;

Field: // 9
      VariableDecl
               {printf("[reduce 09.1]");}
      | FunctionDecl
               {printf("[reduce 09.2]");}
;

InterfaceDecl: // 10
      _interface _id _leftbrace Prototypes _rightbrace
               {printf("[reduce 10.1]");}
;

Prototypes:
// Prototypes* from InterfaceDecl
      /* empty */
               {printf("[reduce 10.1.1]");}
      | Prototypes Prototype
               {printf("[reduce 10.1.2]");}
;

Prototype: // 11
      Type _id _leftparen Formals _rightparen _semicolon
               {printf("[reduce 11.2]");}
      | _void _id _leftparen Formals _rightparen _semicolon
               {printf("[reduce 11.3]");}
;

StmtBlock: // 12
      _leftbrace StmtVarDecl StmtBlockStmt _rightbrace
               {printf("[reduce 12.1]");}
;

StmtVarDecl: // can have 0+ VariableDecl
// VariableDecl* from StmtBlock
      /* empty */
               {printf("[reduce 12.1.1.1]");}
      | StmtVarDecl VariableDecl
               {printf("[reduce 12.1.1.2]");}
;

StmtBlockStmt: // can have 0+ Stmt
// Stmt* from StmtBlock
      /* empty */      %prec "emptyStmt"
               {printf("[reduce 12.1.2.1]");}
      | StmtBlockStmt Stmt
               {printf("[reduce 12.1.2.2]");}
;

Stmt: // 13
      _semicolon
               {printf("[reduce 13.0]");}
      | Expr _semicolon
               {printf("[reduce 13.1]");}
      | IfStmt
               {printf("[reduce 13.2]");}
      | WhileStmt
               {printf("[reduce 13.3]");}
      | ForStmt
               {printf("[reduce 13.4]");}
      | BreakStmt
               {printf("[reduce 13.5]");}
      | ReturnStmt
               {printf("[reduce 13.6]");}
      | PrintStmt
               {printf("[reduce 13.7]");}
      | StmtBlock
               {printf("[reduce 13.8]");}
;

OptionalExpr: // 0 or 1 occurrence of Expr
// <Expr> from 13 Stmt
// <Expr> from 16 ForStmt
// <Expr> from 18 ReturnStmt
      /* empty */
               {printf("[reduce 25.1]");}
      | Expr
               {printf("[reduce 25.2]");}
;

IfStmt: // 14
      _if _leftparen Expr _rightparen Stmt    %prec "then"
               {printf("[reduce 14.1]");}
      | _if _leftparen Expr _rightparen Stmt _else Stmt
               {printf("[reduce 14.2]");}
;

WhileStmt: // 15
      _while _leftparen Expr _rightparen Stmt
               {printf("[reduce 15.1]");}
;

ForStmt: // 16
      _for _leftparen OptionalExpr _semicolon Expr _semicolon OptionalExpr _rightparen Stmt
               {printf("[reduce 16.1]");}
;

BreakStmt: // 17
      _break _semicolon
               {printf("[reduce 17.1]");}
;

ReturnStmt: // 18
      _return OptionalExpr _semicolon
               {printf("[reduce 18.1]");}
;

PrintStmt: // 19
      _println _leftparen ExprList _rightparen _semicolon
               {printf("[reduce 19.1]");}
;

ExprList: // 1 or more Expr with commas only if there is another Expr
// Expr+, from 19
// Expr+, from 23
      Expr
               {printf("[reduce 26.1]");}
      | Expr _comma ExprList
               {printf("[reduce 26.2]");}
;

Expr: // 20
   // Assignment
      Lvalue _assignop Expr
               {printf("[reduce 20.01]");}
   // Constant
      | Constant
               {printf("[reduce 20.02]");}
   // Lvalue
      | Lvalue
               {printf("[reduce 20.03]");}
   // Call
      | Call
               {printf("[reduce 20.04]");}
   // (Expr)
      | _leftparen Expr _rightparen
               {printf("[reduce 20.05]");}
   // Arithmetic
      | Expr _add Expr
               {printf("[reduce 20.06]");}
      | Expr _sub Expr
               {printf("[reduce 20.07]");}
      | Expr _mult Expr
               {printf("[reduce 20.08]");}
      | Expr _div Expr
               {printf("[reduce 20.09]");}
      | Expr _mod Expr
               {printf("[reduce 20.10]");}
   // Unary minus
      | _sub Expr %prec "uminus"
               {printf("[reduce 20.11]");}
   // Relational
      | Expr _less Expr
               {printf("[reduce 20.12]");}
      | Expr _lessequal Expr
               {printf("[reduce 20.13]");}
      | Expr _greater Expr
               {printf("[reduce 20.14]");}
      | Expr _greaterequal Expr
               {printf("[reduce 20.15]");}
      | Expr _equal Expr
               {printf("[reduce 20.16]");}
      | Expr _notequal Expr
               {printf("[reduce 20.17]");}
   // Logical
      | Expr _and Expr
               {printf("[reduce 20.18]");}
      | Expr _or Expr
               {printf("[reduce 20.19]");}
      | _not Expr
               {printf("[reduce 20.20]");}
   // Other
      | _readln _leftparen _rightparen
               {printf("[reduce 20.21]");}
      | _newarray _leftparen _intconstant _comma Type _rightparen
               {printf("[reduce 20.22]");}
;

Lvalue: // 21
      _id
               {printf("[reduce 21.1]");}
      | Lvalue _leftbracket Expr _rightbracket
               {printf("[reduce 21.2]");}
      | Lvalue _period _id
               {printf("[reduce 21.3]");}
;

Call: // 22
      _id _leftparen Actuals _rightparen
               {printf("[reduce 22.1]");}
      | _id _period _id _leftparen Actuals _rightparen
               {printf("[reduce 22.2]");}
;

Actuals: // 23
      /* empty */
               {printf("[reduce 23.1]");}
      | ExprList
               {printf("[reduce 23.2]");}
;

Constant: // 24
      _intconstant
               {printf("[reduce 24.1]");}
      | _doubleconstant
               {printf("[reduce 24.2]");}
      | _stringconstant
               {printf("[reduce 24.3]");}
      | _booleanconstant
               {printf("[reduce 24.4]");}
;

%%
// Programs
void yyerror(char *s)
{
   fprintf(stderr, "%s\n", s);
}

int main()
{
   // yyparse() invokes yylex() to obtain the next token
   int ntoken = yyparse();
   if (ntoken == 0)
   {
      printf("[accept]\n");
   }
   else
   {
      printf("[reject]\n");
   }
}
