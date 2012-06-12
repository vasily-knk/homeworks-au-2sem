data Foo = Foo Foo | Bar Int deriving (Show)

fuck :: Foo -> Int
fuck (Bar i) = i
fuck (Foo f) 
    | f == Bar i = i
    | f == Foo f' = fuck f' + 1
