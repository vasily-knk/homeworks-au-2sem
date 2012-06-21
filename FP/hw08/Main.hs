module Main
    ( main
    , boolG
    , maybeG
    , listG
    , listG'
    , treeG
    ) where

import Combinators
import Control.Monad
import Test.HUnit

-- language boolG "True" == True
-- language boolG "False" == True
-- language boolG "qwerty" == False
boolG :: Grammar
boolG = undefined

-- language (maybeG natural) "Nothing" == True
-- language (maybeG natural) "Just 123" == True
-- language (maybeG natural) "Just123" == False
-- language (maybeG natural) "Just x" == False
maybeG :: Grammar -> Grammar
maybeG = undefined

-- language (listG integer) "[1,-23,25,347]" == True
-- language (listG integer) "[1 ,  -23,  25   ,347]" == False
listG :: Grammar -> Grammar
listG = undefined

-- language (listG' integer) "[1,-23,25,347]" == True
-- language (listG' integer) "[1 ,  -23,  25   ,347]" == True
listG' :: Grammar -> Grammar
listG' = undefined

-- language (treeG integer integer) "100" == True
-- language (treeG integer integer) "<1{2}3>" == True
-- language (treeG integer integer) "<1{2}<3{4}5>>>" == False
-- language (treeG integer integer) "<1{2}<3{4}5>>" == True
-- language (treeG integer integer) "<1{2}<3{4}5>" == False
treeG :: Grammar -> Grammar -> Grammar
treeG = undefined

main = void $ runTestTT $ test
    $    label "epsilon"
    [ language epsilon "qwerty" ~?= False
    , language epsilon "" ~?= True
    ] ++ label "empty"
    [ language empty "qwerty" ~?= False
    , language empty "" ~?= False
    ] ++ label "satisfy"
    [ language (satisfy (/= 'x')) "qwerty" ~?= False
    , language (satisfy (/= 'x')) "q" ~?= True
    , language (satisfy (/= 'x')) "x" ~?= False
    , language (satisfy (/= 'x')) "" ~?= False
    ] ++ label ".>"
    [ language (satisfy (== 'q') .> satisfy (== 'w')) "qw" ~?= True
    , language (satisfy (== 'q') .> satisfy (== 'x')) "qw" ~?= False
    , language (satisfy (== 'x') .> satisfy (== 'w')) "qw" ~?= False
    , language (satisfy (== 'q') .> satisfy (== 'w')) "qwerty" ~?= False
    , language (epsilon .> satisfy (== 'x')) "x" ~?= True
    , language (satisfy (== 'x') .> epsilon) "x" ~?= True
    , language (empty .> satisfy (== 'x')) "x" ~?= False
    , language (satisfy (== 'x') .> empty) "x" ~?= False
    ] ++ label "<."
    [ language (satisfy (== 'q') <. satisfy (== 'w')) "wq" ~?= True
    , language (satisfy (== 'q') <. satisfy (== 'x')) "wq" ~?= False
    , language (satisfy (== 'x') <. satisfy (== 'w')) "wq" ~?= False
    , language (satisfy (== 'q') <. satisfy (== 'w')) "wqerty" ~?= False
    , language (epsilon <. satisfy (== 'x')) "x" ~?= True
    , language (satisfy (== 'x') <. epsilon) "x" ~?= True
    , language (empty <. satisfy (== 'x')) "x" ~?= False
    , language (satisfy (== 'x') <. empty) "x" ~?= False
    ] ++ label "|>"
    [ language (satisfy (== 'q') |> satisfy (== 'x')) "q" ~?= True
    , language (satisfy (== 'x') |> satisfy (== 'q')) "q" ~?= True
    , language (satisfy (== 'x') |> satisfy (== 'y')) "q" ~?= False
    , language (satisfy (== 'x') |> satisfy (== 'q')) "xq" ~?= False
    , language (satisfy (== 'x') |> satisfy (== 'q')) "qx" ~?= False
    ] ++ label "<|"
    [ language (satisfy (== 'q') <| satisfy (== 'x')) "q" ~?= True
    , language (satisfy (== 'x') <| satisfy (== 'q')) "q" ~?= True
    , language (satisfy (== 'x') <| satisfy (== 'y')) "q" ~?= False
    , language (satisfy (== 'x') <| satisfy (== 'q')) "xq" ~?= False
    , language (satisfy (== 'x') <| satisfy (== 'q')) "qx" ~?= False
    ] ++ label "char"
    [ language (char 'q') "qwerty" ~?= False
    , language (char 'x') "qwerty" ~?= False
    , language (char 'x') "x" ~?= True
    ] ++ label "anyChar"
    [ language anyChar "x" ~?= True
    , language anyChar "qwerty" ~?= False
    , language anyChar "" ~?= False
    ] ++ label "anything"
    [ language anything "x" ~?= True
    , language anything "qwerty" ~?= True
    , language anything "" ~?= True
    ] ++ label "digit"
    [ language digit "q" ~?= False
    , language digit "1" ~?= True
    , language digit "" ~?= False
    ] ++ label "string"
    [ language (string "qwerty") "qwerty" ~?= True
    , language (string "qwerty") "qwertyuiop" ~?= False
    , language (string "qwerty") "qwerry" ~?= False
    , language (string "qwerty") "qwert" ~?= False
    ] ++ label "many"
    [ language (many (char 'q')) "qqqwerty" ~?= False
    , language (many (char 'q')) "qqq" ~?= True
    , language (many (char 'q')) "q" ~?= True
    , language (many (char 'q')) "" ~?= True
    ] ++ label "many1"
    [ language (many1 (char 'q')) "qqqwerty" ~?= False
    , language (many1 (char 'q')) "qqq" ~?= True
    , language (many1 (char 'q')) "q" ~?= True
    , language (many1 (char 'q')) "" ~?= False
    ] ++ label "natural"
    [ language natural "qwerty" ~?= False
    , language natural "123qwerty" ~?= False
    , language natural "123" ~?= True
    , language natural "-123" ~?= False
    , language natural "" ~?= False
    ] ++ label "integer"
    [ language integer "qwerty" ~?= False
    , language integer "123qwerty" ~?= False
    , language integer "123" ~?= True
    , language integer "-123" ~?= True
    , language integer "" ~?= False
    ] ++ label "spaces"
    [ language spaces "qwerty" ~?= False
    , language spaces "    qwerty" ~?= False
    , language spaces "    " ~?= True
    , language spaces "" ~?= True
    ] ++ label "try"
    [ language (try natural) "123qwerty" ~?= False
    , language (try natural) "qwerty" ~?= False
    , language (try natural) "123" ~?= True
    , language (try natural) "" ~?= True
    ] ++ label "endBy"
    [ language (natural `endBy` char ';') "1;2;3;456;xyz;" ~?= False
    , language (natural `endBy` char ';') "1;2;3;456;" ~?= True
    , language (natural `endBy` char ';') "1;2;3;456" ~?= False
    , language (natural `endBy` spaces) "12 25   300 " ~?= True
    , language (natural `endBy` spaces) "12 25   300" ~?= True
    , language (natural `endBy` spaces) "qwerty" ~?= False
    , language (natural `endBy` spaces) "" ~?= True
    ] ++ label "endBy1"
    [ language (natural `endBy1` char ';') "1;2;3;456;xyz;" ~?= False
    , language (natural `endBy1` char ';') "1;2;3;456;" ~?= True
    , language (natural `endBy1` char ';') "1;2;3;456" ~?= False
    , language (natural `endBy1` spaces) "12 25   300 " ~?= True
    , language (natural `endBy1` spaces) "12 25   300" ~?= True
    , language (natural `endBy1` spaces) "qwerty" ~?= False
    , language (natural `endBy1` spaces) "" ~?= False
    ] ++ label "sepBy"
    [ language (natural `sepBy` char ';') "1;2;3;456;xyz;" ~?= False
    , language (natural `sepBy` char ';') "1;2;3;456;" ~?= False
    , language (natural `sepBy` char ';') "1;2;3;456" ~?= True
    , language (natural `sepBy` spaces) "12 25   300 " ~?= False
    , language (natural `sepBy` spaces) "12 25   300" ~?= True
    , language (natural `sepBy` spaces) "qwerty" ~?= False
    , language (natural `sepBy` spaces) "" ~?= True
    ] ++ label "sepBy1"
    [ language (natural `sepBy1` char ';') "1;2;3;456;xyz;" ~?= False
    , language (natural `sepBy1` char ';') "1;2;3;456;" ~?= False
    , language (natural `sepBy1` char ';') "1;2;3;456" ~?= True
    , language (natural `sepBy1` spaces) "12 25   300 " ~?= False
    , language (natural `sepBy1` spaces) "12 25   300" ~?= True
    , language (natural `sepBy1` spaces) "qwerty" ~?= False
    , language (natural `sepBy1` spaces) "" ~?= False
    ] ++ label "between"
    [ language (between (char 'a') (char 'b') (char 'c')) "abc" ~?= False
    , language (between (char 'a') (char 'b') (char 'c')) "acb" ~?= True
    ] ++ label "brackets"
    [ language (brackets (string "qwerty")) "[qwerty]" ~?= True
    , language (brackets (string "qwerty")) "[qwertyu]" ~?= False
    ] ++ label "parens"
    [ language (parens spaces) "(   )" ~?= True
    , language (parens spaces) "(q)" ~?= False
    ] ++ label "braces"
    [ language (braces natural) "{123}" ~?= True
    , language (braces natural) "{}" ~?= False
    ] ++ label "angles"
    [ language (angles digit) "<1>" ~?= True
    , language (angles digit) "<1" ~?= False
    ] ++ label "boolG"
    [ language boolG "True" ~?= True
    , language boolG "False" ~?= True
    , language boolG "qwerty" ~?= False
    ] ++ label "maybeG"
    [ language (maybeG natural) "Nothing" ~?= True
    , language (maybeG natural) "Just 123" ~?= True
    , language (maybeG natural) "Just123" ~?= False
    , language (maybeG natural) "Just x" ~?= False
    ] ++ label "listG"
    [ language (listG integer) "[1,-23,25,347]" ~?= True
    , language (listG integer) "[1 ,  -23,  25   ,347]" ~?= False
    ] ++ label "listG'"
    [ language (listG' integer) "[1,-23,25,347]" ~?= True
    , language (listG' integer) "[1 ,  -23,  25   ,347]" ~?= True
    ] ++ label "treeG"
    [ language (treeG integer integer) "100" ~?= True
    , language (treeG integer integer) "<1{2}3>" ~?= True
    , language (treeG integer integer) "<1{2}<3{4}5>>>" ~?= False
    , language (treeG integer integer) "<1{2}<3{4}5>>" ~?= True
    , language (treeG integer integer) "<1{2}<3{4}5>" ~?= False
    ]
  where
    label :: String -> [Test] -> [Test]
    label l = map (\(i,t) -> TestLabel (l ++ " [" ++ show i ++ "]") t) . zip [1..]
