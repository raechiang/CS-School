/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison implementation for Yacc-like parsers in C

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

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "3.0.4"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* Copy the first part of user declarations.  */
#line 2 "parser.y" /* yacc.c:339  */

#include <stdlib.h>
#include <stdio.h>

#line 71 "y.tab.c" /* yacc.c:339  */

# ifndef YY_NULLPTR
#  if defined __cplusplus && 201103L <= __cplusplus
#   define YY_NULLPTR nullptr
#  else
#   define YY_NULLPTR 0
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* In a future release of Bison, this section will be replaced
   by #include "y.tab.h".  */
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

/* Copy the second part of user declarations.  */

#line 220 "y.tab.c" /* yacc.c:358  */

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

#ifndef YY_ATTRIBUTE
# if (defined __GNUC__                                               \
      && (2 < __GNUC__ || (__GNUC__ == 2 && 96 <= __GNUC_MINOR__)))  \
     || defined __SUNPRO_C && 0x5110 <= __SUNPRO_C
#  define YY_ATTRIBUTE(Spec) __attribute__(Spec)
# else
#  define YY_ATTRIBUTE(Spec) /* empty */
# endif
#endif

#ifndef YY_ATTRIBUTE_PURE
# define YY_ATTRIBUTE_PURE   YY_ATTRIBUTE ((__pure__))
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# define YY_ATTRIBUTE_UNUSED YY_ATTRIBUTE ((__unused__))
#endif

#if !defined _Noreturn \
     && (!defined __STDC_VERSION__ || __STDC_VERSION__ < 201112)
# if defined _MSC_VER && 1200 <= _MSC_VER
#  define _Noreturn __declspec (noreturn)
# else
#  define _Noreturn YY_ATTRIBUTE ((__noreturn__))
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

#if defined __GNUC__ && 407 <= __GNUC__ * 100 + __GNUC_MINOR__
/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN \
    _Pragma ("GCC diagnostic push") \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")\
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# define YY_IGNORE_MAYBE_UNINITIALIZED_END \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif


#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYSIZE_T yynewbytes;                                            \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / sizeof (*yyptr);                          \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, (Count) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYSIZE_T yyi;                         \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  20
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   416

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  53
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  35
/* YYNRULES -- Number of rules.  */
#define YYNRULES  95
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  188

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   307

#define YYTRANSLATE(YYX)                                                \
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, without out-of-bounds checking.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,    76,    76,    78,    83,    85,    87,    89,    94,    99,
     104,   106,   108,   110,   112,   114,   119,   121,   126,   129,
     134,   136,   141,   148,   149,   156,   157,   163,   165,   172,
     173,   178,   180,   185,   192,   193,   201,   203,   208,   215,
     216,   222,   224,   229,   231,   233,   235,   237,   239,   241,
     243,   245,   254,   255,   260,   262,   267,   272,   277,   282,
     287,   294,   296,   303,   306,   309,   312,   315,   318,   320,
     322,   324,   326,   329,   332,   334,   336,   338,   340,   342,
     345,   347,   349,   352,   354,   359,   361,   363,   368,   370,
     376,   377,   382,   384,   386,   388
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "_boolean", "_break", "_class",
  "_double", "_else", "_extends", "_for", "_if", "_implements", "_int",
  "_interface", "_newarray", "_println", "_readln", "_return", "_string",
  "_void", "_while", "_add", "_sub", "_mult", "_div", "_mod", "_lessequal",
  "_greaterequal", "_notequal", "_less", "_greater", "_equal", "_and",
  "_or", "_not", "_assignop", "_semicolon", "_comma", "_period",
  "_leftparen", "_rightparen", "_leftbracket", "_rightbracket",
  "_leftbrace", "_rightbrace", "_intconstant", "_doubleconstant",
  "_stringconstant", "_booleanconstant", "_id", "\"then\"",
  "\"emptyStmt\"", "\"uminus\"", "$accept", "Program", "Decl",
  "VariableDecl", "Variable", "Type", "FunctionDecl", "Formals", "VarList",
  "ClassDecl", "Extension", "Implementation", "IDList", "OptionalField",
  "Field", "InterfaceDecl", "Prototypes", "Prototype", "StmtBlock",
  "StmtVarDecl", "StmtBlockStmt", "Stmt", "OptionalExpr", "IfStmt",
  "WhileStmt", "ForStmt", "BreakStmt", "ReturnStmt", "PrintStmt",
  "ExprList", "Expr", "Lvalue", "Call", "Actuals", "Constant", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305,   306,   307
};
# endif

