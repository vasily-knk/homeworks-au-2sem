module Combinators
    ( module Grammar
    , many
    , many1
    , char
    , anyChar
    , anything
    , string
    , digit
    , natural
    , integer
    , spaces
    , try
    , endBy
    , endBy1
    , sepBy
    , sepBy1
    , between
    , brackets
    , parens
    , braces
    , angles
    ) where

import Grammar

-- Язык, состоящий из одного односимвольного слова.
-- language (char 'q') "qwerty" == False
-- language (char 'x') "qwerty" == False
-- language (char 'x') "x" == True
char :: Char -> Grammar
char = undefined

-- Язык, состоящий из всех односимвольных слов.
-- language anyChar "x" == True
-- language anyChar "qwerty" == False
-- language anyChar "" == False
anyChar :: Grammar
anyChar = undefined

-- Язык, состоящий из цифр.
-- language digit "q" == False
-- language digit "1" == True
-- language digit "" == False
digit :: Grammar
digit = undefined

-- Язык, состоящий из одного слова.
-- language (string "qwerty") "qwerty" == True
-- language (string "qwerty") "qwertyuiop" == False
-- language (string "qwerty") "qwerry" == False
-- language (string "qwerty") "qwert" == False
string :: String -> Grammar
string = undefined

-- many p - язык, состоящий из многократной конкатенации слов из p (включая пустое слово).
-- language (many (char 'q')) "qqqwerty" == False
-- language (many (char 'q')) "qqq" == True
-- language (many (char 'q')) "q" == True
-- language (many (char 'q')) "" == True
many :: Grammar -> Grammar
many = undefined

-- many1 p - язык, состоящий из многократной конкатенации слов из p (исключая пустое слово).
-- language (many1 (char 'q')) "qqqwerty" == False
-- language (many1 (char 'q')) "qqq" == True
-- language (many1 (char 'q')) "q" == True
-- language (many1 (char 'q')) "" == False
many1 :: Grammar -> Grammar
many1 = undefined

-- Язык, состоящий из всех натуральных чисел.
-- language natural "qwerty" == False
-- language natural "123qwerty" == False
-- language natural "123" == True
-- language natural "-123" == False
-- language natural "" == False
natural :: Grammar
natural = undefined

-- Язык, состоящий из всех целых чисел.
-- language integer "qwerty" == False
-- language integer "123qwerty" == False
-- language integer "123" == True
-- language integer "-123" == True
-- language integer "" == False
integer :: Grammar
integer = undefined

-- Язык, состоящий из слов, состоящих из пробелов.
-- language spaces "qwerty" == False
-- language spaces "    qwerty" == False
-- language spaces "    " == True
-- language spaces "" == True
spaces :: Grammar
spaces = undefined

-- Добавляет пустое слово в язык.
-- language (try natural) "123qwerty" == False
-- language (try natural) "qwerty" == False
-- language (try natural) "123" == True
-- language (try natural) "" == True
try :: Grammar -> Grammar
try = undefined

-- Язык, состоящий из всех слов.
-- language anything "x" == True
-- language anything "qwerty" == True
-- language anything "" == True
anything :: Grammar
anything = undefined

-- endBy p q - язык, состоящий из последовательностей (включая пустую) чередующихся слов из p и q, начиная с p, заканчивая на q.
-- language (natural `endBy` char ';') "1;2;3;456;xyz;" == False
-- language (natural `endBy` char ';') "1;2;3;456;" == True
-- language (natural `endBy` char ';') "1;2;3;456" == False
-- language (natural `endBy` spaces) "12 25   300 " == True
-- language (natural `endBy` spaces) "12 25   300" == True
-- language (natural `endBy` spaces) "qwerty" == False
-- language (natural `endBy` spaces) "" == True
endBy :: Grammar -> Grammar -> Grammar
endBy = undefined

-- endBy1 p q - язык, состоящий из последовательностей (исключая пустую) чередующихся слов из p и q, начиная с p, заканчивая на q.
-- language (natural `endBy1` char ';') "1;2;3;456;xyz;" == False
-- language (natural `endBy1` char ';') "1;2;3;456;" == True
-- language (natural `endBy1` char ';') "1;2;3;456" == False
-- language (natural `endBy1` spaces) "12 25   300 " == True
-- language (natural `endBy1` spaces) "12 25   300" == True
-- language (natural `endBy1` spaces) "qwerty" == False
-- language (natural `endBy1` spaces) "" == False
endBy1 :: Grammar -> Grammar -> Grammar
endBy1 = undefined

-- sepBy p q - язык, состоящий из последовательностей (включая пустую) чередующихся слов из p и q, начиная и заканчивая на p.
-- language (natural `sepBy` char ';') "1;2;3;456;xyz;" == False
-- language (natural `sepBy` char ';') "1;2;3;456;" == False
-- language (natural `sepBy` char ';') "1;2;3;456" == True
-- language (natural `sepBy` spaces) "12 25   300 " == False
-- language (natural `sepBy` spaces) "12 25   300" == True
-- language (natural `sepBy` spaces) "qwerty" == False
-- language (natural `sepBy` spaces) "" == True
sepBy :: Grammar -> Grammar -> Grammar
sepBy = undefined

-- sepBy1 p q - язык, состоящий из последовательностей (исключая пустую) чередующихся слов из p и q, начиная и заканчивая на p.
-- language (natural `sepBy1` char ';') "1;2;3;456;xyz;" == False
-- language (natural `sepBy1` char ';') "1;2;3;456;" == False
-- language (natural `sepBy1` char ';') "1;2;3;456" == True
-- language (natural `sepBy1` spaces) "12 25   300 " == False
-- language (natural `sepBy1` spaces) "12 25   300" == True
-- language (natural `sepBy1` spaces) "qwerty" == False
-- language (natural `sepBy1` spaces) "" == False
sepBy1 :: Grammar -> Grammar -> Grammar
sepBy1 = undefined

-- between a b c - язык, состоящий из слов из a, c, b.
-- language (between (char 'a') (char 'b') (char 'c')) "abc" == False
-- language (between (char 'a') (char 'b') (char 'c')) "acb" == True
between :: Grammar -> Grammar -> Grammar -> Grammar
between = undefined

-- brackets p - язык, состоящий ищ слов вида [w], где w из p.
-- language (brackets (string "qwerty")) "[qwerty]" == True
-- language (brackets (string "qwerty")) "[qwertyu]" == False
brackets :: Grammar -> Grammar
brackets = undefined

-- parens p - язык, состоящий ищ слов вида (w), где w из p.
-- language (parens spaces) "(   )" == True
-- language (parens spaces) "(q)" == False
parens :: Grammar -> Grammar
parens = undefined

-- braces p - язык, состоящий ищ слов вида {w}, где w из p.
-- language (braces natural) "{123}" == True
-- language (braces natural) "{}" == False
braces :: Grammar -> Grammar
braces = undefined

-- angles p - язык, состоящий ищ слов вида <w>, где w из p.
-- language (angles digit) "<1>" == True
-- language (angles digit) "<1" == False
angles :: Grammar -> Grammar
angles = undefined
