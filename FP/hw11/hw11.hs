module Main where

import Control.Monad
import Data.Monoid
import Control.Monad.Writer

-- 1.
-- �������� ������� for :: Monad m => Integer -> Integer -> (Integer -> m ()) -> m ()
-- ���, ����� for 0 5 (\i -> print i) ������� �� ����� 0, 1, 2, 3, 4.
       
for :: Monad m => Integer -> Integer -> (Integer -> m ()) -> m ()
for x y f = mapM_ f [x..(y-1)]
       
    
-- 3.
-- a) �������� ������ Counter. ��� ������ ������������ ��������
-- tick :: Counter ()
-- runCounter :: Counter a -> (a, Int) -- ���������� ��������� � ���������� �����.


newtype Counter a = Counter { runCounter :: (a, Int) }

counter :: (a, Int) -> Counter a
counter = Counter

tick :: Counter ()
tick = counter ((), 1)

instance Monad Counter where
    return x = Counter (x, 0)
    (Counter (x, i)) >>= f = Counter $ helper $ f x where
        helper (Counter (y, j)) = (y, j + i)

    
-- b) �������� �������
-- filter' :: (a -> Bool) -> [a] -> Counter [a]
-- append :: [a] -> [a] -> Counter [a]
-- qsort :: Ord a => [a] -> Counter [a]

filter' :: (a -> Bool) -> [a] -> Counter[a]
filter' _ [] = return []
filter' pred (x:xs)
    | pred x = do
        tick
        xs <- filter' pred xs 
        return (x : xs)
    | otherwise = filter' pred xs

append :: [a] -> [a] -> Counter [a]
append [] ys = return ys
append (x:xs) ys = do
    tick
    xs <- append xs ys
    return (x : xs)

qsort :: Ord a => [a] -> Counter [a]
qsort [] = return []
qsort (p:xs) = do
    tick
    lesser  <- filter' (< p) xs
    lesser  <- qsort lesser
    greater <- filter' (>= p) xs
    greater <- qsort greater
    lesser `append` (p : greater)
    

-- ���������� ��������� ������� filter, (++), sort, �� ��������� ���������� �����.
-- ���������, ��� ������ [8,4,12,2,6,10,14,1,3,5,7,9,11,13,15] ����������� �������, ��� [1..15]