#define YYPACT_NINF -164

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-164)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  (!!((Yytable_value) == (-1)))

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
      74,  -164,   -41,  -164,  -164,   -29,  -164,   -27,  -164,    27,
    -164,  -164,    -7,   -38,  -164,  -164,  -164,    39,    28,    45,
    -164,  -164,  -164,    36,    50,    41,    80,  -164,    25,  -164,
      25,  -164,    47,    54,    82,    65,   -37,    66,  -164,    67,
      71,  -164,  -164,    68,  -164,   -36,  -164,    25,  -164,    77,
      77,    47,    92,    75,    83,  -164,  -164,  -164,  -164,  -164,
    -164,  -164,  -164,  -164,    25,    25,   106,    81,    85,  -164,
     128,    91,    93,    94,    89,    95,    96,   100,   101,   190,
     107,   190,   190,  -164,   190,  -164,  -164,  -164,  -164,  -164,
     -13,  -164,  -164,  -164,  -164,  -164,  -164,  -164,  -164,   318,
      78,  -164,  -164,  -164,  -164,  -164,   190,   190,    88,   190,
     109,   111,   350,   190,  -164,  -164,   241,   114,   190,   190,
     190,   190,   190,   190,   190,   190,   190,   190,   190,   190,
     190,   190,  -164,   190,   116,   190,   132,   261,   133,   129,
     301,  -164,  -164,   281,  -164,   140,  -164,   141,    26,    26,
    -164,  -164,  -164,   386,   386,   130,   386,   386,   130,   375,
     363,   350,  -164,   219,   190,   174,    25,   144,   190,   174,
     190,  -164,  -164,   334,   175,    -5,  -164,  -164,  -164,   145,
     190,   174,  -164,  -164,   146,  -164,   174,  -164
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,    12,     0,    11,    10,     0,    13,     0,    15,     0,
       2,     4,     0,     0,     5,     6,     7,    23,     0,     0,
       1,     3,     8,     0,     9,     0,    25,    34,    19,    14,
      19,    24,     0,     0,     0,    21,     0,     0,    18,     0,
      28,    26,    29,     0,    33,     0,    35,     0,     9,     0,
       0,     0,     0,     0,     0,    20,    39,    17,    16,    27,
      22,    31,    32,    30,    19,    19,    41,     0,     0,    40,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    52,
       0,     0,     0,    43,     0,    38,    92,    93,    94,    95,
      85,    51,    42,    45,    46,    47,    48,    49,    50,     0,
      65,    66,    64,    37,    36,    58,    52,     0,     0,     0,
       0,     0,    53,     0,    73,    82,     0,     0,    90,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    44,     0,     0,     0,     0,     0,     0,     0,
      61,    83,    59,     0,    67,     0,    91,     0,    68,    69,
      70,    71,    72,    75,    77,    79,    74,    76,    78,    80,
      81,    63,    87,     0,     0,     0,     0,     0,     0,     0,
      90,    88,    86,     0,    54,     0,    60,    62,    56,     0,
      52,     0,    84,    89,     0,    55,     0,    57
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -164,  -164,   178,   -45,    15,     0,   143,     8,   150,  -164,
    -164,  -164,   142,  -164,  -164,  -164,  -164,  -164,    33,  -164,
    -164,  -163,  -105,  -164,  -164,  -164,  -164,  -164,  -164,   -99,
     -65,  -164,  -164,    22,  -164
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     9,    10,    11,    35,    36,    14,    37,    38,    15,
      26,    33,    41,    52,    63,    16,    34,    46,    91,    66,
      70,    92,   111,    93,    94,    95,    96,    97,    98,   146,
      99,   100,   101,   147,   102
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_int16 yytable[] =
{
      13,   136,   174,    23,    23,    23,   178,    61,    17,    13,
     139,    24,    48,    54,   112,    12,   114,   115,   185,   116,
      18,    69,    19,   187,    12,   117,   118,    20,     1,    22,
       1,     3,     2,     3,    45,   182,    23,     4,    39,     4,
       5,   112,   137,     6,   140,     6,     7,    25,   143,   121,
     122,   123,    13,   140,   148,   149,   150,   151,   152,   153,
     154,   155,   156,   157,   158,   159,   160,    12,   161,   177,
     163,    27,    67,    68,     8,   184,     8,     1,    29,     2,
       3,    12,    57,    58,    28,     1,     4,     5,     3,    30,
      31,    32,     6,     7,     4,     1,    40,    42,     3,   173,
       6,    43,    47,   140,     4,   140,    49,    50,    51,     1,
       6,     7,     3,   133,    64,   112,   134,    53,     4,   135,
      56,    71,    65,     8,     6,    72,    44,   103,   106,   104,
     105,     8,    73,   138,   107,   108,    60,    74,    75,   109,
     110,     8,    76,    77,    78,    79,   113,   142,    80,   141,
      81,   119,   120,   121,   122,   123,   124,   125,    -1,   127,
     128,    -1,    82,   145,    83,   162,   175,    84,   164,   167,
     166,    56,    85,    86,    87,    88,    89,    90,    73,   170,
     176,   171,   181,    74,    75,   183,   186,    21,    76,    77,
      78,    79,   179,    59,    80,    62,    81,    55,     0,     0,
       0,     0,     0,     0,    76,     0,    78,     0,    82,     0,
      83,     0,    81,    84,     0,     0,     0,    56,     0,    86,
      87,    88,    89,    90,    82,     0,     0,     0,     0,    84,
       0,     0,     0,     0,     0,    86,    87,    88,    89,    90,
     119,   120,   121,   122,   123,   124,   125,   126,   127,   128,
     129,   130,   131,     0,     0,     0,     0,     0,     0,     0,
       0,   172,   119,   120,   121,   122,   123,   124,   125,   126,
     127,   128,   129,   130,   131,     0,     0,     0,     0,     0,
       0,   144,   119,   120,   121,   122,   123,   124,   125,   126,
     127,   128,   129,   130,   131,     0,     0,     0,     0,     0,
       0,   165,   119,   120,   121,   122,   123,   124,   125,   126,
     127,   128,   129,   130,   131,     0,     0,     0,     0,     0,
       0,   169,   119,   120,   121,   122,   123,   124,   125,   126,
     127,   128,   129,   130,   131,     0,     0,     0,   168,   119,
     120,   121,   122,   123,   124,   125,   126,   127,   128,   129,
     130,   131,     0,     0,   132,   119,   120,   121,   122,   123,
     124,   125,   126,   127,   128,   129,   130,   131,     0,     0,
     180,   119,   120,   121,   122,   123,   124,   125,   126,   127,
     128,   129,   130,   131,   119,   120,   121,   122,   123,   124,
     125,   126,   127,   128,   129,   130,   119,   120,   121,   122,
     123,   124,   125,   126,   127,   128,   129,   119,   120,   121,
     122,   123,    -1,    -1,     0,    -1,    -1
};

