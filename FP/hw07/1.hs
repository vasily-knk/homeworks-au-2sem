-- 1. �������� instance Eq, Show, Read, Functor ��� ���� Tree a b, ������� ��� ���������� � ���������� ��.
-- show � read ������ �������� ��������� �������:
-- show (Branch (Leaf 1) 2 (Branch (Leaf 3) 4 (Leaf 5))) == "<1{2}<3{4}5>>"
-- (read "<1{2}<3{4}5>>" :: Tree Int Int) == Branch (Leaf 1) 2 (Branch (Leaf 3) 4 (Leaf 5))

import Tree

instance (Eq a, Eq b) => Eq (Tree a b) where
    (==) (Branch _ _ _) (Leaf _) = False
    (==) (Branch l a r) (Branch l' a' r') = (a == a') && (l == l') && (r == r')
    (==) (Leaf a) (Leaf b) = a == b
    (==) a b = b == a

instance (Show a, Show b) => Show (Tree a b) where
    show (Branch l a r) = "<" ++ show l ++ "{" ++ show a ++ "}" ++ show r ++ ">"
    show (Leaf a) = show a
    
instance Functor (Tree a) where
    fmap f (Leaf a) = Leaf $ f a
    fmap f (Branch l a r) = Branch (fmap f l) (fmap f a) (fmap f r)
    