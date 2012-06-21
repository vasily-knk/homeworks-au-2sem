import qualified Data.Map as M

import Expr
import Parsing

type Store = M.Map String Value

type Error = String

type Evaluated = Either [Error] Value

errorTypeMisMatch = Left ["Type mismatch"]
errorUnknownVar name = Left ["Unknown var " ++ name]

-- --------------------------------------------------------------------------------
-- doEvalBinOp
-- --------------------------------------------\------------------------------------
doEvalBinOp :: BinOp -> Value -> Value -> Evaluated

doEvalBinOp Plus    (I i1) (I i2) = Right $ I $ i1 + i2
doEvalBinOp Minus   (I i1) (I i2) = Right $ I $ i1 - i2
doEvalBinOp Mul     (I i1) (I i2) = Right $ I $ i1 * i2

doEvalBinOp Less    (I i1) (I i2) = Right $ B $ i1 < i2
doEvalBinOp Greater (I i1) (I i2) = Right $ B $ i1 > i2
doEvalBinOp Equals  (I i1) (I i2) = Right $ B $ i1 == i2

doEvalBinOp And     (B i1) (B i2) = Right $ B $ i1 && i2
doEvalBinOp Or      (B i1) (B i2) = Right $ B $ i1 || i2

doEvalBinOp _ _ _ = errorTypeMisMatch



-- --------------------------------------------------------------------------------
-- evalBinOp
-- --------------------------------------------------------------------------------

evalBinOp :: BinOp -> Evaluated -> Evaluated -> Evaluated

evalBinOp _ (Left e1) (Left e2) = Left (e1 ++ e2)
evalBinOp _ (Left e) _ = Left e
evalBinOp _ _ (Left e) = Left e
evalBinOp op (Right v1) (Right v2) = doEvalBinOp op v1 v2

-- --------------------------------------------------------------------------------
-- doEvalUnOp
-- --------------------------------------------------------------------------------

doEvalUnOp :: UnOp -> Value -> Evaluated

doEvalUnOp Not (B b) = Right $ B $ not b
doEvalUnOp Neg (I i) = Right $ I (-i)

doEvalUnOp _ _ = errorTypeMisMatch

-- --------------------------------------------------------------------------------
-- evalUnOp
-- --------------------------------------------------------------------------------

evalUnOp :: UnOp -> Evaluated -> Evaluated

evalUnOp _ (Left err) = Left err
evalUnOp op (Right v) = doEvalUnOp op v


-- --------------------------------------------------------------------------------
-- evalVar
-- --------------------------------------------------------------------------------

--evalMaybeVar :: Maybe Value -> Evaluated
--evalMaybeVar Nothing  = errorUnknownVar
--evalMaybeVar (Just v) = Right v


-- --------------------------------------------------------------------------------
-- evalExpr
-- --------------------------------------------------------------------------------

evalExpr :: Store -> Expr -> Evaluated
evalExpr _ (Const c)        = Right c
evalExpr s (BinOp op e1 e2) = evalBinOp op (evalExpr s e1) (evalExpr s e2)
evalExpr s (UnOp op e)      = evalUnOp op (evalExpr s e)
evalExpr s (Var var )       = case (M.lookup var s) of 
                                Nothing -> errorUnknownVar var
                                Just v -> Right v

-- --------------------------------------------------------------------------------
-- evalStat
-- --------------------------------------------------------------------------------

doEvalIf :: Store -> Evaluated -> Stat -> Stat -> ([Error], Store)
doEvalIf store (Left err) _ _           = (err, store)
doEvalIf store (Right (B True)) stat _  = evalStat store stat
doEvalIf store (Right (B False)) _ stat = evalStat store stat
doEvalIf store _ _ _ = (["Type mismatch"], store)

doEvalAssign :: Store -> String -> Evaluated -> ([Error], Store)
doEvalAssign store _ (Left err) = (err, store)
doEvalAssign store name (Right v) = ([], M.insert name v store)

doEvalComp :: Store -> [Stat] -> ([Error], Store)
doEvalComp store [] = ([], store)
doEvalComp store (x:xs) = if null $ fst $ res then (doEvalComp (snd $ res) xs) else (fst res, store) where 
    res = evalStat store x
    
--doEvalWhileInner :: Store -> Expr -> 
    
doEvalWhile :: Store -> Expr -> Stat -> ([Error], Store)
doEvalWhile store expr stat = case (evalExpr store expr) of
    (Left err)          -> (err, store)
    (Right (B False))   -> ([], store)
    (Right (B True))    -> if null $ fst $ res then
                                doEvalWhile (snd $ res) expr stat else
                                (fst $ res, store)  where
                                    res = evalStat store stat;
    (Right _)           -> (["Type mismatch"], store) 
        

--doEvalWhile store (Left err) _ = (err, store)
--doEvalWhile store (Right (B False)) _ = ([], store)
--doEvalWhile store expr stat = if null $ fst $ res then (doEvalWhile 



evalStat :: Store -> Stat -> ([Error], Store)
evalStat store (If cond s1 s2) = doEvalIf store (evalExpr store cond) s1 s2
evalStat store (Assign name expr) = doEvalAssign store name (evalExpr store expr)
evalStat store (Comp a) = doEvalComp store a
evalStat store (While expr stat) = doEvalWhile store expr stat






evalProg :: Store -> Prog -> ([Error], Store)
evalProg store (Prog lst) = doEvalComp store lst

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
    
    print . evalExpr (M.fromList [("x",B True),("t",I 5),("f",I 5)]) . read $ inp1
    print . evalExpr (M.fromList [("x",B True),("y",I 5),("z",I 5)]) . read $ inp1
    print . evalExpr (M.fromList [("x",B True),("y",I 2),("z",I 2)]) . read $ inp1
    print . evalExpr M.empty . read $ inp2
    
    print . evalStat (M.fromList [("x", I (-1))]) . read $ inp5
    print . evalProg (M.fromList [("n",I 5)]) . read $ inp3
