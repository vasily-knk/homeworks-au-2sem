-- 1. Написать instance Eq, Show, Read, Functor для типа Tree a b, который был реализован в предыдущем ДЗ.
-- show и read должны работать следующим образом:
-- show (Branch (Leaf 1) 2 (Branch (Leaf 3) 4 (Leaf 5))) == "<1{2}<3{4}5>>"
-- (read "<1{2}<3{4}5>>" :: Tree Int Int) == Branch (Leaf 1) 2 (Branch (Leaf 3) 4 (Leaf 5))

import Tree

instance (Eq a, Eq b) => Eq (Tree a b) where
    (==) (Branch _ _ _) (Leaf _) = False
    (==) (Branch l a r) (Branch l' a' r') = (a == a') && (l == l') && (r == r')
    (==) (Leaf a) (Leaf b) = a == b
    (==) a b = b == a

instance (Show a, Show b) => Show (Tree a b) where
    showsPrec _ (Leaf a) = shows a
    showsPrec _ (Branch l a r) = ('<':) . shows l . ('{':) . shows a . ('}':) . shows r . ('>':)


readsTree ('<':buf) = [(Branch l v r, rightRest) | 
        (left,  '{':leftRest)  <- readsTree buf,
        (value, '}':valueRest) <- reads leftRest,
        (right, '>':rightRest) <- readsTree valueRest]
readsTree buf = [(Leaf value, rest) | (value, rest) <- reads(buf)]
    
instance (Read a, Read b) => Read (Tree a b) where
        readsPrec _ buf = readsTree buf
    
-- ???
instance Functor (Tree a) where
    fmap f (Leaf v) = Leaf $ f v
    fmap f (Branch l v r) = Branch (fmap f l) v (fmap f r)
    
tree = b (b (b l l) (b l (b l l))) (b l (b l (b l l)))
  where l = Leaf 500; b l r = Branch l 300 r