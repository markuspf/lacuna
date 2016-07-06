module GAP.Syntax

data Name = MkName String

mutual
  data Var = MkVar Name
  
  -- Make literals their own type?
  -- Its not quite clear what a "literal" is though

  -- Expressions have values
  data Expr =
      MkInt Integer
      | MkBool Bool
      | MkChar Char
      | MkPerm (List Integer)
      | MkString String
      | MkFloat Double
      | MkRec (List )
      | MkList (List Expr)
      | MkFuncCall Name (List Expr)
      | MkFunc Name (List Name) (List Name) Block
      | MkRefLVar Name
      | MkRefHVar Name
      | MkRefGVar Name

  -- Statements
  data Stat =
    MkAss Var Expr           -- assignment var := expr
    | MkAssGVar
    | MkAssHVar
    | MkAssLVar
    | MkAssList
    | MkAssPosObj
    | MkAssComObj
    | MkIf Expr Block Block  -- if ... then ... else  (should this be generalised to if then elif then?)
    | MkFor Expr Block       -- for x do block od;
    | MkRepeat Expr Block    -- repeat block until expr;
    | MkWhile Expr Block
    | MkReturn (Maybe Expr)
    | MkEmpty 
  data Block = List Stat
