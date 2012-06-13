import qualified Data.Map as M

import Expr
import Parsing

type Store = M.Map String Value

type Error = String

evalExpr :: Store -> Expr -> Either [Error] Value
evalExpr = undefined

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
    print . evalExpr (M.fromList [("x",B True),("t",I 5),("f",I 5)]) . read $ inp1
    print . evalExpr (M.fromList [("x",B True),("y",I 5),("z",I 5)]) . read $ inp1
    print . evalExpr (M.fromList [("x",B True),("y",I 2),("z",I 2)]) . read $ inp1
    print . evalExpr M.empty . read $ inp2
    print . evalProg (M.fromList [("n",I 5)]) . read $ inp3