static const yytype_int16 yycheck[] =
{
       0,   106,   165,    41,    41,    41,   169,    52,    49,     9,
     109,    49,    49,    49,    79,     0,    81,    82,   181,    84,
      49,    66,    49,   186,     9,    38,    39,     0,     3,    36,
       3,     6,     5,     6,    34,    40,    41,    12,    30,    12,
      13,   106,   107,    18,   109,    18,    19,     8,   113,    23,
      24,    25,    52,   118,   119,   120,   121,   122,   123,   124,
     125,   126,   127,   128,   129,   130,   131,    52,   133,   168,
     135,    43,    64,    65,    49,   180,    49,     3,    42,     5,
       6,    66,    49,    50,    39,     3,    12,    13,     6,    39,
      49,    11,    18,    19,    12,     3,    49,    43,     6,   164,
      18,    19,    37,   168,    12,   170,    40,    40,    37,     3,
      18,    19,     6,    35,    39,   180,    38,    49,    12,    41,
      43,    40,    39,    49,    18,    40,    44,    36,    39,    36,
      36,    49,     4,    45,    39,    39,    44,     9,    10,    39,
      39,    49,    14,    15,    16,    17,    39,    36,    20,    40,
      22,    21,    22,    23,    24,    25,    26,    27,    28,    29,
      30,    31,    34,    49,    36,    49,   166,    39,    36,    40,
      37,    43,    44,    45,    46,    47,    48,    49,     4,    39,
      36,    40,     7,     9,    10,    40,    40,     9,    14,    15,
      16,    17,   170,    51,    20,    52,    22,    47,    -1,    -1,
      -1,    -1,    -1,    -1,    14,    -1,    16,    -1,    34,    -1,
      36,    -1,    22,    39,    -1,    -1,    -1,    43,    -1,    45,
      46,    47,    48,    49,    34,    -1,    -1,    -1,    -1,    39,
      -1,    -1,    -1,    -1,    -1,    45,    46,    47,    48,    49,
      21,    22,    23,    24,    25,    26,    27,    28,    29,    30,
      31,    32,    33,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    42,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    -1,    -1,    -1,    -1,    -1,
      -1,    40,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    -1,    -1,    -1,    -1,    -1,
      -1,    40,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    -1,    -1,    -1,    -1,    -1,
      -1,    40,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    -1,    -1,    -1,    37,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    30,    31,
      32,    33,    -1,    -1,    36,    21,    22,    23,    24,    25,
      26,    27,    28,    29,    30,    31,    32,    33,    -1,    -1,
      36,    21,    22,    23,    24,    25,    26,    27,    28,    29,
      30,    31,    32,    33,    21,    22,    23,    24,    25,    26,
      27,    28,    29,    30,    31,    32,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    21,    22,    23,
      24,    25,    26,    27,    -1,    29,    30
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     3,     5,     6,    12,    13,    18,    19,    49,    54,
      55,    56,    57,    58,    59,    62,    68,    49,    49,    49,
       0,    55,    36,    41,    49,     8,    63,    43,    39,    42,
      39,    49,    11,    64,    69,    57,    58,    60,    61,    60,
      49,    65,    43,    19,    44,    58,    70,    37,    49,    40,
      40,    37,    66,    49,    49,    61,    43,    71,    71,    65,
      44,    56,    59,    67,    39,    39,    72,    60,    60,    56,
      73,    40,    40,     4,     9,    10,    14,    15,    16,    17,
      20,    22,    34,    36,    39,    44,    45,    46,    47,    48,
      49,    71,    74,    76,    77,    78,    79,    80,    81,    83,
      84,    85,    87,    36,    36,    36,    39,    39,    39,    39,
      39,    75,    83,    39,    83,    83,    83,    38,    39,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    30,    31,
      32,    33,    36,    35,    38,    41,    75,    83,    45,    82,
      83,    40,    36,    83,    40,    49,    82,    86,    83,    83,
      83,    83,    83,    83,    83,    83,    83,    83,    83,    83,
      83,    83,    49,    83,    36,    40,    37,    40,    37,    40,
      39,    40,    42,    83,    74,    58,    36,    82,    74,    86,
      36,     7,    40,    40,    75,    74,    40,    74
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    53,    54,    54,    55,    55,    55,    55,    56,    57,
      58,    58,    58,    58,    58,    58,    59,    59,    60,    60,
      61,    61,    62,    63,    63,    64,    64,    65,    65,    66,
      66,    67,    67,    68,    69,    69,    70,    70,    71,    72,
      72,    73,    73,    74,    74,    74,    74,    74,    74,    74,
      74,    74,    75,    75,    76,    76,    77,    78,    79,    80,
      81,    82,    82,    83,    83,    83,    83,    83,    83,    83,
      83,    83,    83,    83,    83,    83,    83,    83,    83,    83,
      83,    83,    83,    83,    83,    84,    84,    84,    85,    85,
      86,    86,    87,    87,    87,    87
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     1,     2,     1,     1,     1,     1,     2,     2,
       1,     1,     1,     1,     3,     1,     6,     6,     1,     0,
       3,     1,     7,     0,     2,     0,     2,     3,     1,     0,
       2,     1,     1,     5,     0,     2,     6,     6,     4,     0,
       2,     0,     2,     1,     2,     1,     1,     1,     1,     1,
       1,     1,     0,     1,     5,     7,     5,     9,     2,     3,
       5,     1,     3,     3,     1,     1,     1,     3,     3,     3,
       3,     3,     3,     2,     3,     3,     3,     3,     3,     3,
       3,     3,     2,     3,     6,     1,     4,     3,     4,     6,
       0,     1,     1,     1,     1,     1
};


