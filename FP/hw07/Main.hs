import qualified Data.Map as M

import Expr
import Parsing

type Store = M.Map String Value

type Error = String

type Evaluated = Either [Error] Value



-- --------------------------------------------------------------------------------
-- doEvalBinOp
-- --------------------------------------------------------------------------------
doEvalBinOp :: BinOp -> Value -> Value -> Evaluated

doEvalBinOp Plus    (I i1) (I i2) = Right $ I $ i1 + i2
doEvalBinOp Minus   (I i1) (I i2) = Right $ I $ i1 - i2
doEvalBinOp Mul     (I i1) (I i2) = Right $ I $ i1 * i2

doEvalBinOp Less    (I i1) (I i2) = Right $ B $ i1 < i2
doEvalBinOp Greater (I i1) (I i2) = Right $ B $ i1 > i2
doEvalBinOp Equals  (I i1) (I i2) = Right $ B $ i1 == i2

doEvalBinOp And     (B i1) (B i2) = Right $ B $ i1 && i2
doEvalBinOp Or      (B i1) (B i2) = Right $ B $ i1 || i2

doEvalBinOp _ _ _ = Left ["Type mismatch"]




-- --------------------------------------------------------------------------------
-- evalBinOp
-- --------------------------------------------------------------------------------

evalBinOp :: BinOp -> Evaluated -> Evaluated -> Evaluated

evalBinOp _ (Left e1) (Left e2) = Left (e1 ++ e2)
evalBinOp _ (Left e) _ = Left e
evalBinOp _ _ (Left e) = Left e
evalBinOp op (Right v1) (Right v2) = doevalBinOp op v1 v2

-- --------------------------------------------------------------------------------
-- evalUnOp
-- --------------------------------------------------------------------------------

evalUnOp :: BinOp -> Evaluated -> Evaluated



-- --------------------------------------------------------------------------------
-- evalExpr
-- --------------------------------------------------------------------------------

evalExpr :: Store -> Expr -> Evaluated
evalExpr _ (Const v)        = Right v
evalExpr s (BinOp op e1 e2) = evalBinOp op (evalExpr s e1) (evalExpr s e2)
evalExpr s (UnOp op e)      = evalUnOp op (evalExpr s e)

evalStat :: Store -> Stat -> ([Error], Store)
evalStat = undefined

evalProg :: Store -> Prog -> ([Error], Store)
evalProg = undefined

inp1 = "!x || (y < 3 && z == y) && 5 < y + 7 * (z + y * 3)"
inp2 = "-5 + -3 * 2 - 7"
inp3 = "r = 1;\n"
    ++ "while (n > 0) {\n"
    ++ "\tr = r * n;\n"
    ++ "\tn = n - 1;\n"
    ++ "}"
inp4 = "if (x > 0) y = 1; else y = 2;"
inp5 = "if (x > 0) y = 1;"

main = do
    print (read inp3 :: Prog)
    print (read inp4 :: Prog)
    print (read inp5 :: Prog)
    print . evalExpr (M.fromList[]) . read $ "(117 > 100) + 7"
    
    --print . evalExpr (M.fromList [("x",B True),("t",I 5),("f",I 5)]) . read $ inp1
    --print . evalExpr (M.fromList [("x",B True),("y",I 5),("z",I 5)]) . read $ inp1
    --print . evalExpr (M.fromList [("x",B True),("y",I 2),("z",I 2)]) . read $ inp1
    --print . evalExpr M.empty . read $ inp2
    --print . evalProg (M.fromList [("n",I 5)]) . read $ inp3
