module Grammar
    ( Grammar
    , language
    , satisfy
    , epsilon
    , empty
    , (.>)
    , (<.)
    , (<|)
    , (|>)
    ) where

import Control.Monad

newtype Grammar = ...

-- language g возвращает язык, описанный грамматикой g.
language :: Grammar -> String -> Bool
language = undefined

-- Язык, состоящий из одного пустого слова.
-- language epsilon "qwerty" == False
-- language epsilon "" == True
epsilon :: Grammar
epsilon = undefined

-- Пустой язык.
-- language empty "qwerty" == False
-- language empty "" == False
empty :: Grammar
empty = undefined

-- Язык, состоящий из односимвольных строк, удовлетворяющих предикату.
-- language (satisfy (/= 'x')) "qwerty" == False
-- language (satisfy (/= 'x')) "q" == True
-- language (satisfy (/= 'x')) "x" == False
-- language (satisfy (/= 'x')) "" == False
satisfy :: (Char -> Bool) -> Grammar
satisfy = undefined

infixl 3 <|, |>
infixl 4 <., .>

-- p .> q - Язык, состоящий из конкатенации слов из языка p и q.
-- language (satisfy (== 'q') .> satisfy (== 'w')) "qw" == True
-- language (satisfy (== 'q') .> satisfy (== 'x')) "qw" == False
-- language (satisfy (== 'x') .> satisfy (== 'w')) "qw" == False
-- language (satisfy (== 'q') .> satisfy (== 'w')) "qwerty" == False
-- 
-- epsilon - единица для .>
-- p .> epsilon == p
-- epsilon .> p == p
-- 
-- empty - ноль для .>
-- p .> empty == empty
-- empty .> p == empty
(.>) :: Grammar -> Grammar -> Grammar
(.>) = undefined

(<.) :: Grammar -> Grammar -> Grammar
p <. q = p .> q

-- Объединение языков.
-- language (satisfy (== 'q') |> satisfy (== 'x')) "q" == True
-- language (satisfy (== 'x') |> satisfy (== 'q')) "q" == True
-- language (satisfy (== 'x') |> satisfy (== 'y')) "q" == False
-- language (satisfy (== 'x') |> satisfy (== 'q')) "xq" == False
-- language (satisfy (== 'x') |> satisfy (== 'q')) "qx" == False
-- 
-- empty - единица для .>
-- p |> empty == p
-- empty |> p == p
(<|) :: Grammar -> Grammar -> Grammar
(<|) = undefined

(|>) :: Grammar -> Grammar -> Grammar
p |> q = q <| p