#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)
#define YYEMPTY         (-2)
#define YYEOF           0

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                  \
do                                                              \
  if (yychar == YYEMPTY)                                        \
    {                                                           \
      yychar = (Token);                                         \
      yylval = (Value);                                         \
      YYPOPSTACK (yylen);                                       \
      yystate = *yyssp;                                         \
      goto yybackup;                                            \
    }                                                           \
  else                                                          \
    {                                                           \
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;                                                  \
    }                                                           \
while (0)

/* Error token number */
#define YYTERROR        1
#define YYERRCODE       256



/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)

/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


# define YY_SYMBOL_PRINT(Title, Type, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Type, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*----------------------------------------.
| Print this symbol's value on YYOUTPUT.  |
`----------------------------------------*/

static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  FILE *yyo = yyoutput;
  YYUSE (yyo);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# endif
  YYUSE (yytype);
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyoutput, "%s %s (",
             yytype < YYNTOKENS ? "token" : "nterm", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yytype_int16 *yyssp, YYSTYPE *yyvsp, int yyrule)
{
  unsigned long int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       yystos[yyssp[yyi + 1 - yynrhs]],
                       &(yyvsp[(yyi + 1) - (yynrhs)])
                                              );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
static YYSIZE_T
yystrlen (const char *yystr)
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            /* Fall through.  */
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (YY_NULLPTR, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYSIZE_T yysize1 = yysize + yytnamerr (YY_NULLPTR, yytname[yyx]);
                  if (! (yysize <= yysize1
                         && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                    return 2;
                  yysize = yysize1;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    YYSIZE_T yysize1 = yysize + yystrlen (yyformat);
    if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
      return 2;
    yysize = yysize1;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
{
  YYUSE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}




/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       'yyss': related to states.
       'yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        YYSTYPE *yyvs1 = yyvs;
        yytype_int16 *yyss1 = yyss;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * sizeof (*yyssp),
                    &yyvs1, yysize * sizeof (*yyvsp),
                    &yystacksize);

        yyss = yyss1;
        yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yytype_int16 *yyss1 = yyss;
        union yyalloc *yyptr =
          (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
        if (! yyptr)
          goto yyexhaustedlab;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
                  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = yylex ();
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);
  printf("[shift]\n")

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
#line 77 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 01.1]");}
#line 1478 "y.tab.c" /* yacc.c:1646  */
    break;

  case 3:
#line 79 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 01.2]");}
#line 1484 "y.tab.c" /* yacc.c:1646  */
    break;

  case 4:
#line 84 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 02.1]");}
#line 1490 "y.tab.c" /* yacc.c:1646  */
    break;

  case 5:
#line 86 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 02.2]");}
#line 1496 "y.tab.c" /* yacc.c:1646  */
    break;

  case 6:
#line 88 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 02.3]");}
#line 1502 "y.tab.c" /* yacc.c:1646  */
    break;

  case 7:
#line 90 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 02.4]");}
#line 1508 "y.tab.c" /* yacc.c:1646  */
    break;

  case 8:
#line 95 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 03.1]");}
#line 1514 "y.tab.c" /* yacc.c:1646  */
    break;

  case 9:
#line 100 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 04.1]");}
#line 1520 "y.tab.c" /* yacc.c:1646  */
    break;

  case 10:
#line 105 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 05.1]");}
#line 1526 "y.tab.c" /* yacc.c:1646  */
    break;

  case 11:
#line 107 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 05.2]");}
#line 1532 "y.tab.c" /* yacc.c:1646  */
    break;

  case 12:
#line 109 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 05.3]");}
#line 1538 "y.tab.c" /* yacc.c:1646  */
    break;

  case 13:
#line 111 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 05.4]");}
#line 1544 "y.tab.c" /* yacc.c:1646  */
    break;

  case 14:
#line 113 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 05.5]");}
#line 1550 "y.tab.c" /* yacc.c:1646  */
    break;

  case 15:
#line 115 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 05.6]");}
#line 1556 "y.tab.c" /* yacc.c:1646  */
    break;

  case 16:
#line 120 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 06.1]");}
#line 1562 "y.tab.c" /* yacc.c:1646  */
    break;

  case 17:
#line 122 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 06.2]");}
#line 1568 "y.tab.c" /* yacc.c:1646  */
    break;

  case 18:
#line 127 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 07.1]");}
#line 1574 "y.tab.c" /* yacc.c:1646  */
    break;

  case 19:
#line 129 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 07.2]");}
#line 1580 "y.tab.c" /* yacc.c:1646  */
    break;

  case 20:
#line 135 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 07.1.1]");}
#line 1586 "y.tab.c" /* yacc.c:1646  */
    break;

  case 21:
#line 137 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 07.1.2]");}
#line 1592 "y.tab.c" /* yacc.c:1646  */
    break;

  case 22:
#line 142 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1]");}
#line 1598 "y.tab.c" /* yacc.c:1646  */
    break;

  case 23:
#line 148 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1.1.1]");}
#line 1604 "y.tab.c" /* yacc.c:1646  */
    break;

  case 24:
#line 150 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1.1.2]");}
#line 1610 "y.tab.c" /* yacc.c:1646  */
    break;

  case 25:
#line 156 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1.2.1]");}
#line 1616 "y.tab.c" /* yacc.c:1646  */
    break;

  case 26:
#line 158 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1.2.2]");}
#line 1622 "y.tab.c" /* yacc.c:1646  */
    break;

  case 27:
#line 164 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1.2.2.1]");}
#line 1628 "y.tab.c" /* yacc.c:1646  */
    break;

  case 28:
#line 166 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1.2.2.2]");}
#line 1634 "y.tab.c" /* yacc.c:1646  */
    break;

  case 29:
#line 172 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1.3.1]");}
#line 1640 "y.tab.c" /* yacc.c:1646  */
    break;

  case 30:
#line 174 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 08.1.3.2]");}
#line 1646 "y.tab.c" /* yacc.c:1646  */
    break;

  case 31:
#line 179 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 09.1]");}
#line 1652 "y.tab.c" /* yacc.c:1646  */
    break;

  case 32:
#line 181 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 09.2]");}
#line 1658 "y.tab.c" /* yacc.c:1646  */
    break;

  case 33:
#line 186 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 10.1]");}
#line 1664 "y.tab.c" /* yacc.c:1646  */
    break;

  case 34:
#line 192 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 10.1.1]");}
#line 1670 "y.tab.c" /* yacc.c:1646  */
    break;

  case 35:
#line 194 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 10.1.2]");}
#line 1676 "y.tab.c" /* yacc.c:1646  */
    break;

  case 36:
#line 202 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 11.2]");}
#line 1682 "y.tab.c" /* yacc.c:1646  */
    break;

  case 37:
#line 204 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 11.3]");}
#line 1688 "y.tab.c" /* yacc.c:1646  */
    break;

  case 38:
#line 209 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 12.1]");}
#line 1694 "y.tab.c" /* yacc.c:1646  */
    break;

  case 39:
#line 215 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 12.1.1.1]");}
#line 1700 "y.tab.c" /* yacc.c:1646  */
    break;

  case 40:
#line 217 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 12.1.1.2]");}
#line 1706 "y.tab.c" /* yacc.c:1646  */
    break;

  case 41:
#line 223 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 12.1.2.1]");}
#line 1712 "y.tab.c" /* yacc.c:1646  */
    break;

  case 42:
#line 225 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 12.1.2.2]");}
#line 1718 "y.tab.c" /* yacc.c:1646  */
    break;

  case 43:
#line 230 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.0]");}
#line 1724 "y.tab.c" /* yacc.c:1646  */
    break;

  case 44:
#line 232 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.1]");}
#line 1730 "y.tab.c" /* yacc.c:1646  */
    break;

  case 45:
#line 234 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.2]");}
#line 1736 "y.tab.c" /* yacc.c:1646  */
    break;

  case 46:
#line 236 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.3]");}
#line 1742 "y.tab.c" /* yacc.c:1646  */
    break;

  case 47:
#line 238 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.4]");}
#line 1748 "y.tab.c" /* yacc.c:1646  */
    break;

  case 48:
#line 240 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.5]");}
#line 1754 "y.tab.c" /* yacc.c:1646  */
    break;

  case 49:
#line 242 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.6]");}
#line 1760 "y.tab.c" /* yacc.c:1646  */
    break;

  case 50:
#line 244 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.7]");}
#line 1766 "y.tab.c" /* yacc.c:1646  */
    break;

  case 51:
#line 246 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 13.8]");}
#line 1772 "y.tab.c" /* yacc.c:1646  */
    break;

  case 52:
#line 254 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 25.1]");}
#line 1778 "y.tab.c" /* yacc.c:1646  */
    break;

  case 53:
#line 256 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 25.2]");}
#line 1784 "y.tab.c" /* yacc.c:1646  */
    break;

  case 54:
#line 261 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 14.1]");}
#line 1790 "y.tab.c" /* yacc.c:1646  */
    break;

  case 55:
#line 263 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 14.2]");}
#line 1796 "y.tab.c" /* yacc.c:1646  */
    break;

  case 56:
#line 268 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 15.1]");}
#line 1802 "y.tab.c" /* yacc.c:1646  */
    break;

  case 57:
#line 273 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 16.1]");}
#line 1808 "y.tab.c" /* yacc.c:1646  */
    break;

  case 58:
#line 278 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 17.1]");}
#line 1814 "y.tab.c" /* yacc.c:1646  */
    break;

  case 59:
#line 283 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 18.1]");}
#line 1820 "y.tab.c" /* yacc.c:1646  */
    break;

  case 60:
#line 288 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 19.1]");}
#line 1826 "y.tab.c" /* yacc.c:1646  */
    break;

  case 61:
#line 295 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 26.1]");}
#line 1832 "y.tab.c" /* yacc.c:1646  */
    break;

  case 62:
#line 297 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 26.2]");}
#line 1838 "y.tab.c" /* yacc.c:1646  */
    break;

  case 63:
#line 304 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.01]");}
#line 1844 "y.tab.c" /* yacc.c:1646  */
    break;

  case 64:
#line 307 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.02]");}
#line 1850 "y.tab.c" /* yacc.c:1646  */
    break;

  case 65:
#line 310 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.03]");}
#line 1856 "y.tab.c" /* yacc.c:1646  */
    break;

  case 66:
#line 313 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.04]");}
#line 1862 "y.tab.c" /* yacc.c:1646  */
    break;

  case 67:
#line 316 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.05]");}
#line 1868 "y.tab.c" /* yacc.c:1646  */
    break;

  case 68:
#line 319 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.06]");}
#line 1874 "y.tab.c" /* yacc.c:1646  */
    break;

  case 69:
#line 321 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.07]");}
#line 1880 "y.tab.c" /* yacc.c:1646  */
    break;

  case 70:
#line 323 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.08]");}
#line 1886 "y.tab.c" /* yacc.c:1646  */
    break;

  case 71:
#line 325 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.09]");}
#line 1892 "y.tab.c" /* yacc.c:1646  */
    break;

  case 72:
#line 327 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.10]");}
#line 1898 "y.tab.c" /* yacc.c:1646  */
    break;

  case 73:
#line 330 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.11]");}
#line 1904 "y.tab.c" /* yacc.c:1646  */
    break;

  case 74:
#line 333 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.12]");}
#line 1910 "y.tab.c" /* yacc.c:1646  */
    break;

  case 75:
#line 335 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.13]");}
#line 1916 "y.tab.c" /* yacc.c:1646  */
    break;

  case 76:
#line 337 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.14]");}
#line 1922 "y.tab.c" /* yacc.c:1646  */
    break;

  case 77:
#line 339 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.15]");}
#line 1928 "y.tab.c" /* yacc.c:1646  */
    break;

  case 78:
#line 341 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.16]");}
#line 1934 "y.tab.c" /* yacc.c:1646  */
    break;

  case 79:
#line 343 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.17]");}
#line 1940 "y.tab.c" /* yacc.c:1646  */
    break;

  case 80:
#line 346 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.18]");}
#line 1946 "y.tab.c" /* yacc.c:1646  */
    break;

  case 81:
#line 348 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.19]");}
#line 1952 "y.tab.c" /* yacc.c:1646  */
    break;

  case 82:
#line 350 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.20]");}
#line 1958 "y.tab.c" /* yacc.c:1646  */
    break;

  case 83:
#line 353 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.21]");}
#line 1964 "y.tab.c" /* yacc.c:1646  */
    break;

  case 84:
#line 355 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 20.22]");}
#line 1970 "y.tab.c" /* yacc.c:1646  */
    break;

  case 85:
#line 360 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 21.1]");}
#line 1976 "y.tab.c" /* yacc.c:1646  */
    break;

  case 86:
#line 362 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 21.2]");}
#line 1982 "y.tab.c" /* yacc.c:1646  */
    break;

  case 87:
#line 364 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 21.3]");}
#line 1988 "y.tab.c" /* yacc.c:1646  */
    break;

  case 88:
#line 369 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 22.1]");}
#line 1994 "y.tab.c" /* yacc.c:1646  */
    break;

  case 89:
#line 371 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 22.2]");}
#line 2000 "y.tab.c" /* yacc.c:1646  */
    break;

  case 90:
#line 376 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 23.1]");}
#line 2006 "y.tab.c" /* yacc.c:1646  */
    break;

  case 91:
#line 378 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 23.2]");}
#line 2012 "y.tab.c" /* yacc.c:1646  */
    break;

  case 92:
#line 383 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 24.1]");}
#line 2018 "y.tab.c" /* yacc.c:1646  */
    break;

  case 93:
#line 385 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 24.2]");}
#line 2024 "y.tab.c" /* yacc.c:1646  */
    break;

  case 94:
#line 387 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 24.3]");}
#line 2030 "y.tab.c" /* yacc.c:1646  */
    break;

  case 95:
#line 389 "parser.y" /* yacc.c:1646  */
    {printf("[reduce 24.4]");}
#line 2036 "y.tab.c" /* yacc.c:1646  */
    break;


#line 2040 "y.tab.c" /* yacc.c:1646  */
      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYTERROR;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  return yyresult;
}
#line 392 "parser.y" /* yacc.c:1906  */

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
