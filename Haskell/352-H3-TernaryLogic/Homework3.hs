import Data.List

{-
CS352 Spring 2017 Student Name: Rachel Chiang
-}

{-
This homework consists of the implementation of operators and expression trees
in ternary logic in Haskell

The rest of this file contains the skeleton of the solution. Some data definitions
and some of the functions are already coded for you. You will have to add code in all the
places marked TODO. At the end of each section are a few functions whose name
start with the word test. These functions will test your implementation.
Feel free to execute them, they take no parameter and return True if the test pass.

Add your implementation at the places indicated by the words TODO. You can also add
helper functions if you need to, but DO NOT make any other changes to the file.
In particular do not eliminate any of the comments and most importantly DO NOT make any change the code of the test functions.
Points will be taken away for tampering with those functions.

Don't forget to define the type for all the functions you code!!!
-}

{-
Ternary logic:
We’ll use the constructors T, F and M for True, False and Maybe

The basic operation of ternary logic are not, and, or, equivalence and implication with the truth table below.
To avoid conflict with the Bool implementation of Haskell we'll use the following names:
n't for not
&&& for and
||| to or
<=> for equivalence
==> for implication

Truth Table:
x    y    n't x    x <=> y     x ||| y     x &&& Y     x ==> Y
T    F     F         F           T           F           F
T    T               T           T           T           T
T    M               M           T           M           M
F    F     T         T           F           F           T
F    T               F           T           F           T
F    M               M           M           F           T
M    F     M         M           M           F           M
M    T               M           T           M           T
M    M               T           M           M           T
-}

-- P A R T 1   T E R N A R Y   L O G I C

-- We use Ternary for the datatype, with the constructor T F and M
data Ternary = T | F | M
    deriving (Eq, Show, Ord)

--TODO: Implement the n't (not) function
n't :: Ternary -> Ternary
n't T = F
n't F = T
n't M = M

-- The equivalence operator has the lowest precedence infix:
-- infix 1 <=>
-- TODO Implement the infix operator <=> (equivalence)
(<=>) :: Ternary -> Ternary -> Ternary
(<=>) x y | x == y = T
(<=>) F T = F
(<=>) T F = F
(<=>) _ _ = M

-- The implication operator has the same precedence as equivalence
-- infix 1 ==>
-- TODO Implement the infix operator ==> (implication)
(==>) :: Ternary -> Ternary -> Ternary
(==>) T F = F
(==>) T M = M
(==>) M F = M
(==>) _ _ = T

-- The Or operator is just above equivalence in precedence
-- infix 2 |||
-- TODO Implement the infix operator ||| (Or)
(|||) :: Ternary -> Ternary -> Ternary
(|||) x y | x == y = x
(|||) T _ = T
(|||) _ T = T
(|||) F y = y
(|||) x F = x

-- The And operator is just above Or in precedence
-- infix 3 &&&
-- TODO Implement the infix operator &&& (And)
(&&&) :: Ternary -> Ternary -> Ternary
(&&&) x y | x == y = x
(&&&) _ F = F
(&&&) F _ = F
(&&&) M T = M
(&&&) T M = M

-- This completes part 1. You can use the following functions to test your implementation
testNot :: Bool
testNot = (n't T == F) && (n't F == T) && (n't M == M)


testEquiv :: Bool
testEquiv = ((T <=> F) == F)
         && ((T <=> T) == T)
         && ((T <=> M) == M)
         && ((F <=> F) == T)
         && ((F <=> T) == F)
         && ((F <=> M) == M)
         && ((M <=> F) == M)
         && ((M <=> T) == M)
         && ((M <=> M) == T)

testImply :: Bool
testImply = ((T ==> F) == F)
         && ((T ==> T) == T)
         && ((T ==> M) == M)
         && ((F ==> F) == T)
         && ((F ==> T) == T)
         && ((F ==> M) == T)
         && ((M ==> F) == M)
         && ((M ==> T) == T)
         && ((M ==> M) == T)

testOr :: Bool
testOr = ((T ||| F) == T)
      && ((T ||| T) == T)
      && ((T ||| M) == T)
      && ((F ||| F) == F)
      && ((F ||| T) == T)
      && ((F ||| M) == M)
      && ((M ||| F) == M)
      && ((M ||| T) == T)
      && ((M ||| M) == M)

testAnd :: Bool
testAnd = ((T &&& F) == F)
        && ((T &&& T) == T)
        && ((T &&& M) == M)
        && ((F &&& F) == F)
        && ((F &&& T) == F)
        && ((F &&& M) == F)
        && ((M &&& F) == F)
        && ((M &&& T) == M)
        && ((M &&& M) == M)

testPart1 = testNot && testEquiv && testImply && testOr && testAnd


{- P A R T 2: Create an evaluator for TExpTree's
--TODO Define the type TExpTree, using the constructors L, N, A, O, E, I
L for Ternary literals (i.e. T, F or M)
N for a node representing the prefix not
A for a node representing the infix And
O for a node representing the infix Or
E for a node representing the infix Equivalence
I for a node representing the infix Implication
It will be convenient to have the new type derive from the classes Show and Eq
-}

data TExpTree a = L a
                | A (TExpTree a) (TExpTree a)
                | O (TExpTree a) (TExpTree a)
                | E (TExpTree a) (TExpTree a)
                | I (TExpTree a) (TExpTree a)
                | N (TExpTree a)
                deriving (Eq, Show, Ord)

{- TODO create a function evalT that takes an expression tree and returns the value
of the expression.
-}
evalT :: TExpTree Ternary -> Ternary
evalT (L x) = x
evalT (A x y) = (&&&) (evalT x) (evalT y)
evalT (O x y) = (|||) (evalT x) (evalT y)
evalT (E x y) = (<=>) (evalT x) (evalT y)
evalT (I x y) = (==>) (evalT x) (evalT y)
evalT (N x) = (n't) (evalT x)

-- This completes part 2. You can use the following functions to test your implementation
testEvalT :: Bool
testEvalT = evalT (O (L T) (O (N (L T))(E (L T) (L M)))) == T
          && evalT (O (L F) (O (N (L F)) (E (L F) (L M)))) == T
          && evalT (O (L M) (O (N (L M)) (E (L M) (L M)))) == T
          && evalT (O (L M) (N (L M))) == M
          && evalT (A (L F) (L M)) == F
          && evalT (O (L T) (O (N (L F)) (E (L M) (I (L M)(L T))))) == T
